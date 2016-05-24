package org.dgfoundation.amp.nireports;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.dgfoundation.amp.algo.AmpCollections;
import org.dgfoundation.amp.algo.Memoizer;
import org.dgfoundation.amp.nireports.schema.NiDimension;
import org.dgfoundation.amp.nireports.schema.NiDimension.Coordinate;
import org.dgfoundation.amp.nireports.schema.NiDimension.NiDimensionUsage;
import org.dgfoundation.amp.nireports.schema.NiReportColumn;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static org.dgfoundation.amp.algo.AmpCollections.remap;

/**
 * a {@link NiFilters} instance which holds the result of computations done elsewhere
 * @author Dolghier Constantin
 *
 */
public class PassiveNiFilters implements NiFilters {
		
	/**
	 * the {@link NiReportsEngine} instance used as a context to generate this instance
	 */
	protected final NiReportsEngine engine;
	
	/**
	 * the predicates on coordinates applied to all the cells of the report which respond to a given coordinate
	 */
	protected final Map<NiDimensionUsage, Predicate<NiDimension.Coordinate>> filteringCoordinates;
	
	/**
	 * the predicates on cells applied to the cells of given columns
	 */
	protected final Map<String, Predicate<Cell>> cellPredicates;
	
	/**
	 * the columns whose filtered-out ids are to be used to filter out the ids eligible for the report
	 */
	protected final Set<String> filteringColumns;
	
	/**
	 * the function used to generate the initial activity ids 
	 */
	protected final Function<NiReportsEngine, Set<Long>> activityIdsComputer;
	
	/**
	 * the memoized set of ids of the initial workspace filter
	 */
	protected final Memoizer<Set<Long>> workspaceFilter;
	
	/**
	 * the supplemental Predicate to be applied on the activity ids
	 */
	protected final Predicate<Long> activityIdsPredicate;
	
	/**
	 * the hierarchies which the generated report MUST have. They will be generated virtually and then removed in case the ReportSpec happens to not specify them
	 */
	protected final Set<String> mandatoryHiers;
	
	public PassiveNiFilters(NiReportsEngine engine, Map<NiDimensionUsage, Predicate<Coordinate>> filteringCoordinates, Map<String, List<Predicate<Cell>>> cellPredicates, Set<String> filteringColumns, Set<String> mandatoryHiers, Function<NiReportsEngine, Set<Long>> activityIdsComputer, 
			Optional<Predicate<Long>> activityIdsPredicate) {
		Objects.requireNonNull(activityIdsComputer);
		Objects.requireNonNull(engine);
		this.engine = engine;
		this.filteringCoordinates = unmodifiableMap(filteringCoordinates);
		this.cellPredicates = unmodifiableMap(remap(cellPredicates, AmpCollections::mergePredicates, null));
		this.filteringColumns = unmodifiableSet(filteringColumns);
		this.activityIdsComputer = activityIdsComputer;
		this.activityIdsPredicate = activityIdsPredicate.orElse(ignored -> true);
		this.workspaceFilter = new Memoizer<Set<Long>>(() -> this.activityIdsComputer.apply(this.engine).stream().filter(this.activityIdsPredicate).collect(Collectors.toSet()));
		this.mandatoryHiers = unmodifiableSet(mandatoryHiers);
	}
	
	@Override
	public Map<NiDimensionUsage, Predicate<NiDimension.Coordinate>> getProcessedFilters() {
		return filteringCoordinates;
	}
	
	@Override
	public Map<String, Predicate<Cell>> getCellPredicates() {
		return this.cellPredicates;
	}
	
	@Override
	public Set<String> getFilteringColumns() {
		return filteringColumns;
	}

	@Override
	public Set<Long> getWorkspaceActivityIds() {
		return workspaceFilter.get();
	}

	@Override
	public Set<Long> getFilteredActivityIds() {
		Set<Long> curResult = new HashSet<>(getWorkspaceActivityIds());
		for(String fetchedColumn:engine.fetchedColumns.keySet().stream().filter(this::isFilteringColumn).collect(Collectors.toList())) { //TODO: maybe refactor this to plainly iterate over #getProcessedFilters (after we have some testcases)
			Set<Long> colIds = engine.fetchedColumns.get(fetchedColumn).data.keySet();
			curResult.retainAll(colIds);
		}
		return curResult;
	}
	
	/**
	 * returns true IFF this column should be used for filtering entityIDs
	 * @param colName
	 * @return
	 */
	protected boolean isFilteringColumn(String colName) {
		NiReportColumn<?> col = engine.schema.getColumns().get(colName);
		
		if (col == null)
			return false;
		 
		if (cellPredicates.containsKey(colName))
			return true;
		
		if (!col.levelColumn.isPresent())
			return false;
		
		return getProcessedFilters().keySet().contains(col.levelColumn.get().dimensionUsage);
	}

	@Override
	public Set<String> getMandatoryHiers() {
		return this.mandatoryHiers;
	}
	
}