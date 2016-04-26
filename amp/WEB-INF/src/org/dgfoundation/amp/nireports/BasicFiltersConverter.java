package org.dgfoundation.amp.nireports;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.dgfoundation.amp.newreports.ReportSpecification;
import org.dgfoundation.amp.newreports.ReportWarning;
import org.dgfoundation.amp.nireports.schema.NiDimension.LevelColumn;
import org.dgfoundation.amp.nireports.schema.NiDimension.NiDimensionUsage;
import org.dgfoundation.amp.nireports.schema.NiReportColumn;
import org.dgfoundation.amp.algo.AmpCollections;
import org.dgfoundation.amp.newreports.FilterRule;
import org.dgfoundation.amp.newreports.ReportColumn;
import org.dgfoundation.amp.newreports.ReportElement;
import org.dgfoundation.amp.newreports.ReportElement.ElementType;
import org.dgfoundation.amp.nireports.schema.NiDimension;
import org.dgfoundation.amp.nireports.schema.NiReportsSchema;
import org.dgfoundation.amp.nireports.schema.NiDimension.Coordinate;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

/**
 * a NiFilters converter which does the job (through subclasses, if necessary) in Basic or AMP-like filtering schemas
 * @author Dolghier Constantin
 *
 */
public abstract class BasicFiltersConverter {
	
	protected final Map<ReportElement, List<FilterRule>> rawRules;
	protected final ReportSpecification spec;
	protected final NiReportsEngine engine;
	protected final NiReportsSchema schema;
	
	/**
	 * predicates to be applied to the coordinates of all the cells in the report which respond to a given {@link NiDimensionUsage}
	 */
	protected final Map<NiDimensionUsage, List<Predicate<NiDimension.Coordinate>>> coosPredicates = new HashMap<>();
	
	/**
	 * predicates to apply to the cells of a given column
	 */
	protected final Map<String, List<Predicate<Cell>>> cellPredicates = new HashMap<>();
	
	/**
	 * columns whose actIds are to be a limiting superset of the ids which would make it into the report
	 */
	protected final Set<String> filteringColumns = new TreeSet<>();
	
	/**
	 * supplemental predicate to be applied to the activityIds generated by the other rules
	 */
	protected Optional<Predicate<Long>> activityIdsPredicate = Optional.empty();
	
	public BasicFiltersConverter(NiReportsEngine engine) {
		this.engine = engine;
		this.spec = engine.spec;
		this.schema = engine.schema;
		this.rawRules = (spec.getFilters() != null && spec.getFilters().getAllFilterRules() != null)
			? Collections.unmodifiableMap(spec.getFilters().getAllFilterRules())
			: Collections.emptyMap();
	}
	
	/**
	 * builds a {@link PassiveNiFilters} instance given the data in a running engine + a custom "workspace filter" callback
	 * @param activityIdsSrc the workspace filter
	 * @return
	 */
	public PassiveNiFilters buildNiFilters(Function<NiReportsEngine, Set<Long>> activityIdsSrc) {
		rawRules.forEach((repElem, rules) -> {
			if (rules != null && !rules.isEmpty())
				processElement(repElem, rules);
		});
		Set<String> mandatoryHiers = this.filteringColumns.stream().filter(this::shouldCreateVirtualHierarchy).collect(Collectors.toSet());
		return new PassiveNiFilters(engine, 
				AmpCollections.remap(coosPredicates, (niDim, preds) -> shouldCollapseDimension(niDim.dimension) ? AmpCollections.orPredicates(preds) : AmpCollections.mergePredicates(preds), null), 
				cellPredicates, filteringColumns, mandatoryHiers, activityIdsSrc, activityIdsPredicate);
	}

	/**
	 * returns true iff a given dimension behaves in a nonstandard "OR" way (e.g. all the filters related to this given NiDimension should be ORed between themselves instead of the usual behaviour of ANDing)
	 * @param dimension
	 * @return
	 */
	protected abstract boolean shouldCollapseDimension(NiDimension dimension);
	
	/**
	 * returns true if a given filtering column should also be used as a virtual hierarchy (expensive operation)
	 * @return
	 */
	protected abstract boolean shouldCreateVirtualHierarchy(String columnName);
	
	/**
	 * called for each of the filtering sets defined in the spec with a non-null and/or non-empty list of rules
	 * @param repElem
	 * @param rules
	 */
	protected void processElement(ReportElement repElem, List<FilterRule> rules) {
		if (repElem.type == ElementType.ENTITY) {
			NiUtils.failIf(!(repElem.entity instanceof ReportColumn),
				() -> String.format("entity type %s not supported: %s", repElem.entity.getClass().getName(), repElem.entity.toString()));
			if (repElem.entity instanceof ReportColumn)
				processColumnElement(repElem.entity.getEntityName(), rules);
		} else
			processMiscElement(repElem, rules);
	}
		
	/**
	 * callback called for each filter-by-column filtering set defined in the spec
	 * @param columnName
	 * @param rules
	 */
	protected void processColumnElement(String columnName, List<FilterRule> rules) {
		// filtering by column
		NiReportColumn<?> col = schema.getColumns().get(columnName);
		if (col == null) {
			engine.addReportWarning(new ReportWarning(String.format("not filtering by unimplemented column %s", columnName)));
			return;
		}

		notifySupportedColumn(columnName);

		if (col.levelColumn != null && col.levelColumn.isPresent()) {
			// filtering by a column which defines a LevelColumn: filter the whole report's cellset by the given LC 
			LevelColumn lc = col.levelColumn.get();
			Set<Long> positiveIds = FilterRule.mergeIdRules(rules.stream().filter(rule -> rule.valuesInclusive).collect(toList()));
			Set<Long> negativeIds = FilterRule.mergeIdRules(rules.stream().filter(rule -> !rule.valuesInclusive).collect(toList()));
			addRulesIfPresent(lc, true, positiveIds);
			addRulesIfPresent(lc, false, negativeIds);
		} else {
			// only filter the given column by entity ids
			addCellPredicate(columnName, cell -> cell.entityId, rules);
		}
	}

	/**
	 * called when filtering cells by a predicate 
	 * @param columnName the column whose fetched cells are filtered by the predicate
	 * @param cellIdExtractor the callback used to extract the relevant "id" from the cell (usually an entityId or maybe the Julian code of a date)
	 * @param rules the spec-rules
	 */
	protected void addCellPredicate(String columnName, Function<Cell, Long> cellIdExtractor, List<FilterRule> rules) {
		List<Predicate<Cell>> cellPreds = new ArrayList<>();
		rules.stream().map(FilterRule::buildPredicate).forEach(lp -> {
			cellPreds.add(cell -> lp.test(cellIdExtractor.apply(cell)));
		});
		cellPredicates.computeIfAbsent(columnName, ignored -> new ArrayList<>()).addAll(cellPreds);
	}
	
	/**
	 * callback for notifying subclasses that we are filtering by a column
	 * @param columnName
	 */
	protected void notifySupportedColumn(String columnName) {
		this.filteringColumns.add(columnName);
	}
		
	/**
	 * in case a given set of ids is present, add a coordinates predicate to the under-construction state
	 * @param lc
	 * @param positive
	 * @param ids
	 */
	protected void addRulesIfPresent(LevelColumn lc, boolean positive, Set<Long> ids) {
		if (ids == null)
			return;
				
		boolean collapseDimension = shouldCollapseDimension(lc.dimensionUsage.dimension);
		int level = collapseDimension ? NiDimension.LEVEL_ALL_IDS : lc.level;
		
		Predicate<Coordinate> predicate = FilterRule.maybeNegated(engine.buildAcceptor(lc.dimensionUsage, ids.stream().map(z -> new Coordinate(level, z)).collect(toList())), positive);
		coosPredicates.computeIfAbsent(lc.dimensionUsage, ignored -> new ArrayList<>()).add(predicate);
	}
	
	/**
	 * callback for filtering on non-column items
	 * @param repElem
	 * @param rules
	 */
	protected abstract void processMiscElement(ReportElement repElem, List<FilterRule> rules);
}