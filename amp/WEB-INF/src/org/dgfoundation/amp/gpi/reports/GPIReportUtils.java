package org.dgfoundation.amp.gpi.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.dgfoundation.amp.ar.ArConstants;
import org.dgfoundation.amp.ar.ColumnConstants;
import org.dgfoundation.amp.ar.MeasureConstants;
import org.dgfoundation.amp.newreports.AmpReportFilters;
import org.dgfoundation.amp.newreports.FilterRule;
import org.dgfoundation.amp.newreports.GeneratedReport;
import org.dgfoundation.amp.newreports.GroupingCriteria;
import org.dgfoundation.amp.newreports.ReportAreaImpl;
import org.dgfoundation.amp.newreports.ReportColumn;
import org.dgfoundation.amp.newreports.ReportElement;
import org.dgfoundation.amp.newreports.ReportElement.ElementType;
import org.dgfoundation.amp.newreports.ReportMeasure;
import org.dgfoundation.amp.newreports.ReportSettingsImpl;
import org.dgfoundation.amp.newreports.ReportSpecification;
import org.dgfoundation.amp.newreports.ReportSpecificationImpl;
import org.digijava.kernel.ampapi.endpoints.common.EPConstants;
import org.digijava.kernel.ampapi.endpoints.common.EndpointUtils;
import org.digijava.kernel.ampapi.endpoints.settings.SettingsUtils;
import org.digijava.kernel.ampapi.endpoints.util.FilterUtils;
import org.digijava.kernel.ampapi.endpoints.util.JsonBean;
import org.digijava.module.common.util.DateTimeUtil;

public class GPIReportUtils {

	/**
	 * 
	 * @param indicatorCode
	 * @param formParams
	 * @return generatedRerport {@link GeneratedReport}
	 */
	public static GeneratedReport getGeneratedReportForIndicator(String indicatorCode, JsonBean formParams) {

		switch (indicatorCode) {
		case GPIReportConstants.REPORT_5a:
			return getGeneratedReportForIndicator5a(formParams);
		case GPIReportConstants.REPORT_5b:
			return getGeneratedReportForIndicator5b(formParams);
		case GPIReportConstants.REPORT_6:
			return getGeneratedReportForIndicator6(formParams);
		case GPIReportConstants.REPORT_9b:
			return getGeneratedReportForIndicator9b(formParams);
		default:
			return null;
		}
	}

	/**
	 * create the template for the gpi report 5b. It can be refactored to
	 * another class
	 * 
	 * @param formParams
	 * @return generatedReport 
	 */
	public static GeneratedReport getGeneratedReportForIndicator5a(JsonBean formParams) {

		ReportSpecificationImpl spec = new ReportSpecificationImpl(GPIReportConstants.REPORT_5a,
				ArConstants.DONOR_TYPE);

		String hierarchyColumn = getHierarchyColumn(formParams);
		if (hierarchyColumn.equals(GPIReportConstants.HIERARCHY_DONOR_GROUP)) {
			spec.addColumn(new ReportColumn(ColumnConstants.DONOR_GROUP));
			spec.getHierarchies().add(new ReportColumn(ColumnConstants.DONOR_GROUP));
		} else {
			spec.addColumn(new ReportColumn(ColumnConstants.DONOR_AGENCY));
			spec.getHierarchies().add(new ReportColumn(ColumnConstants.DONOR_AGENCY));
		}
		
		spec.addColumn(new ReportColumn(ColumnConstants.ON_OFF_TREASURY_BUDGET));
		spec.addColumn(new ReportColumn(ColumnConstants.EXECUTING_AGENCY));
		spec.addColumn(new ReportColumn(ColumnConstants.DONOR_GROUP));
		
		spec.getHierarchies().add(new ReportColumn(ColumnConstants.ON_OFF_TREASURY_BUDGET));
		spec.getHierarchies().add(new ReportColumn(ColumnConstants.EXECUTING_AGENCY));

		spec.addMeasure(new ReportMeasure(MeasureConstants.ACTUAL_DISBURSEMENTS));
		spec.addMeasure(new ReportMeasure(MeasureConstants.PLANNED_DISBURSEMENTS));
		spec.addMeasure(new ReportMeasure(MeasureConstants.DISBURSED_AS_SCHEDULED));
		spec.addMeasure(new ReportMeasure(MeasureConstants.OVER_DISBURSED));
		spec.setGroupingCriteria(GroupingCriteria.GROUPING_YEARLY);
		spec.setSummaryReport(true);

		applyAppovalStatusFilter(formParams, spec);

		SettingsUtils.applySettings(spec, formParams, true);
		clearYearRangeSettings(spec);

		GeneratedReport generatedReport = EndpointUtils.runReport(spec, ReportAreaImpl.class, null);

		return generatedReport;
	}

	/**
	 * create the template for the gpi report 5b. It can be refactored to
	 * another class
	 * 
	 * @param formParams
	 * @return generatedReport 
	 */
	public static GeneratedReport getGeneratedReportForIndicator5b(JsonBean formParams) {

		ReportSpecificationImpl spec = new ReportSpecificationImpl(GPIReportConstants.REPORT_5b,
				ArConstants.DONOR_TYPE);

		String hierarchyColumn = getHierarchyColumn(formParams);
		if (hierarchyColumn.equals(GPIReportConstants.HIERARCHY_DONOR_GROUP)) {
			spec.addColumn(new ReportColumn(ColumnConstants.DONOR_GROUP));
			spec.getHierarchies().add(new ReportColumn(ColumnConstants.DONOR_GROUP));
		} else {
			spec.addColumn(new ReportColumn(ColumnConstants.DONOR_AGENCY));
			spec.getHierarchies().add(new ReportColumn(ColumnConstants.DONOR_AGENCY));
		}

		spec.addMeasure(new ReportMeasure(MeasureConstants.ACTUAL_DISBURSEMENTS));
		spec.setGroupingCriteria(GroupingCriteria.GROUPING_YEARLY);
		spec.setSummaryReport(true);

		applyAppovalStatusFilter(formParams, spec);

		SettingsUtils.applySettings(spec, formParams, true);
		clearYearRangeSettings(spec);

		for (String mtefColumn : getMTEFColumnsForIndicator5b(spec)) {
			spec.addColumn(new ReportColumn(mtefColumn));
		}


		GeneratedReport generatedReport = EndpointUtils.runReport(spec, ReportAreaImpl.class, null);

		return generatedReport;
	}

	/**
	 * create the template for the gpi report 6. It can be refactored to another
	 * class
	 * 
	 * @param formParams
	 * @return generatedReport 
	 */
	public static GeneratedReport getGeneratedReportForIndicator6(JsonBean formParams) {

		ReportSpecificationImpl spec = new ReportSpecificationImpl(GPIReportConstants.REPORT_6, ArConstants.DONOR_TYPE);

		String hierarchyColumn = getHierarchyColumn(formParams);
		if (hierarchyColumn.equals(GPIReportConstants.HIERARCHY_DONOR_GROUP)) {
			spec.addColumn(new ReportColumn(ColumnConstants.DONOR_GROUP));
			spec.getHierarchies().add(new ReportColumn(ColumnConstants.DONOR_GROUP));
		} else {
			spec.addColumn(new ReportColumn(ColumnConstants.DONOR_AGENCY));
			spec.getHierarchies().add(new ReportColumn(ColumnConstants.DONOR_AGENCY));
		}
		
		spec.setGroupingCriteria(GroupingCriteria.GROUPING_YEARLY);
		spec.addMeasure(new ReportMeasure(MeasureConstants.PLANNED_DISBURSEMENTS));
		spec.setSummaryReport(true);

		applyAppovalStatusFilter(formParams, spec);

		SettingsUtils.applySettings(spec, formParams, true);
		clearYearRangeSettings(spec);
		
		GeneratedReport generatedReport = EndpointUtils.runReport(spec, ReportAreaImpl.class, null);

		return generatedReport;
	}

	/**
	 * create the template for the gpi report 9b. It can be refactored to
	 * another class
	 * 
	 * @param formParams
	 * @return generatedReport 
	 */
	public static GeneratedReport getGeneratedReportForIndicator9b(JsonBean formParams) {

		ReportSpecificationImpl spec = new ReportSpecificationImpl(GPIReportConstants.REPORT_9b, ArConstants.GPI_TYPE);

		String hierarchyColumn = getHierarchyColumn(formParams);
		if (hierarchyColumn.equals(GPIReportConstants.HIERARCHY_DONOR_GROUP)) {
			spec.addColumn(new ReportColumn(ColumnConstants.DONOR_GROUP));
			spec.getHierarchies().add(new ReportColumn(ColumnConstants.DONOR_GROUP));
		} else {
			spec.addColumn(new ReportColumn(ColumnConstants.DONOR_AGENCY));
			spec.getHierarchies().add(new ReportColumn(ColumnConstants.DONOR_AGENCY));
		}
		
		spec.setGroupingCriteria(GroupingCriteria.GROUPING_YEARLY);
		spec.addMeasure(new ReportMeasure(MeasureConstants.NATIONAL_BUDGET_EXECUTION_PROCEDURES));
		spec.addMeasure(new ReportMeasure(MeasureConstants.NATIONAL_FINANCIAL_REPORTING_PROCEDURES));
		spec.addMeasure(new ReportMeasure(MeasureConstants.NATIONAL_AUDITING_PROCEDURES));
		spec.addMeasure(new ReportMeasure(MeasureConstants.NATIONAL_PROCUREMENT_EXECUTION_PROCEDURES));
		spec.setSummaryReport(true);

		applyAppovalStatusFilter(formParams, spec);

		SettingsUtils.applySettings(spec, formParams, true);
		clearYearRangeSettings(spec);

		GeneratedReport generatedReport = EndpointUtils.runReport(spec, ReportAreaImpl.class, null);

		return generatedReport;
	}

	private static String getHierarchyColumn(JsonBean formParams) {
		if (formParams.get(GPIReportConstants.HIERARCHY_PARAMETER) != null) {
			return (String) formParams.get(GPIReportConstants.HIERARCHY_PARAMETER);
		}

		return "";
	}
	
	/**
	 * @param formParams
	 * @param spec
	 */
	public static void applyAppovalStatusFilter(JsonBean formParams, ReportSpecificationImpl spec) {
		if (formParams != null) {
			Map<String, Object> filters = (Map<String, Object>) formParams.get(EPConstants.FILTERS);
			AmpReportFilters filterRules = FilterUtils.getFilterRules(filters, null);

			if (filterRules == null) {
				filterRules = new AmpReportFilters();
			}

			ReportElement elem = new ReportElement(new ReportColumn(ColumnConstants.APPROVAL_STATUS));

			// Validated Activities - 4
			FilterRule filterRule = new FilterRule(Arrays.asList("4"), true);
			filterRules.addFilterRule(elem, filterRule);

			spec.setFilters(filterRules);
		}
	}

	/**
	 * @param spec
	 */
	public static void clearYearRangeSettings(ReportSpecificationImpl spec) {
		ReportSettingsImpl reportSettings = (ReportSettingsImpl) spec.getSettings();
		reportSettings.setYearRangeFilter(null);
	}

	public static List<String> getMTEFColumnsForIndicator5b(ReportSpecification spec) {
		
		FilterRule dateFilterRule = getDateFilterRule(spec);
		if (dateFilterRule == null || StringUtils.isBlank(dateFilterRule.min)) {
			throw new RuntimeException("No year selected. Please specify the date filter");
		}
		
		Date fromJulianNumberToDate = DateTimeUtil.fromJulianNumberToDate(dateFilterRule.min);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromJulianNumberToDate);
		int year = cal.get(Calendar.YEAR);

		List<String> mtefColumns = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			mtefColumns.add("MTEF " + (year + i));
		}

		return mtefColumns;
	}

	public static FilterRule getDateFilterRule(ReportSpecification spec) {
		Optional<Entry<ReportElement, FilterRule>> dateRuleEntry = spec.getFilters()
				.getFilterRules().entrySet().stream()
				.filter(entry -> entry.getKey().type.equals(ElementType.DATE))
				.filter(entry -> entry.getKey().entity == null)
				.findAny();
		
		FilterRule dateRule = dateRuleEntry.isPresent() ? dateRuleEntry.get().getValue() : null;
		
		return dateRule;
	}
}
