/**
 * 
 */
package org.dgfoundation.amp.ar.amp211;

import java.util.Arrays;

import org.dgfoundation.amp.mondrian.ReportingTestCase;
import org.dgfoundation.amp.mondrian.ReportAreaForTests;
import org.junit.Test;

public class PledgeReportsTests211 extends ReportingTestCase {
	
	public PledgeReportsTests211() {
		super("pledge reports mondrian tests 211");
	}
	
	
	@Test
	public void testAMP_21336_detail_dates_and_contacts() {
		ReportAreaForTests correctReportEn = new ReportAreaForTests()
	    .withContents("Pledges Titles", "Report Totals", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "Pledge Contact 1 - Address", "", "Pledge Contact 1 - Alternate Contact", "", "Pledge Contact 1 - Email", "", "Pledge Contact 1 - Ministry", "", "Pledge Contact 1 - Name", "", "Pledge Contact 1 - Telephone", "", "Pledge Contact 1 - Title", "", "Pledge Contact 2 - Title", "", "2012-Actual Pledge", "1 041 111,77", "2013-Actual Pledge", "1 800 000", "2014-Actual Pledge", "9 186 878,1", "Total Measures-Actual Pledge", "12 966 059,62")
	    .withChildren(
	      new ReportAreaForTests().withContents("Pledges Titles", "Test pledge 1", "Pledges Detail Date Range", "01/04/2014 - 16/04/2014, 06/06/2012 - 04/04/2014, 18/04/2014 - 24/04/2014", "Pledges Detail End Date", "04/04/2014, 16/04/2014, 24/04/2014", "Pledges Detail Start Date", "01/04/2014, 06/06/2012, 18/04/2014", "Pledge Contact 1 - Address", "Some Contact Address", "Pledge Contact 1 - Alternate Contact", "Alternative Guy first contact", "Pledge Contact 1 - Email", "contact@amp.org", "Pledge Contact 1 - Ministry", "Ministry of Pledge affairs", "Pledge Contact 1 - Name", "A Contact name", "Pledge Contact 1 - Telephone", "8976535", "Pledge Contact 1 - Title", "Dr", "Pledge Contact 2 - Title", "alternate contact title", "2012-Actual Pledge", "1,25", "2013-Actual Pledge", "", "2014-Actual Pledge", "986 878,1", "Total Measures-Actual Pledge", "986 879,35"),
	      new ReportAreaForTests().withContents("Pledges Titles", "ACVL Pledge Name 2", "Pledges Detail Date Range", "02/01/1998 - Undefined", "Pledges Detail End Date", "", "Pledges Detail Start Date", "02/01/1998", "Pledge Contact 1 - Address", "", "Pledge Contact 1 - Alternate Contact", "", "Pledge Contact 1 - Email", "virvan@gmail.com", "Pledge Contact 1 - Ministry", "Ministry of Pledges", "Pledge Contact 1 - Name", "Vanessa Goas", "Pledge Contact 1 - Telephone", "", "Pledge Contact 1 - Title", "Dr", "Pledge Contact 2 - Title", "", "2012-Actual Pledge", "", "2013-Actual Pledge", "", "2014-Actual Pledge", "", "Total Measures-Actual Pledge", "938 069,75"),
	      new ReportAreaForTests().withContents("Pledges Titles", "free text name 2", "Pledges Detail Date Range", "02/03/2012 - 03/03/2015", "Pledges Detail End Date", "03/03/2015", "Pledges Detail Start Date", "02/03/2012", "Pledge Contact 1 - Address", "", "Pledge Contact 1 - Alternate Contact", "", "Pledge Contact 1 - Email", "", "Pledge Contact 1 - Ministry", "", "Pledge Contact 1 - Name", "", "Pledge Contact 1 - Telephone", "", "Pledge Contact 1 - Title", "", "Pledge Contact 2 - Title", "", "2012-Actual Pledge", "1 041 110,52", "2013-Actual Pledge", "", "2014-Actual Pledge", "", "Total Measures-Actual Pledge", "1 041 110,52"),
	      new ReportAreaForTests().withContents("Pledges Titles", "Heavily used pledge", "Pledges Detail Date Range", "01/02/2013 - 29/04/2014, 08/04/2014 - 11/02/2015", "Pledges Detail End Date", "11/02/2015, 29/04/2014", "Pledges Detail Start Date", "01/02/2013, 08/04/2014", "Pledge Contact 1 - Address", "", "Pledge Contact 1 - Alternate Contact", "", "Pledge Contact 1 - Email", "", "Pledge Contact 1 - Ministry", "", "Pledge Contact 1 - Name", "", "Pledge Contact 1 - Telephone", "", "Pledge Contact 1 - Title", "", "Pledge Contact 2 - Title", "", "2012-Actual Pledge", "", "2013-Actual Pledge", "1 800 000", "2014-Actual Pledge", "8 200 000", "Total Measures-Actual Pledge", "10 000 000"));
		
		runMondrianTestCase("AMP-21336-pledge-details-contacts-in-mondrian",
			Arrays.asList("Test pledge 1", "ACVL Pledge Name 2", "free text name 2", "Heavily used pledge"), // pledge report -> pledge names 
			correctReportEn, "en");
	}
	
	@Test
	public void testAMP_21901_undefined_type_of_assistance_with_pledge_dates() {
		ReportAreaForTests correctReportEn = new ReportAreaForTests()
	    .withContents("Pledges Donor Group", "Report Totals", "Pledge Status", "", "Pledges Type Of Assistance", "", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "1 800 000", "2013-Planned Commitments", "0", "2013-Actual Commitments", "2 670 000", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "-870 000", "2014-Actual Pledge", "8 200 000", "2014-Planned Commitments", "65 000 000", "2014-Actual Commitments", "3 350 000", "2014-Actual Disbursements", "450 000", "2014-Commitment Gap", "4 850 000", "Total Measures-Actual Pledge", "10 938 069,75", "Total Measures-Planned Commitments", "65 000 000", "Total Measures-Actual Commitments", "6 020 000", "Total Measures-Actual Disbursements", "450 000", "Total Measures-Commitment Gap", "4 918 069,75")
	    .withChildren(
	      new ReportAreaForTests()    .withContents("Pledges Donor Group", "Default Group Totals", "Pledge Status", "", "Pledges Type Of Assistance", "", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "0", "2013-Planned Commitments", "0", "2013-Actual Commitments", "0", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "0", "2014-Actual Pledge", "0", "2014-Planned Commitments", "0", "2014-Actual Commitments", "50 000", "2014-Actual Disbursements", "0", "2014-Commitment Gap", "-50 000", "Total Measures-Actual Pledge", "938 069,75", "Total Measures-Planned Commitments", "0", "Total Measures-Actual Commitments", "50 000", "Total Measures-Actual Disbursements", "0", "Total Measures-Commitment Gap", "888 069,75")
	      .withChildren(
	        new ReportAreaForTests()
	              .withContents("Pledges Donor Group", "", "Pledge Status", "Pledge Status: Undefined Totals", "Pledges Type Of Assistance", "", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "0", "2013-Planned Commitments", "0", "2013-Actual Commitments", "0", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "0", "2014-Actual Pledge", "0", "2014-Planned Commitments", "0", "2014-Actual Commitments", "50 000", "2014-Actual Disbursements", "0", "2014-Commitment Gap", "-50 000", "Total Measures-Actual Pledge", "938 069,75", "Total Measures-Planned Commitments", "0", "Total Measures-Actual Commitments", "50 000", "Total Measures-Actual Disbursements", "0", "Total Measures-Commitment Gap", "888 069,75")
	        .withChildren(
	          new ReportAreaForTests()        .withContents("Pledges Donor Group", "", "Pledge Status", "", "Pledges Type Of Assistance", "default type of assistance Totals", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "0", "2013-Planned Commitments", "0", "2013-Actual Commitments", "0", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "0", "2014-Actual Pledge", "0", "2014-Planned Commitments", "0", "2014-Actual Commitments", "0", "2014-Actual Disbursements", "0", "2014-Commitment Gap", "0", "Total Measures-Actual Pledge", "938 069,75", "Total Measures-Planned Commitments", "0", "Total Measures-Actual Commitments", "0", "Total Measures-Actual Disbursements", "0", "Total Measures-Commitment Gap", "938 069,75")
	          .withChildren(
	            new ReportAreaForTests()          .withContents("Pledges Donor Group", "Default Group", "Pledge Status", "Pledge Status: Undefined", "Pledges Type Of Assistance", "default type of assistance", "Pledges Titles", "ACVL Pledge Name 2", "Pledges Detail Date Range", "02/01/1998 - Undefined", "Pledges Detail End Date", "", "Pledges Detail Start Date", "02/01/1998", "2013-Actual Pledge", "", "2013-Planned Commitments", "", "2013-Actual Commitments", "", "2013-Actual Disbursements", "", "2013-Commitment Gap", "", "2014-Actual Pledge", "", "2014-Planned Commitments", "", "2014-Actual Commitments", "", "2014-Actual Disbursements", "", "2014-Commitment Gap", "", "Total Measures-Actual Pledge", "938 069,75", "Total Measures-Planned Commitments", "0", "Total Measures-Actual Commitments", "0", "Total Measures-Actual Disbursements", "0", "Total Measures-Commitment Gap", "938 069,75")        ),
	          new ReportAreaForTests()        .withContents("Pledges Donor Group", "", "Pledge Status", "", "Pledges Type Of Assistance", "Pledges Type Of Assistance: Undefined Totals", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "0", "2013-Planned Commitments", "0", "2013-Actual Commitments", "0", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "0", "2014-Actual Pledge", "0", "2014-Planned Commitments", "0", "2014-Actual Commitments", "50 000", "2014-Actual Disbursements", "0", "2014-Commitment Gap", "-50 000", "Total Measures-Actual Pledge", "0", "Total Measures-Planned Commitments", "0", "Total Measures-Actual Commitments", "50 000", "Total Measures-Actual Disbursements", "0", "Total Measures-Commitment Gap", "-50 000")
	          .withChildren(
	            new ReportAreaForTests()          .withContents("Pledges Donor Group", "", "Pledge Status", "", "Pledges Type Of Assistance", "Pledges Type Of Assistance: Undefined", "Pledges Titles", "ACVL Pledge Name 2", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "", "2013-Planned Commitments", "", "2013-Actual Commitments", "", "2013-Actual Disbursements", "", "2013-Commitment Gap", "", "2014-Actual Pledge", "", "2014-Planned Commitments", "", "2014-Actual Commitments", "50 000", "2014-Actual Disbursements", "", "2014-Commitment Gap", "-50 000", "Total Measures-Actual Pledge", "0", "Total Measures-Planned Commitments", "0", "Total Measures-Actual Commitments", "50 000", "Total Measures-Actual Disbursements", "0", "Total Measures-Commitment Gap", "-50 000")        )      )    ),
	      new ReportAreaForTests()    .withContents("Pledges Donor Group", "American Totals", "Pledge Status", "", "Pledges Type Of Assistance", "", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "1 800 000", "2013-Planned Commitments", "0", "2013-Actual Commitments", "2 670 000", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "-870 000", "2014-Actual Pledge", "8 200 000", "2014-Planned Commitments", "65 000 000", "2014-Actual Commitments", "3 300 000", "2014-Actual Disbursements", "450 000", "2014-Commitment Gap", "4 900 000", "Total Measures-Actual Pledge", "10 000 000", "Total Measures-Planned Commitments", "65 000 000", "Total Measures-Actual Commitments", "5 970 000", "Total Measures-Actual Disbursements", "450 000", "Total Measures-Commitment Gap", "4 030 000")
	      .withChildren(
	        new ReportAreaForTests()
	              .withContents("Pledges Donor Group", "", "Pledge Status", "default status Totals", "Pledges Type Of Assistance", "", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "1 800 000", "2013-Planned Commitments", "0", "2013-Actual Commitments", "2 670 000", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "-870 000", "2014-Actual Pledge", "8 200 000", "2014-Planned Commitments", "65 000 000", "2014-Actual Commitments", "3 300 000", "2014-Actual Disbursements", "450 000", "2014-Commitment Gap", "4 900 000", "Total Measures-Actual Pledge", "10 000 000", "Total Measures-Planned Commitments", "65 000 000", "Total Measures-Actual Commitments", "5 970 000", "Total Measures-Actual Disbursements", "450 000", "Total Measures-Commitment Gap", "4 030 000")
	        .withChildren(
	          new ReportAreaForTests()        .withContents("Pledges Donor Group", "", "Pledge Status", "", "Pledges Type Of Assistance", "default type of assistance Totals", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "0", "2013-Planned Commitments", "0", "2013-Actual Commitments", "0", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "0", "2014-Actual Pledge", "8 200 000", "2014-Planned Commitments", "0", "2014-Actual Commitments", "0", "2014-Actual Disbursements", "0", "2014-Commitment Gap", "8 200 000", "Total Measures-Actual Pledge", "8 200 000", "Total Measures-Planned Commitments", "0", "Total Measures-Actual Commitments", "0", "Total Measures-Actual Disbursements", "0", "Total Measures-Commitment Gap", "8 200 000")
	          .withChildren(
	            new ReportAreaForTests()          .withContents("Pledges Donor Group", "American", "Pledge Status", "default status", "Pledges Type Of Assistance", "default type of assistance", "Pledges Titles", "Heavily used pledge", "Pledges Detail Date Range", "08/04/2014 - 11/02/2015", "Pledges Detail End Date", "11/02/2015", "Pledges Detail Start Date", "08/04/2014", "2013-Actual Pledge", "", "2013-Planned Commitments", "", "2013-Actual Commitments", "", "2013-Actual Disbursements", "", "2013-Commitment Gap", "", "2014-Actual Pledge", "8 200 000", "2014-Planned Commitments", "", "2014-Actual Commitments", "", "2014-Actual Disbursements", "", "2014-Commitment Gap", "8 200 000", "Total Measures-Actual Pledge", "8 200 000", "Total Measures-Planned Commitments", "0", "Total Measures-Actual Commitments", "0", "Total Measures-Actual Disbursements", "0", "Total Measures-Commitment Gap", "8 200 000")        ),
	          new ReportAreaForTests()        .withContents("Pledges Donor Group", "", "Pledge Status", "", "Pledges Type Of Assistance", "Pledges Type Of Assistance: Undefined Totals", "Pledges Titles", "", "Pledges Detail Date Range", "", "Pledges Detail End Date", "", "Pledges Detail Start Date", "", "2013-Actual Pledge", "1 800 000", "2013-Planned Commitments", "0", "2013-Actual Commitments", "2 670 000", "2013-Actual Disbursements", "0", "2013-Commitment Gap", "-870 000", "2014-Actual Pledge", "0", "2014-Planned Commitments", "65 000 000", "2014-Actual Commitments", "3 300 000", "2014-Actual Disbursements", "450 000", "2014-Commitment Gap", "-3 300 000", "Total Measures-Actual Pledge", "1 800 000", "Total Measures-Planned Commitments", "65 000 000", "Total Measures-Actual Commitments", "5 970 000", "Total Measures-Actual Disbursements", "450 000", "Total Measures-Commitment Gap", "-4 170 000")
	          .withChildren(
	            new ReportAreaForTests()          .withContents("Pledges Donor Group", "", "Pledge Status", "", "Pledges Type Of Assistance", "Pledges Type Of Assistance: Undefined", "Pledges Titles", "Heavily used pledge", "Pledges Detail Date Range", "01/02/2013 - 29/04/2014", "Pledges Detail End Date", "29/04/2014", "Pledges Detail Start Date", "01/02/2013", "2013-Actual Pledge", "1 800 000", "2013-Planned Commitments", "", "2013-Actual Commitments", "2 670 000", "2013-Actual Disbursements", "", "2013-Commitment Gap", "-870 000", "2014-Actual Pledge", "", "2014-Planned Commitments", "65 000 000", "2014-Actual Commitments", "3 300 000", "2014-Actual Disbursements", "450 000", "2014-Commitment Gap", "-3 300 000", "Total Measures-Actual Pledge", "1 800 000", "Total Measures-Planned Commitments", "65 000 000", "Total Measures-Actual Commitments", "5 970 000", "Total Measures-Actual Disbursements", "450 000", "Total Measures-Commitment Gap", "-4 170 000")        )      )    )  );
		
		runMondrianTestCase("AMP-21901-undefined-type-of-assistance",
			Arrays.asList("ACVL Pledge Name 2", "Heavily used pledge"), 
			correctReportEn, "en");
	}

}
