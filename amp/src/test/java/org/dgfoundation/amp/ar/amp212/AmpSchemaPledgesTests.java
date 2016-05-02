package org.dgfoundation.amp.ar.amp212;

import java.util.Arrays;
import java.util.List;

import org.dgfoundation.amp.ar.ArConstants;
import org.dgfoundation.amp.ar.ColumnConstants;
import org.dgfoundation.amp.ar.MeasureConstants;
import org.dgfoundation.amp.mondrian.ReportAreaForTests;
import org.dgfoundation.amp.mondrian.ReportingTestCase;
import org.dgfoundation.amp.newreports.AreaOwner;
import org.dgfoundation.amp.newreports.GroupingCriteria;
import org.dgfoundation.amp.newreports.ReportColumn;
import org.dgfoundation.amp.newreports.ReportSpecification;
import org.dgfoundation.amp.newreports.ReportSpecificationImpl;
import org.dgfoundation.amp.newreports.SortingInfo;
import org.dgfoundation.amp.nireports.GrandTotalsDigest;
import org.dgfoundation.amp.nireports.output.NiReportExecutor;
import org.dgfoundation.amp.reports.mondrian.converters.AmpReportsToReportSpecification;
import org.dgfoundation.amp.testmodels.NiReportModel;
import org.dgfoundation.amp.testutils.ReportTestingUtils;
import org.junit.Test;

/**
 * 
 * testcases for the fetching states of AMP + the AMP schema
 * 
 * @author Constantin Dolghier
 *
 */
public class AmpSchemaPledgesTests extends ReportingTestCase {
	
	public AmpSchemaPledgesTests() {
		super("AmpSchemaPledgesTests");
	}
	
	final List<String> acts = Arrays.asList(
			"activity 1 with agreement",
			"Activity 2 with multiple agreements",
			"Activity Linked With Pledge",
			"Activity with both MTEFs and Act.Comms",
			"activity with capital spending",
			"activity with components",
			"activity with contracting agency",
			"activity with directed MTEFs",
			"activity_with_disaster_response",
			"activity with funded components",
			"activity with incomplete agreement",
			"activity with many MTEFs",
			"activity with pipeline MTEFs and act. disb",
			"Activity with planned disbursements",
			"activity with primary_program",
			"Activity with primary_tertiary_program",
			"activity with tertiary_program",
			"activity-with-unfunded-components",
			"Activity with Zones",
			"Activity With Zones and Percentages",
			"crazy funding 1",
			"date-filters-activity",
			"Eth Water",
			"execution rate activity",
			"mtef activity 1",
			"mtef activity 2",
			"new activity with contracting",
			"pledged 2",
			"pledged education activity 1",
			"Project with documents",
			"Proposed Project Cost 1 - USD",
			"Proposed Project Cost 2 - EUR",
			"ptc activity 1",
			"ptc activity 2",
			"Pure MTEF Project",
			"SSC Project 1",
			"SSC Project 2",
			"SubNational no percentages",
			"TAC_activity_1",
			"TAC_activity_2",
			"Test MTEF directed",
			"third activity with agreements",
			"Unvalidated activity",
			"with weird currencies"
		);
	
	@Override
	protected NiReportExecutor getNiExecutor(List<String> activityNames) {
		return getDbExecutor(activityNames);
	}
	
	protected ReportSpecificationImpl buildPledgeReport(String reportName, List<String> columns, List<String> measures, 
            List<String> hierarchies, GroupingCriteria groupingCriteria) {
        return ReportSpecificationImpl.buildFor(reportName, columns, measures, hierarchies, groupingCriteria, 
                ArConstants.PLEDGES_TYPE);
    }
	
	@Test
	public void testPlainPledgeReport() {
		NiReportModel cor = new NiReportModel("AMP-16003-flat")
		        .withHeaders(Arrays.asList(
		                "(RAW: (startRow: 0, rowSpan: 1, totalRowSpan: 4, colStart: 0, colSpan: 6))",
		                "(Pledges Titles: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 0, colSpan: 1));(Pledge Status: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 1, colSpan: 1));(Funding: (startRow: 1, rowSpan: 1, totalRowSpan: 3, colStart: 2, colSpan: 3));(Totals: (startRow: 1, rowSpan: 2, totalRowSpan: 3, colStart: 5, colSpan: 1))",
		                "(2012: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 2, colSpan: 1));(2013: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 3, colSpan: 1));(2014: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 4, colSpan: 1))",
		                "(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 2, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 3, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 4, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 5, colSpan: 1))"))
		            .withWarnings(Arrays.asList())
		            .withBody(      new ReportAreaForTests(null)
		              .withContents("Pledges Titles", "", "Pledge Status", "", "Funding-2012-Actual Pledge", "1 041 111,77", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2014-Actual Pledge", "9 186 878,1", "Totals-Actual Pledge", "12 966 059,62")
		              .withChildren(
		                new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Pledge Status", "second status", "Funding-2012-Actual Pledge", "1,25", "Funding-2014-Actual Pledge", "986 878,1", "Totals-Actual Pledge", "986 879,35"),
		                new ReportAreaForTests(new AreaOwner(4), "Pledges Titles", "ACVL Pledge Name 2", "Totals-Actual Pledge", "938 069,75"),
		                new ReportAreaForTests(new AreaOwner(5), "Pledges Titles", "free text name 2", "Funding-2012-Actual Pledge", "1 041 110,52", "Totals-Actual Pledge", "1 041 110,52"),
		                new ReportAreaForTests(new AreaOwner(6), "Pledges Titles", "Heavily used pledge", "Pledge Status", "default status", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2014-Actual Pledge", "8 200 000", "Totals-Actual Pledge", "10 000 000")      ));

		
		runNiTestCase(spec("AMP-16003-flat"), "en", acts, cor);
	}
	
	@Test
	public void testActualPledgeActualCommitmentsByPrimarySector() {
		NiReportModel cor = new NiReportModel("AMP-17196-by-pledge-primary-sector")
			.withHeaders(Arrays.asList(
					"(RAW: (startRow: 0, rowSpan: 1, totalRowSpan: 4, colStart: 0, colSpan: 13))",
					"(Pledges sectors: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 0, colSpan: 1));(Pledges Titles: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 1, colSpan: 1));(Related Projects: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 2, colSpan: 1));(Funding: (startRow: 1, rowSpan: 1, totalRowSpan: 3, colStart: 3, colSpan: 8));(Totals: (startRow: 1, rowSpan: 2, totalRowSpan: 3, colStart: 11, colSpan: 2))",
					"(1998: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 3, colSpan: 2));(2012: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 5, colSpan: 2));(2013: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 7, colSpan: 2));(2014: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 9, colSpan: 2))",
					"(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 3, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 4, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 5, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 6, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 7, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 8, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 9, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 10, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 11, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 12, colSpan: 1))"))
				.withWarnings(Arrays.asList(
					"-1: [entityId: -1, message: measure Commitment Gap not supported in NiReports]"))
				.withBody(      new ReportAreaForTests(null)
			      .withContents("Pledges sectors", "", "Pledges Titles", "", "Related Projects", "", "Funding-1998-Actual Pledge", "938 069,75", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "1 041 111,77", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2013-Actual Commitments", "5 340 000", "Funding-2014-Actual Pledge", "9 186 878,1", "Funding-2014-Actual Commitments", "3 350 000", "Totals-Actual Pledge", "12 966 059,62", "Totals-Actual Commitments", "8 690 000")
			      .withChildren(
			        new ReportAreaForTests(new AreaOwner("Pledges sectors", "112 - BASIC EDUCATION"))
			        .withContents("Pledges Titles", "", "Related Projects", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "0,44", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2013-Actual Commitments", "5 340 000", "Funding-2014-Actual Pledge", "8 545 407,34", "Funding-2014-Actual Commitments", "3 300 000", "Totals-Actual Pledge", "10 345 407,77", "Totals-Actual Commitments", "8 640 000", "Pledges sectors", "112 - BASIC EDUCATION")
			        .withChildren(
			          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2012-Actual Pledge", "0,44", "Funding-2014-Actual Pledge", "345 407,34", "Totals-Actual Pledge", "345 407,77"),
			          new ReportAreaForTests(new AreaOwner(6), "Pledges Titles", "Heavily used pledge", "Related Projects", "pledged 2, pledged education activity 1", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2013-Actual Commitments", "5 340 000", "Funding-2014-Actual Pledge", "8 200 000", "Funding-2014-Actual Commitments", "3 300 000", "Totals-Actual Pledge", "10 000 000", "Totals-Actual Commitments", "8 640 000")        ),
			        new ReportAreaForTests(new AreaOwner("Pledges sectors", "113 - SECONDARY EDUCATION")).withContents("Pledges Titles", "", "Related Projects", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "0,81", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "0", "Funding-2013-Actual Commitments", "0", "Funding-2014-Actual Pledge", "641 470,77", "Funding-2014-Actual Commitments", "0", "Totals-Actual Pledge", "641 471,58", "Totals-Actual Commitments", "0", "Pledges sectors", "113 - SECONDARY EDUCATION")
			        .withChildren(
			          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2012-Actual Pledge", "0,81", "Funding-2014-Actual Pledge", "641 470,77", "Totals-Actual Pledge", "641 471,58")        ),
			        new ReportAreaForTests(new AreaOwner("Pledges sectors", "Pledges sectors: Undefined"))
			        .withContents("Pledges Titles", "", "Related Projects", "", "Funding-1998-Actual Pledge", "938 069,75", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "1 041 110,52", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "0", "Funding-2013-Actual Commitments", "0", "Funding-2014-Actual Pledge", "0", "Funding-2014-Actual Commitments", "50 000", "Totals-Actual Pledge", "1 979 180,27", "Totals-Actual Commitments", "50 000", "Pledges sectors", "Pledges sectors: Undefined")
			        .withChildren(
			          new ReportAreaForTests(new AreaOwner(4), "Pledges Titles", "ACVL Pledge Name 2", "Related Projects", "Activity Linked With Pledge", "Funding-1998-Actual Pledge", "938 069,75", "Funding-2014-Actual Commitments", "50 000", "Totals-Actual Pledge", "938 069,75", "Totals-Actual Commitments", "50 000"),
			          new ReportAreaForTests(new AreaOwner(5), "Pledges Titles", "free text name 2", "Funding-2012-Actual Pledge", "1 041 110,52", "Totals-Actual Pledge", "1 041 110,52")        )      ));

		
		runNiTestCase(spec("AMP-17196-by-pledge-primary-sector"), "en", acts, cor);
	}
	
	@Test
    public void testActualPledgeByPledgeStatus() {
        NiReportModel cor = new NiReportModel("AMP-16003-by-pledge-status")
                .withHeaders(Arrays.asList(
                        "(RAW: (startRow: 0, rowSpan: 1, totalRowSpan: 4, colStart: 0, colSpan: 6))",
                        "(Pledge Status: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 0, colSpan: 1));(Pledges Titles: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 1, colSpan: 1));(Funding: (startRow: 1, rowSpan: 1, totalRowSpan: 3, colStart: 2, colSpan: 3));(Totals: (startRow: 1, rowSpan: 2, totalRowSpan: 3, colStart: 5, colSpan: 1))",
                        "(2012: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 2, colSpan: 1));(2013: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 3, colSpan: 1));(2014: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 4, colSpan: 1))",
                        "(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 2, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 3, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 4, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 5, colSpan: 1))"))
                    .withWarnings(Arrays.asList())
                    .withBody(      new ReportAreaForTests(null)
                      .withContents("Pledge Status", "", "Pledges Titles", "", "Funding-2012-Actual Pledge", "1 041 111,77", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2014-Actual Pledge", "9 186 878,1", "Totals-Actual Pledge", "12 966 059,62")
                      .withChildren(
                        new ReportAreaForTests(new AreaOwner("Pledge Status", "default status", 2117)).withContents("Pledges Titles", "", "Funding-2012-Actual Pledge", "0", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2014-Actual Pledge", "8 200 000", "Totals-Actual Pledge", "10 000 000", "Pledge Status", "default status")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(6), "Pledges Titles", "Heavily used pledge", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2014-Actual Pledge", "8 200 000", "Totals-Actual Pledge", "10 000 000")        ),
                        new ReportAreaForTests(new AreaOwner("Pledge Status", "second status", 2118)).withContents("Pledges Titles", "", "Funding-2012-Actual Pledge", "1,25", "Funding-2013-Actual Pledge", "0", "Funding-2014-Actual Pledge", "986 878,1", "Totals-Actual Pledge", "986 879,35", "Pledge Status", "second status")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2012-Actual Pledge", "1,25", "Funding-2014-Actual Pledge", "986 878,1", "Totals-Actual Pledge", "986 879,35")        ),
                        new ReportAreaForTests(new AreaOwner("Pledge Status", "Pledge Status: Undefined", -999999999))
                        .withContents("Pledges Titles", "", "Funding-2012-Actual Pledge", "1 041 110,52", "Funding-2013-Actual Pledge", "0", "Funding-2014-Actual Pledge", "0", "Totals-Actual Pledge", "1 979 180,27", "Pledge Status", "Pledge Status: Undefined")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(4), "Pledges Titles", "ACVL Pledge Name 2", "Totals-Actual Pledge", "938 069,75"),
                          new ReportAreaForTests(new AreaOwner(5), "Pledges Titles", "free text name 2", "Funding-2012-Actual Pledge", "1 041 110,52", "Totals-Actual Pledge", "1 041 110,52")        )      ));
        
        runNiTestCase(spec("AMP-16003-by-pledge-status"), "en", acts, cor);
	}
	
	@Test
    public void testActualPledgeDetailDatesAndContacts() {
        NiReportModel cor = new NiReportModel("AMP-21336-pledge-details-contacts-in-mondrian")
                .withHeaders(Arrays.asList(
                        "(RAW: (startRow: 0, rowSpan: 1, totalRowSpan: 4, colStart: 0, colSpan: 16))",
                        "(Pledges Titles: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 0, colSpan: 1));(Pledges Detail Date Range: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 1, colSpan: 1));(Pledges Detail End Date: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 2, colSpan: 1));(Pledges Detail Start Date: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 3, colSpan: 1));(Pledge Contact 1 - Address: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 4, colSpan: 1));(Pledge Contact 1 - Alternate Contact: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 5, colSpan: 1));(Pledge Contact 1 - Email: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 6, colSpan: 1));(Pledge Contact 1 - Ministry: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 7, colSpan: 1));(Pledge Contact 1 - Name: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 8, colSpan: 1));(Pledge Contact 1 - Telephone: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 9, colSpan: 1));(Pledge Contact 1 - Title: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 10, colSpan: 1));(Pledge Contact 2 - Title: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 11, colSpan: 1));(Funding: (startRow: 1, rowSpan: 1, totalRowSpan: 3, colStart: 12, colSpan: 3));(Totals: (startRow: 1, rowSpan: 2, totalRowSpan: 3, colStart: 15, colSpan: 1))",
                        "(2012: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 12, colSpan: 1));(2013: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 13, colSpan: 1));(2014: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 14, colSpan: 1))",
                        "(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 12, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 13, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 14, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 15, colSpan: 1))"))
                    .withWarnings(Arrays.asList())
                    .withBody(      new ReportAreaForTests(null)
                      .withContents("Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "Pledge Contact 1 - Address", "", "Pledge Contact 1 - Alternate Contact", "", "Pledge Contact 1 - Email", "", "Pledge Contact 1 - Ministry", "", "Pledge Contact 1 - Name", "", "Pledge Contact 1 - Telephone", "", "Pledge Contact 1 - Title", "", "Pledge Contact 2 - Title", "", "Funding-2012-Actual Pledge", "1 041 111,77", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2014-Actual Pledge", "9 186 878,1", "Totals-Actual Pledge", "12 966 059,62")
                      .withChildren(
                        new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Pledges Detail Date Range", "2012-06-06 - 2014-04-04, 2014-04-01 - 2014-04-16, 2014-04-18 - 2014-04-24", "Pledges Detail End Date", "2014-04-04, 2014-04-16, 2014-04-24", "Pledges Detail Start Date", "2012-06-06, 2014-04-01, 2014-04-18", "Pledge Contact 1 - Address", "Some Contact Address", "Pledge Contact 1 - Alternate Contact", "Alternative Guy first contact", "Pledge Contact 1 - Email", "contact@amp.org", "Pledge Contact 1 - Ministry", "Ministry of Pledge affairs", "Pledge Contact 1 - Name", "A Contact name", "Pledge Contact 1 - Telephone", "8976535", "Pledge Contact 1 - Title", "Dr", "Pledge Contact 2 - Title", "alternate contact title", "Funding-2012-Actual Pledge", "1,25", "Funding-2014-Actual Pledge", "986 878,1", "Totals-Actual Pledge", "986 879,35"),
                        new ReportAreaForTests(new AreaOwner(4), "Pledges Titles", "ACVL Pledge Name 2", "Pledge Contact 1 - Address", "", "Pledge Contact 1 - Alternate Contact", "", "Pledge Contact 1 - Email", "virvan@gmail.com", "Pledge Contact 1 - Ministry", "Ministry of Pledges", "Pledge Contact 1 - Name", "Vanessa Goas", "Pledge Contact 1 - Telephone", "", "Pledge Contact 1 - Title", "Dr", "Pledge Contact 2 - Title", "", "Totals-Actual Pledge", "938 069,75"),
                        new ReportAreaForTests(new AreaOwner(5), "Pledges Titles", "free text name 2", "Pledges Detail Date Range", "2012-03-02 - 2015-03-03", "Pledges Detail End Date", "2015-03-03", "Pledges Detail Start Date", "2012-03-02", "Pledge Contact 1 - Address", "", "Pledge Contact 1 - Alternate Contact", "", "Pledge Contact 1 - Email", "", "Pledge Contact 1 - Ministry", "", "Pledge Contact 1 - Name", "", "Pledge Contact 1 - Telephone", "", "Pledge Contact 1 - Title", "", "Pledge Contact 2 - Title", "", "Funding-2012-Actual Pledge", "1 041 110,52", "Totals-Actual Pledge", "1 041 110,52"),
                        new ReportAreaForTests(new AreaOwner(6), "Pledges Titles", "Heavily used pledge", "Pledges Detail Date Range", "2013-02-01 - 2014-04-29, 2014-04-08 - 2015-02-11", "Pledges Detail End Date", "2014-04-29, 2015-02-11", "Pledges Detail Start Date", "2013-02-01, 2014-04-08", "Pledge Contact 1 - Address", "", "Pledge Contact 1 - Alternate Contact", "", "Pledge Contact 1 - Email", "", "Pledge Contact 1 - Ministry", "", "Pledge Contact 1 - Name", "", "Pledge Contact 1 - Telephone", "", "Pledge Contact 1 - Title", "", "Pledge Contact 2 - Title", "", "Funding-2013-Actual Pledge", "1 800 000", "Funding-2014-Actual Pledge", "8 200 000", "Totals-Actual Pledge", "10 000 000")      ));
        
        runNiTestCase(spec("AMP-21336-pledge-details-contacts-in-mondrian"), "en", acts, cor);
    }
	
	@Test
    public void testActualPledgeByAidModality() {
	    // current result not matches old reports -> to re-check once Related Projects changed
        NiReportModel cor = new NiReportModel("AMP-17153-aid-modality-hier")
                .withHeaders(Arrays.asList(
                        "(RAW: (startRow: 0, rowSpan: 1, totalRowSpan: 4, colStart: 0, colSpan: 7))",
                        "(Pledges Aid Modality: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 0, colSpan: 1));(Pledges Titles: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 1, colSpan: 1));(Related Projects: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 2, colSpan: 1));(Funding: (startRow: 1, rowSpan: 1, totalRowSpan: 3, colStart: 3, colSpan: 3));(Totals: (startRow: 1, rowSpan: 2, totalRowSpan: 3, colStart: 6, colSpan: 1))",
                        "(2012: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 3, colSpan: 1));(2013: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 4, colSpan: 1));(2014: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 5, colSpan: 1))",
                        "(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 3, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 4, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 5, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 6, colSpan: 1))"))
                    .withWarnings(Arrays.asList())
                    .withBody(      new ReportAreaForTests(null)
                      .withContents("Pledges Aid Modality", "", "Pledges Titles", "", "Related Projects", "", "Funding-2012-Actual Pledge", "1 041 111,77", "Funding-2013-Actual Pledge", "0", "Funding-2014-Actual Pledge", "9 186 878,1", "Totals-Actual Pledge", "11 166 059,62")
                      .withChildren(
                        new ReportAreaForTests(new AreaOwner("Pledges Aid Modality", "Conferences, seminars, capacity specializations", 2099))
                        .withContents("Pledges Titles", "", "Related Projects", "", "Funding-2012-Actual Pledge", "0", "Funding-2013-Actual Pledge", "0", "Funding-2014-Actual Pledge", "8 200 000", "Totals-Actual Pledge", "9 138 069,75", "Pledges Aid Modality", "Conferences, seminars, capacity specializations")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(4), "Pledges Titles", "ACVL Pledge Name 2", "Related Projects", "Activity Linked With Pledge", "Totals-Actual Pledge", "938 069,75"),
                          new ReportAreaForTests(new AreaOwner(6), "Pledges Titles", "Heavily used pledge", "Related Projects", "pledged 2, pledged education activity 1", "Funding-2014-Actual Pledge", "8 200 000", "Totals-Actual Pledge", "8 200 000")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Aid Modality", "Development of shared analytical studies", 2107)).withContents("Pledges Titles", "", "Related Projects", "", "Funding-2012-Actual Pledge", "1,25", "Funding-2013-Actual Pledge", "0", "Funding-2014-Actual Pledge", "0", "Totals-Actual Pledge", "1,25", "Pledges Aid Modality", "Development of shared analytical studies")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2012-Actual Pledge", "1,25", "Totals-Actual Pledge", "1,25")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Aid Modality", "Diplomats and courses", 2098)).withContents("Pledges Titles", "", "Related Projects", "", "Funding-2012-Actual Pledge", "1 041 110,52", "Funding-2013-Actual Pledge", "0", "Funding-2014-Actual Pledge", "0", "Totals-Actual Pledge", "1 041 110,52", "Pledges Aid Modality", "Diplomats and courses")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(5), "Pledges Titles", "free text name 2", "Funding-2012-Actual Pledge", "1 041 110,52", "Totals-Actual Pledge", "1 041 110,52")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Aid Modality", "Interchanging models, proposals, and printed materials", 2100)).withContents("Pledges Titles", "", "Related Projects", "", "Funding-2012-Actual Pledge", "0", "Funding-2013-Actual Pledge", "0", "Funding-2014-Actual Pledge", "219 202,1", "Totals-Actual Pledge", "219 202,1", "Pledges Aid Modality", "Interchanging models, proposals, and printed materials")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2014-Actual Pledge", "219 202,1", "Totals-Actual Pledge", "219 202,1")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Aid Modality", "Sending and exchanging experts, researchers, and professors", 2106)).withContents("Pledges Titles", "", "Related Projects", "", "Funding-2012-Actual Pledge", "0", "Funding-2013-Actual Pledge", "0", "Funding-2014-Actual Pledge", "767 676", "Totals-Actual Pledge", "767 676", "Pledges Aid Modality", "Sending and exchanging experts, researchers, and professors")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2014-Actual Pledge", "767 676", "Totals-Actual Pledge", "767 676")        )      ));
        
        runNiTestCase(spec("AMP-17153-aid-modality-hier"), "en", acts, cor);
    }
	
	@Test
    public void testActualPledgeActualCommitmentsByRegions() {
        NiReportModel cor = new NiReportModel("Actual Pledge and Actual Commitments By Regions")
                .withHeaders(Arrays.asList(
                        "(RAW: (startRow: 0, rowSpan: 1, totalRowSpan: 4, colStart: 0, colSpan: 12))",
                        "(Pledges Regions: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 0, colSpan: 1));(Pledges Titles: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 1, colSpan: 1));(Funding: (startRow: 1, rowSpan: 1, totalRowSpan: 3, colStart: 2, colSpan: 8));(Totals: (startRow: 1, rowSpan: 2, totalRowSpan: 3, colStart: 10, colSpan: 2))",
                        "(1998: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 2, colSpan: 2));(2012: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 4, colSpan: 2));(2013: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 6, colSpan: 2));(2014: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 8, colSpan: 2))",
                        "(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 2, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 3, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 4, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 5, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 6, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 7, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 8, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 9, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 10, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 11, colSpan: 1))"))
                    .withWarnings(Arrays.asList())
                    .withBody(      new ReportAreaForTests(null)
                      .withContents("Pledges Regions", "", "Pledges Titles", "", "Funding-1998-Actual Pledge", "938,069,75", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "1,041,111,77", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "1,800,000", "Funding-2013-Actual Commitments", "5,340,000", "Funding-2014-Actual Pledge", "9,186,878,1", "Funding-2014-Actual Commitments", "3,350,000", "Totals-Actual Pledge", "12,966,059,62", "Totals-Actual Commitments", "8,690,000")
                      .withChildren(
                        new ReportAreaForTests(new AreaOwner("Pledges Regions", "Anenii Noi County", 9085)).withContents("Pledges Titles", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "0", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "630,000", "Funding-2013-Actual Commitments", "1,869,000", "Funding-2014-Actual Pledge", "2,870,000", "Funding-2014-Actual Commitments", "1,155,000", "Totals-Actual Pledge", "3,500,000", "Totals-Actual Commitments", "3,024,000", "Pledges Regions", "Anenii Noi County")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(6), "Pledges Titles", "Heavily used pledge", "Funding-2013-Actual Pledge", "630,000", "Funding-2013-Actual Commitments", "1,869,000", "Funding-2014-Actual Pledge", "2,870,000", "Funding-2014-Actual Commitments", "1,155,000", "Totals-Actual Pledge", "3,500,000", "Totals-Actual Commitments", "3,024,000")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Regions", "Balti County", 9086)).withContents("Pledges Titles", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "0,56", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "0", "Funding-2013-Actual Commitments", "0", "Funding-2014-Actual Pledge", "444,095,15", "Funding-2014-Actual Commitments", "0", "Totals-Actual Pledge", "444,095,71", "Totals-Actual Commitments", "0", "Pledges Regions", "Balti County")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2012-Actual Pledge", "0,56", "Funding-2014-Actual Pledge", "444,095,15", "Totals-Actual Pledge", "444,095,71")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Regions", "Cahul County", 9087)).withContents("Pledges Titles", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "0,62", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "0", "Funding-2013-Actual Commitments", "0", "Funding-2014-Actual Pledge", "493,439,05", "Funding-2014-Actual Commitments", "0", "Totals-Actual Pledge", "493,439,68", "Totals-Actual Commitments", "0", "Pledges Regions", "Cahul County")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2012-Actual Pledge", "0,62", "Funding-2014-Actual Pledge", "493,439,05", "Totals-Actual Pledge", "493,439,68")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Regions", "Lapusna County", 9096)).withContents("Pledges Titles", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "0", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "1,170,000", "Funding-2013-Actual Commitments", "3,471,000", "Funding-2014-Actual Pledge", "5,330,000", "Funding-2014-Actual Commitments", "2,145,000", "Totals-Actual Pledge", "6,500,000", "Totals-Actual Commitments", "5,616,000", "Pledges Regions", "Lapusna County")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(6), "Pledges Titles", "Heavily used pledge", "Funding-2013-Actual Pledge", "1,170,000", "Funding-2013-Actual Commitments", "3,471,000", "Funding-2014-Actual Pledge", "5,330,000", "Funding-2014-Actual Commitments", "2,145,000", "Totals-Actual Pledge", "6,500,000", "Totals-Actual Commitments", "5,616,000")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Regions", "Transnistrian Region", 9105)).withContents("Pledges Titles", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "0,06", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "0", "Funding-2013-Actual Commitments", "0", "Funding-2014-Actual Pledge", "49,343,91", "Funding-2014-Actual Commitments", "0", "Totals-Actual Pledge", "49,343,97", "Totals-Actual Commitments", "0", "Pledges Regions", "Transnistrian Region")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Funding-2012-Actual Pledge", "0,06", "Funding-2014-Actual Pledge", "49,343,91", "Totals-Actual Pledge", "49,343,97")        ),
                        new ReportAreaForTests(new AreaOwner("Pledges Regions", "Pledges Regions: Undefined", -8977))
                        .withContents("Pledges Titles", "", "Funding-1998-Actual Pledge", "938,069,75", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "1,041,110,52", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "0", "Funding-2013-Actual Commitments", "0", "Funding-2014-Actual Pledge", "0", "Funding-2014-Actual Commitments", "50,000", "Totals-Actual Pledge", "1,979,180,27", "Totals-Actual Commitments", "50,000", "Pledges Regions", "Pledges Regions: Undefined")
                        .withChildren(
                          new ReportAreaForTests(new AreaOwner(4), "Pledges Titles", "ACVL Pledge Name 2", "Funding-1998-Actual Pledge", "938,069,75", "Funding-2014-Actual Commitments", "50,000", "Totals-Actual Pledge", "938,069,75", "Totals-Actual Commitments", "50,000"),
                          new ReportAreaForTests(new AreaOwner(5), "Pledges Titles", "free text name 2", "Funding-2012-Actual Pledge", "1,041,110,52", "Totals-Actual Pledge", "1,041,110,52")        )      ));
        
        ReportSpecificationImpl spec = buildPledgeReport("Actual Pledge and Actual Commitments By Regions", 
                Arrays.asList(ColumnConstants.PLEDGES_REGIONS, ColumnConstants.PLEDGES_TITLES), 
                Arrays.asList(MeasureConstants.PLEDGES_ACTUAL_PLEDGE, MeasureConstants.PLEDGES_ACTUAL_COMMITMENTS),
                Arrays.asList(ColumnConstants.PLEDGES_REGIONS), GroupingCriteria.GROUPING_YEARLY);
        
        runNiTestCase(spec, "en", acts, cor);
    }
	
	@Test
    public void testByDonorGroup() {
	    NiReportModel cor = new NiReportModel("By Donor Group")
	            .withHeaders(Arrays.asList(
	                    "(RAW: (startRow: 0, rowSpan: 1, totalRowSpan: 4, colStart: 0, colSpan: 14))",
	                    "(Pledges Donor Group: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 0, colSpan: 1));(Pledges Titles: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 1, colSpan: 1));(Pledges sectors: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 2, colSpan: 1));(Pledges Programs: (startRow: 1, rowSpan: 3, totalRowSpan: 3, colStart: 3, colSpan: 1));(Funding: (startRow: 1, rowSpan: 1, totalRowSpan: 3, colStart: 4, colSpan: 8));(Totals: (startRow: 1, rowSpan: 2, totalRowSpan: 3, colStart: 12, colSpan: 2))",
	                    "(1998: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 4, colSpan: 2));(2012: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 6, colSpan: 2));(2013: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 8, colSpan: 2));(2014: (startRow: 2, rowSpan: 1, totalRowSpan: 2, colStart: 10, colSpan: 2))",
	                    "(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 4, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 5, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 6, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 7, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 8, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 9, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 10, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 11, colSpan: 1));(Actual Pledge: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 12, colSpan: 1));(Actual Commitments: (startRow: 3, rowSpan: 1, totalRowSpan: 1, colStart: 13, colSpan: 1))"))
	                .withWarnings(Arrays.asList())
	                .withBody(      new ReportAreaForTests(null)
	                  .withContents("Pledges Donor Group", "", "Pledges Titles", "", "Pledges sectors", "", "Pledges Programs", "", "Funding-1998-Actual Pledge", "938,069,75", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "1,041,111,77", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "1,800,000", "Funding-2013-Actual Commitments", "5,340,000", "Funding-2014-Actual Pledge", "9,186,878,1", "Funding-2014-Actual Commitments", "3,350,000", "Totals-Actual Pledge", "12,966,059,62", "Totals-Actual Commitments", "8,690,000")
	                  .withChildren(
	                    new ReportAreaForTests(new AreaOwner("Pledges Donor Group", "American", 19)).withContents("Pledges Titles", "", "Pledges sectors", "", "Pledges Programs", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "0", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "1,800,000", "Funding-2013-Actual Commitments", "5,340,000", "Funding-2014-Actual Pledge", "8,200,000", "Funding-2014-Actual Commitments", "3,300,000", "Totals-Actual Pledge", "10,000,000", "Totals-Actual Commitments", "8,640,000", "Pledges Donor Group", "American")
	                    .withChildren(
	                      new ReportAreaForTests(new AreaOwner(6), "Pledges Titles", "Heavily used pledge", "Pledges sectors", "112 - BASIC EDUCATION", "Funding-2013-Actual Pledge", "1,800,000", "Funding-2013-Actual Commitments", "5,340,000", "Funding-2014-Actual Pledge", "8,200,000", "Funding-2014-Actual Commitments", "3,300,000", "Totals-Actual Pledge", "10,000,000", "Totals-Actual Commitments", "8,640,000")        ),
	                    new ReportAreaForTests(new AreaOwner("Pledges Donor Group", "Default Group", 17))
	                    .withContents("Pledges Titles", "", "Pledges sectors", "", "Pledges Programs", "", "Funding-1998-Actual Pledge", "938,069,75", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "1,041,110,52", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "0", "Funding-2013-Actual Commitments", "0", "Funding-2014-Actual Pledge", "0", "Funding-2014-Actual Commitments", "50,000", "Totals-Actual Pledge", "1,979,180,27", "Totals-Actual Commitments", "50,000", "Pledges Donor Group", "Default Group")
	                    .withChildren(
	                      new ReportAreaForTests(new AreaOwner(4), "Pledges Titles", "ACVL Pledge Name 2", "Pledges Programs", "Subprogram p1.b", "Funding-1998-Actual Pledge", "938,069,75", "Funding-2014-Actual Commitments", "50,000", "Totals-Actual Pledge", "938,069,75", "Totals-Actual Commitments", "50,000"),
	                      new ReportAreaForTests(new AreaOwner(5), "Pledges Titles", "free text name 2", "Funding-2012-Actual Pledge", "1,041,110,52", "Totals-Actual Pledge", "1,041,110,52")        ),
	                    new ReportAreaForTests(new AreaOwner("Pledges Donor Group", "European", 18)).withContents("Pledges Titles", "", "Pledges sectors", "", "Pledges Programs", "", "Funding-1998-Actual Pledge", "0", "Funding-1998-Actual Commitments", "0", "Funding-2012-Actual Pledge", "1,25", "Funding-2012-Actual Commitments", "0", "Funding-2013-Actual Pledge", "0", "Funding-2013-Actual Commitments", "0", "Funding-2014-Actual Pledge", "986,878,1", "Funding-2014-Actual Commitments", "0", "Totals-Actual Pledge", "986,879,35", "Totals-Actual Commitments", "0", "Pledges Donor Group", "European")
	                    .withChildren(
	                      new ReportAreaForTests(new AreaOwner(3), "Pledges Titles", "Test pledge 1", "Pledges sectors", "112 - BASIC EDUCATION, 113 - SECONDARY EDUCATION", "Pledges Programs", "Subprogram p1.b", "Funding-2012-Actual Pledge", "1,25", "Funding-2014-Actual Pledge", "986,878,1", "Totals-Actual Pledge", "986,879,35")        )      ));
	    
	    ReportSpecificationImpl spec = buildPledgeReport("By Donor Group", 
                Arrays.asList(ColumnConstants.PLEDGES_DONOR_GROUP, ColumnConstants.PLEDGES_TITLES, ColumnConstants.PLEDGES_SECTORS, ColumnConstants.PLEDGES_PROGRAMS), 
                Arrays.asList(MeasureConstants.PLEDGES_ACTUAL_PLEDGE, MeasureConstants.PLEDGES_ACTUAL_COMMITMENTS),
                Arrays.asList(ColumnConstants.PLEDGES_DONOR_GROUP), GroupingCriteria.GROUPING_YEARLY);
        
        runNiTestCase(spec, "en", acts, cor);
    }
	
}
