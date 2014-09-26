package org.digijava.kernel.ampapi.mondrian.util;

import org.digijava.module.aim.helper.Constants;

/**
 * 
 * @author Nadejda Mandrescu
 * @since July 2014 - Mondrian from-scratch reimplementation
 */
public final class MoConstants {

	//AMP Cubes
	public static String CONNECTION_DS = "jdbc:mondrian:Datasource=" + Constants.MONETDB_JNDI_ALIAS;
	public static final String SCHEMA_PROCESSOR = "DynamicSchemaProcessor=" + AmpMondrianSchemaProcessor.class.getCanonicalName();
	public static final String SCHEMA_PATH = "WEB-INF/src/org/digijava/kernel/ampapi/mondrian/util/AMP.xml".replace("/", System.getProperty("file.separator"));
	public static final String FUNDING_CUBE_NAME = "Donor Funding";
	public static final String DEFAULT_CUBE_NAME = FUNDING_CUBE_NAME;
	
	//Query
	public static final String QUERY_NAME_KEY = "QUERY_NAME_KEY";
	public static final String DEFAULT_QUERY_NAME = "Anonymous";
	public static final String COLUMNS = "COLUMNS";
	public static final String ROWS = "ROWS";
	public static final String FILTER = "FILTER";
	public static final String MEASURE = "Measure";
	public static final String MEASURES = "Measures";
	public static final String MEMBERS = "Members";
	public static final String MEMBER = "MEMBER";
	public static final String CURRENT_MEMBER = "CurrentMember";
	public static final String FIRST_CHILD = "FirstChild";
	public static final String LAST_CHILD = "LastChild";
	public static final String PROPERTIES = "Properties";
	public static final String PROP_FORMAT_STRING = "FORMAT_STRING";
	public static final String MEMBER_NAME = "name";
	public static final String FUNC_CROSS_JOIN = "CrossJoin";
	public static final String FUNC_CROSS_JOIN_FORMAT = "CrossJoin(%s, %s)";
	public static final String FUNC_HIERARCHIZE = "Hierarchize";
	public static final String FUNC_INTERSECT = "Intersect";
	public static final String FUNC_UNION = "Union";
	public static final String FUNC_UNION_FORMAT = "Union(%s, %s)";
	public static final String FUNC_ORDER = "Order";
	public static final String FUNC_FILTER = "Filter";
	public static final String FUNC_CAST = "Cast";

	//Dimensions
	public static final String ACTIVITY_STATUS = "Activity Status";
	public static final String MODALITIES = "Modalities";
	public static final String TYPE_OF_COOPERATION = "Type of Cooperation";
	public static final String TYPE_OF_IMPLEMENTATION = "Type of Implementation";
	public static final String PROCUREMENT_SYSTEM = "Procurement System";
	public static final String FUNDING_STATUS = "Funding Status";
	public static final String MODE_OF_PAYMENT = "Mode of Payment";
	public static final String FINANCING_INSTRUMENT = "Financing Instrument";
	public static final String TYPE_OF_ASSISTANCE = "Type Of Assistance";
	public static final String ACTIVITY_LONG_TEXTS = "Activity Long Texts";
	public static final String ACTIVITY_TEXTS = "Activity Texts";
	public static final String ACTIVITY_FIXED_TEXTS = "Activity Fixed Texts";
	public static final String ACTIVITY_CURRENCY_AMOUNTS = "Activity Currency Amounts";
	public static final String DATES = "Dates";
	public static final String ACTIVITY_DATES = "Activity Dates";
	public static final String LOCATION = "Location"; 
	public static final String PRIMARY_SECTOR = "Primary Sector";
	public static final String SECONDARY_SECTOR = "Secondary Sector";
	public static final String TERTIARY_SECTOR = "Tertiary Sector";
	public static final String PRIMARY_PROGRAMS = "Primary Program";
	public static final String SECONDARY_PROGRAMS = "Secondary Program";
	public static final String TERTIARY_PROGRAMS = "Tertiary Program";
	public static final String NATIONAL_OBJECTIVES = "National Objectives";
	public static final String DONOR_AGENCY = "Donor Agency";
	public static final String IMPLEMENTING_AGENCY = "Implementing Agency";
	public static final String EXECUTING_AGENCY = "Executing Agency";
	public static final String BENEFICIARY_AGENCY = "Beneficiary Agency";
	public static final String RESPONSIBLE_AGENCY = "Responsible Organization";
	//Hierarchies
	public static final String H_ORIG_COMPLETION_DATE = "Original Completion Date";
	public static final String H_FINAL_DATE_FOR_CONTRACTING = "Final Date for Contracting";
	public static final String H_FINAL_DATE_FOR_DISBURSEMENTS = "Final Date for Disbursements";
	public static final String H_PROPOSED_START_DATE = "Proposed Start Date";
	public static final String H_ACTUAL_START_DATE = "Actual Start Date";
	public static final String H_PROPOSED_APPROVAL_DATE = "Proposed Approval Date";
	public static final String H_ACTUAL_APPROVAL_DATE = "Actual Approval Date";
	public static final String H_ACTUAL_COMPLETION_DATE = "Actual Completion Date";	
	public static final String H_PROPOSED_COMPLETION_DATE = "Proposed Completion Date";
	public static final String H_ACTIVITY_CREATED_ON = "Activity Created On";
	public static final String H_ACTIVITY_UPDATED_ON = "Activity Updated On";
	public static final String H_ACTIVITY_ID = "Activity Id";
	public static final String H_GOVERNMENT_AGREEMENT_NUMBER = "Government Agreement Number";
	public static final String H_BUDGET_CODE_PROJECT_ID = "Budget Code Project Id";
	public static final String H_DRAFT = "Draft";
	public static final String H_FY = "FY";
	public static final String H_VOTE = "Vote";
	public static final String H_SUB_VOTE = "Sub-Vote";
	public static final String H_PROJECT_CODE = "Project Code";
	public static final String H_MINISTRY_CODE = "Ministry Code";
	public static final String H_CRIS_NUMBER = "Cris Number";
	public static final String H_PROJECT_IMPLEMENTING_UNIT = "Project Implementing Unit";
	public static final String H_PROJECT_APPROVED_BY_IMAC = "Project has been approved by IMAC";
	public static final String H_GOVERNMENT_IN_STEERING_COMMITTEE = "Government is member of project steering committee";
	public static final String H_PROJECT_ON_BUDGET = "Project is on budget";
	public static final String H_PROJECT_ON_PARLIAMENT = "Project is on parliament";
	public static final String H_PROJECT_DISBURSES_DIRECTLY_INTO_GOVERNMENT_TREASURY = "Project disburses directly into the Government single treasury account";
	public static final String H_PROJECT_USES_NATIONAL_FIN_MNG_SYSTEMS = "Project uses national financial management systems";
	public static final String H_PROJECT_USES_NATIONAL_PROCURMENT_SYSTEMS = "Project uses national procurement systems";
	public static final String H_PROJECT_USES_NATIONAL_AUDIT_SYSTEMS = "Project uses national audit systems";
	public static final String H_PROPOSED_PROJECT_LIFE = "Proposed Project Life";
	public static final String H_DATES = "Dates";
	public static final String H_YEAR = "Year";
	public static final String H_QUARTER = "Quarter";
	public static final String H_MONTH = "Month";
	public static final String H_ORGANIZATIONS = "Organization Hierarchy";
	public static final String H_ORG_TYPE_NAME = "Organization Type Name";
	public static final String H_ORG_GROUP_NAME = "Organization Group Name";
	public static final String H_ORG_NAME = "Organization Name";
	public static final String H_LOCATIONS = "Locations";
	public static final String H_REGIONS = "Region Name";
	public static final String H_LEVEL_0_SECTOR = "Level 0 Sector";
	public static final String H_LEVEL_1_SECTOR = "Level 1 Sector";
	public static final String H_LEVEL_2_SECTOR = "Level 2 Sector";
	public static final String H_PROJECT_TITLE = "Project Title";
	public static final String H_APPROVAL_STATUS = "Approval Status";
	public static final String H_AMP_ID = "AMP ID";
	public static final String H_TEAM = "Team";
	public static final String H_ACTIVITY_CREATED_BY = "Activity Created By";
	public static final String H_ACTIVITY_UPDATED_BY = "Activity Updated By";
	public static final String H_ACTIVITY_APPROVED_BY ="Activity Approved By";
	public static final String H_GOVERNMENT_APPROVAL_PROCEDURES = "Government Approval Procedures";
	public static final String H_JOINT_CRITERIA = "Joint Criteria";
	public static final String H_INDIRECT_ON_BUDGET = "Indirect On Budget";
	private static final String H_CATEGORY_NAME = "Category Name";
	public static final String H_ACTIVITY_STATUS = H_CATEGORY_NAME;
	public static final String H_MODALITIES = H_CATEGORY_NAME;
	public static final String H_TYPE_OF_COOPERATION = H_CATEGORY_NAME;
	public static final String H_TYPE_OF_IMPLEMENTATION = H_CATEGORY_NAME;
	public static final String H_PROCUREMENT_SYSTEM = H_CATEGORY_NAME;
	public static final String H_FUNDING_STATUS = H_CATEGORY_NAME;
	public static final String H_MODE_OF_PAYMENT = H_CATEGORY_NAME;
	public static final String H_FINANCING_INSTRUMENT = H_CATEGORY_NAME;
	public static final String H_TYPE_OF_ASSISTANCE = H_CATEGORY_NAME;
	public static final String H_PROJECT_DESCRIPTION = "Project Description";
	public static final String H_OBJECTIVE = "Objective";
	public static final String H_RESULTS = "Results";
	public static final String H_PURPOSE = "Purpose";
	public static final String H_PROJECT_COMMENTS = "Project Comments";
	public static final String H_PROJECT_IMPACT = "Project Impact";
	public static final String H_EQUAL_OPPORTUNITY = "Equal Opportunity";
	public static final String H_ENVIRONMENT = "Environment";
	public static final String H_MINORITIES = "Minorities";
	public static final String H_PROGRAM_DESCRIPTION = "Program Description";
	//Attributes/Levels
	public static final String ATTR_PROJECT_TITLE = "Project Title";
	public static final String ATTR_APPROVAL_STATUS = "Approval Status";
	public static final String ATTR_AMP_ID = "AMP ID";
	public static final String ATTR_TEAM = "Team";
	public static final String ATTR_ACTIVITY_CREATED_BY = "Activity Created By";
	public static final String ATTR_ACTIVITY_UPDATED_BY = "Activity Updated By";
	public static final String ATTR_ACTIVITY_APPROVED_BY ="Activity Approved By";
	public static final String ATTR_GOVERNMENT_APPROVAL_PROCEDURES = "Government Approval Procedures";
	public static final String ATTR_JOINT_CRITERIA = "Joint Criteria";
	public static final String ATTR_INDIRECT_ON_BUDGET = "Indirect On Budget";
	private static final String ATTR_CATEGORY_NAME = "Category Name";
	public static final String ATTR_ACTIVITY_STATUS = ATTR_CATEGORY_NAME;
	public static final String ATTR_MODALITIES = ATTR_CATEGORY_NAME;
	public static final String ATTR_TYPE_OF_COOPERATION = ATTR_CATEGORY_NAME;
	public static final String ATTR_TYPE_OF_IMPLEMENTATION = ATTR_CATEGORY_NAME;
	public static final String ATTR_PROCUREMENT_SYSTEM = ATTR_CATEGORY_NAME;
	public static final String ATTR_FUNDING_STATUS = ATTR_CATEGORY_NAME;
	public static final String ATTR_MODE_OF_PAYMENT = ATTR_CATEGORY_NAME;
	public static final String ATTR_FINANCING_INSTRUMENT = ATTR_CATEGORY_NAME;
	public static final String ATTR_TYPE_OF_ASSISTANCE = ATTR_CATEGORY_NAME;
	public static final String ATTR_PROJECT_DESCRIPTION = "Project Description";
	public static final String ATTR_OBJECTIVE = "Objective";
	public static final String ATTR_RESULTS = "Results";
	public static final String ATTR_PURPOSE = "Purpose";
	public static final String ATTR_PROJECT_COMMENTS = "Project Comments";
	public static final String ATTR_PROJECT_IMPACT = "Project Impact";
	public static final String ATTR_EQUAL_OPPORTUNITY = "Equal Opportunity";
	public static final String ATTR_ENVIRONMENT = "Environment";
	public static final String ATTR_MINORITIES = "Minorities";
	public static final String ATTR_PROGRAM_DESCRIPTION = "Program Description";
	public static final String ATTR_ORIG_COMPLETION_DATE = "Original Completion Date";
	public static final String ATTR_FINAL_DATE_FOR_CONTRACTING = "Final Date for Contracting";
	public static final String ATTR_FINAL_DATE_FOR_DISBURSEMENTS = "Final Date for Disbursements";
	public static final String ATTR_PROPOSED_START_DATE = "Proposed Start Date";
	public static final String ATTR_ACTUAL_START_DATE = "Actual Start Date";
	public static final String ATTR_PROPOSED_APPROVAL_DATE = "Proposed Approval Date";
	public static final String ATTR_ACTUAL_APPROVAL_DATE = "Actual Approval Date";
	public static final String ATTR_ACTUAL_COMPLETION_DATE ="Actual Completion Date";
	public static final String ATTR_PROPOSED_COMPLETION_DATE = "Proposed Completion Date";
	public static final String ATTR_ACTIVITY_CREATED_ON = "Activity Created On";
	public static final String ATTR_ACTIVITY_UPDATED_ON = "Activity Updated On";
	public static final String ATTR_ACTIVITY_ID = "Activity Id";
	public static final String ATTR_GOVERNMENT_AGREEMENT_NUMBER = "Government Agreement Number";
	public static final String ATTR_BUDGET_CODE_PROJECT_ID = "Budget Code Project Id";
	public static final String ATTR_DRAFT = "Draft";
	public static final String ATTR_FY = "FY";
	public static final String ATTR_VOTE = "Vote";
	public static final String ATTR_SUB_VOTE = "Sub-Vote";
	public static final String ATTR_PROJECT_CODE = "Project Code";
	public static final String ATTR_MINISTRY_CODE = "Ministry Code";
	public static final String ATTR_CRIS_NUMBER = "Cris Number";
	public static final String ATTR_PROJECT_IMPLEMENTING_UNIT = "Project Implementing Unit";
	public static final String ATTR_PROJECT_APPROVED_BY_IMAC = "Project has been approved by IMAC";
	public static final String ATTR_GOVERNMENT_IN_STEERING_COMMITTEE = "Government is member of project steering committee";
	public static final String ATTR_PROJECT_ON_BUDGET = "Project is on budget";
	public static final String ATTR_PROJECT_ON_PARLIAMENT = "Project is on parliament";
	public static final String ATTR_PROJECT_DISBURSES_DIRECTLY_INTO_GOVERNMENT_TREASURY = "Project disburses directly into the Government single treasury account";
	public static final String ATTR_PROJECT_USES_NATIONAL_FIN_MNG_SYSTEMS = "Project uses national financial management systems";
	public static final String ATTR_PROJECT_USES_NATIONAL_PROCURMENT_SYSTEMS = "Project uses national procurement systems";
	public static final String ATTR_PROJECT_USES_NATIONAL_AUDIT_SYSTEMS = "Project uses national audit systems";
	public static final String ATTR_PROPOSED_PROJECT_LIFE = "Proposed Project Life";
	public static final String ATTR_YEAR = "Year";
	public static final String ATTR_QUARTER = "Quarter";
	public static final String ATTR_MONTH = "Month";
	public static final String ATTR_DATE = "Date";
	public static final String ATTR_DAY = "Day";
	public static final String ATTR_ALL_DATES = "All Periods";
	public static final String ATTR_COUNTRY_NAME = "Country Name";
	public static final String ATTR_REGION_NAME = "Region Name";
	public static final String ATTR_ZONE_NAME = "Zone Name";
	public static final String ATTR_DISTRICT_NAME = "District Name";
	public static final String ATTR_LOCATION_NAME = "Location";
	public static final String ATTR_LEVEL_0_SECTOR_NAME = "Level 0 Sector";
	public static final String ATTR_LEVEL_1_SECTOR_NAME = "Level 1 Sector";
	public static final String ATTR_LEVEL_2_SECTOR_NAME = "Level 2 Sector";
	public static final String ATTR_ORG_TYPE_NAME = "Organization Type Name";
	public static final String ATTR_ORG_GROUP_NAME = "Organization Group Name";
	public static final String ATTR_ORG_NAME = "Organization Name";
	public static final String ATTR_ORG_CODE = "Organization Code";
	public static final String ATTR_PROGRAM_LEVEL_0_NAME = "Program Level 0 Name";
	public static final String ATTR_PROGRAM_LEVEL_1_NAME = "Program Level 1 Name";
	public static final String ATTR_PROGRAM_LEVEL_2_NAME = "Program Level 2 Name";
	public static final String ATTR_PROGRAM_LEVEL_3_NAME = "Program Level 3 Name";
	public static final String ATTR_PROGRAM_LEVEL_4_NAME = "Program Level 4 Name";
	public static final String ATTR_PROGRAM_LEVEL_5_NAME = "Program Level 5 Name";
	public static final String ATTR_PROGRAM_LEVEL_6_NAME = "Program Level 6 Name";
	public static final String ATTR_PROGRAM_LEVEL_7_NAME = "Program Level 7 Name";
	public static final String ATTR_PROGRAM_LEVEL_8_NAME = "Program Level 8 Name";
	public static final String ATTR_PROPOSED_PROJECT_AMOUNT = "Proposed Project Amount";

	//properties
	public static final String P_KEY = "Key";
	
	//Measures
	public static final String ACTUAL_COMMITMENTS = "Actual Commitments";
	public static final String ACTUAL_DISBURSEMENTS = "Actual Disbursements";
	public static final String ACTUAL_EXPENDITURES = "Actual Expenditures";
	public static final String PLANNED_COMMITMENTS = "Planned Commitments";
	public static final String PLANNED_DISBURSEMENTS = "Planned Disbursements";
	public static final String PLANNED_EXPENDITURES = "Planned Expenditures";
	
	public static final String TOTAL_MEASURES = "Total Measures";
	
	//Values
	public static final String BOOLEAN_TRUE_KEY = "2";
	public static final String BOOLEAN_FALSE_KEY = "1";
	public static final Integer UNDEFINED_KEY = 999999999;
	
	//Pledges Constant
	public static String PLEDGE_PLEDGES_COMMITMENTS = "Pledges Actual Commitments";
	public static String PLEDGE_PLEDGES_DISBURSEMENTS = "Pledges Actual Disbursements";
	public static String PLEDGE_PLEDGES_COMMITMENTS_GAP = "Commitment Gap";
	
	//Quarters
	public static final String Q1 = "Q1";
	public static final String Q2 = "Q2";
	public static final String Q3 = "Q3";
	public static final String Q4 = "Q4";
	
	public static final String DATE_FORMAT = "YYYY-MM-dd";
	public static final String HAS_AMP_PROPERTIES = "HAS_AMP_PROPERTIES";
}
