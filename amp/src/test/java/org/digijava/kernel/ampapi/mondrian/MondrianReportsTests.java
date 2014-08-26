/**
 * 
 */
package org.digijava.kernel.ampapi.mondrian;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.dgfoundation.amp.ar.ColumnConstants;
import org.dgfoundation.amp.ar.MeasureConstants;
import org.dgfoundation.amp.ar.ReportContextData;
import org.dgfoundation.amp.newreports.GeneratedReport;
import org.dgfoundation.amp.newreports.GroupingCriteria;
import org.dgfoundation.amp.newreports.ReportAreaImpl;
import org.dgfoundation.amp.newreports.ReportColumn;
import org.dgfoundation.amp.newreports.ReportEntityType;
import org.dgfoundation.amp.newreports.ReportMeasure;
import org.dgfoundation.amp.newreports.ReportSpecification;
import org.dgfoundation.amp.newreports.ReportSpecificationImpl;
import org.dgfoundation.amp.newreports.SortingInfo;
import org.dgfoundation.amp.reports.mondrian.MondrianReportFilters;
import org.dgfoundation.amp.reports.mondrian.MondrianReportGenerator;
import org.dgfoundation.amp.reports.mondrian.MondrianReportUtils;
import org.dgfoundation.amp.testutils.AmpTestCase;
import org.dgfoundation.amp.testutils.ReportTestingUtils;
import org.digijava.kernel.ampapi.mondrian.util.MoConstants;
import org.digijava.kernel.request.TLSUtils;
import org.digijava.module.aim.dbentity.AmpReports;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Test Reports generation via Reports API provided by {@link org.dgfoundation.amp.reports.mondrian.MondrianReportGenerator MondrianReportGenerator}
 * @author Nadejda Mandrescu
 */
public class MondrianReportsTests extends AmpTestCase {
	
	private MondrianReportsTests(String name) {
		super(name);
	}
	
	public static Test suite()
	{
		TestSuite suite = new TestSuite(MondrianReportsTests.class.getName());
		/* tests are failing so far, because we are moving out the totals calculation from MDX, 
		 * to revise when GeneratedReport structure will be build based on CellDataSet  
		suite.addTest(new MondrianReportsTests("testNoTotals"));
		suite.addTest(new MondrianReportsTests("testTotals"));
		suite.addTest(new MondrianReportsTests("testColumnSortingNoTotals"));
		suite.addTest(new MondrianReportsTests("testColumnMeasureSortingTotals"));
		suite.addTest(new MondrianReportsTests("testSortingByTuplesTotals"));
		suite.addTest(new MondrianReportsTests("testMultipleDateFilters"));
		suite.addTest(new MondrianReportsTests("testAmpReportToReportSpecification"));
		*/
		suite.addTest(new MondrianReportsTests("testGenerateReportAsOlap4JCellSet"));
		//suite.addTest(new MondrianReportsTests("testHeavyQuery"));
		
		return suite;
	}
	
	public void testNoTotals() {
		ReportSpecificationImpl spec = getDefaultSpec("testNoTotals", false);
		spec.addColumn(new ReportColumn(ColumnConstants.PROJECT_TITLE, ReportEntityType.ENTITY_TYPE_ALL));
		generateAndValidate(spec, true);
	}
	
	public void testTotals() {
		ReportSpecificationImpl spec = getDefaultSpec("testTotals", true);
		generateAndValidate(spec, true);
	}
	
	public void testColumnSortingNoTotals() {
		ReportSpecificationImpl spec = getDefaultSpec("testColumnSortingNoTotals", false);
		spec.addSorter(new SortingInfo(new ReportColumn(ColumnConstants.DONOR_TYPE, ReportEntityType.ENTITY_TYPE_ALL), true)); //ascending
		spec.addSorter(new SortingInfo(new ReportColumn(ColumnConstants.PRIMARY_SECTOR, ReportEntityType.ENTITY_TYPE_ACTIVITY), false)); //descending
		generateAndValidate(spec, true);
	}
	
	public void testColumnMeasureSortingTotals() {
		ReportSpecificationImpl spec = getDefaultSpec("testColumnMeasureSortingTotals", true);
		spec.addSorter(new SortingInfo(new ReportColumn(ColumnConstants.DONOR_TYPE, ReportEntityType.ENTITY_TYPE_ALL), true)); //ascending
		spec.addSorter(new SortingInfo(new ReportMeasure(MeasureConstants.ACTUAL_COMMITMENTS, ReportEntityType.ENTITY_TYPE_ALL), false)); //descending
		generateAndValidate(spec, true);
	}

	public void testSortingByTuplesTotals() {
		ReportSpecificationImpl spec = getDefaultSpec("testSortingByTuplesTotals", true);
		spec.setGroupingCriteria(GroupingCriteria.GROUPING_QUARTERLY);
		spec.addSorter(new SortingInfo(new ReportColumn(ColumnConstants.DONOR_TYPE, ReportEntityType.ENTITY_TYPE_ALL), true)); //ascending
		spec.addSorter(new SortingInfo("2013", "Q2", new ReportMeasure(MeasureConstants.ACTUAL_COMMITMENTS, ReportEntityType.ENTITY_TYPE_ALL), false)); //descending
		generateAndValidate(spec, true);
	}
	
	public void testMultipleDateFilters() {
		ReportSpecificationImpl spec = getDefaultSpec("testMultipleDateFilters", true);
		String err = null;
		spec.setGroupingCriteria(GroupingCriteria.GROUPING_QUARTERLY);
		MondrianReportFilters filters = new MondrianReportFilters();
		SimpleDateFormat sdf = new SimpleDateFormat(MoConstants.DATE_FORMAT);
		try {
			filters.addDateRangeFilterRule(sdf.parse("2010-01-01"), sdf.parse("2011-09-15"));
			filters.addYearsFilterRule(Arrays.asList(2013, 2014), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			err = e.getMessage();
		}
		assertNull(err);
		spec.setFilters(filters);
		generateAndValidate(spec, true);
	}

	private ReportSpecificationImpl getDefaultSpec(String name, boolean doTotals) {
		ReportSpecificationImpl spec = new ReportSpecificationImpl(name);
		spec.addColumn(new ReportColumn(ColumnConstants.DONOR_TYPE, ReportEntityType.ENTITY_TYPE_ALL));
		spec.addColumn(new ReportColumn(ColumnConstants.PRIMARY_SECTOR, ReportEntityType.ENTITY_TYPE_ACTIVITY));
		spec.addMeasure(new ReportMeasure(MeasureConstants.ACTUAL_COMMITMENTS, ReportEntityType.ENTITY_TYPE_ALL));
		spec.addMeasure(new ReportMeasure(MeasureConstants.ACTUAL_DISBURSEMENTS, ReportEntityType.ENTITY_TYPE_ALL));
		spec.setCalculateColumnTotals(doTotals);
		spec.setCalculateRowTotals(doTotals);
		return spec;
	}
	public void testAmpReportToReportSpecification1() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attr = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attr);
		HttpServletRequest test = TLSUtils.getRequest();
		System.out.println(test);
	}
	
	public void testAmpReportToReportSpecification() {
		ReportSpecificationImpl spec = getReportSpecificatin("NadiaMondrianTest");
		generateAndValidate(spec, true);
	}
	
	public void testGenerateReportAsOlap4JCellSet() {
		ReportSpecificationImpl spec = getReportSpecificatin("NadiaMondrianTest");
		generateAndValidate(spec, true, true);
	}
	
	private ReportSpecificationImpl getReportSpecificatin(String reportName) {
		//AmpReports report = (AmpReports) PersistenceManager.getSession().get(AmpReports.class, 1018L);//id is from Moldova DB, TODO: update for tests db 
		AmpReports report = ReportTestingUtils.loadReportByName(reportName);

		org.apache.struts.mock.MockHttpServletRequest mockRequest = new org.apache.struts.mock.MockHttpServletRequest(new org.apache.struts.mock.MockHttpSession());
		mockRequest.setAttribute("ampReportId", report.getId().toString());
		TLSUtils.populate(mockRequest);
		ReportContextData.createWithId(report.getId().toString(), true);

		return MondrianReportUtils.toReportSpecification(report);
	}
	
	
	
	public void testHeavyQuery() {
		long start = System.currentTimeMillis();
		ReportSpecificationImpl spec = new ReportSpecificationImpl("testHeavyQuery");
		spec.addColumn(new ReportColumn(ColumnConstants.REGION, ReportEntityType.ENTITY_TYPE_ALL));
		spec.addColumn(new ReportColumn(ColumnConstants.PRIMARY_SECTOR, ReportEntityType.ENTITY_TYPE_ACTIVITY));
		spec.addColumn(new ReportColumn(ColumnConstants.PROJECT_TITLE, ReportEntityType.ENTITY_TYPE_ALL));
		spec.addMeasure(new ReportMeasure(MeasureConstants.ACTUAL_COMMITMENTS, ReportEntityType.ENTITY_TYPE_ALL));
		spec.addMeasure(new ReportMeasure(MeasureConstants.ACTUAL_DISBURSEMENTS, ReportEntityType.ENTITY_TYPE_ALL));
		spec.setGroupingCriteria(GroupingCriteria.GROUPING_YEARLY);
		generateAndValidate(spec, true);
		long end = System.currentTimeMillis();
		System.out.println("Full time (with printing) = " + (end-start) + "(ms)");
	}
	
	private void generateAndValidate(ReportSpecification spec, boolean print) {
		generateAndValidate(spec, print, false);
	}
	
	private void generateAndValidate(ReportSpecification spec, boolean print, boolean asCellSet) {
		String err = null;
		MondrianReportGenerator generator = new MondrianReportGenerator(ReportAreaImpl.class, print);
		GeneratedReport report = null;
		CellDataSet cellSet = null;
		try {
			if (asCellSet)
				cellSet = generator.generateReportAsSaikuCellDataSet(spec);
			else { 
				report = generator.executeReport(spec);
				System.out.println("Generation duration = " + report.generationTime + "(ms)");
			}	
		} catch (Exception e) {
			System.err.println(e.getMessage());
			err = e.getMessage();
		}
		//basic tests, todo more
		assertNull(err);
		if (asCellSet)
			assertNotNull(cellSet);
		else {
			assertNotNull(report);
			assertNotNull(report.reportContents);
		}
	}
}
