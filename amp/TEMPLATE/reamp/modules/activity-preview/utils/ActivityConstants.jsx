/**
 * @author Daniel Oliva
 */

export const DEFAULT_CURRENCY = 'XOF';

export const PROJECT_TITLE = 'project_title';
export const AMP_ID = 'amp_id';
export const ACTIVITY_STATUS = 'activity_status';
export const ACTIVITY_BUDGET = 'activity_budget';
export const ACTIVITY_SECTION_IDS =
  [
    { key: 'AcIdentification', hash: '#AcIdentification', value: 'Identification', translationKey: 'amp.activity-preview:sectionIdentification'},
    { key: 'AcInternalIds', hash: '#AcInternalIds', value: 'Agency Internal IDs', translationKey: 'amp.activity-preview:sectionInternalIds' },
    { key: 'AcPlanning', hash: '#AcPlanning', value: 'Planning', translationKey: 'Planning' },
    { key: 'AcLocation', hash: '#AcLocation', value: 'Location', translationKey: 'Location' },
    { key: 'AcNationalPlan', hash: '#AcNationalPlan', value: 'National Plan', translationKey: 'NationalPlan' },
    { key: 'AcProgram', hash: '#AcProgram', value: 'Program', translationKey: 'Program' },
    { key: 'AcSector', hash: '#AcSector', value: 'Sectors', translationKey: 'SectorsLabel' },
    { key: 'AcFundingSources', hash: '#AcFundingSources', value: 'Funding Sources', translationKey: 'FundingSources' },
    { key: 'AcFunding', hash: '#AcFunding', value: 'Funding', translationKey: 'Funding' },
    { key: 'AcRelatedOrganizations', hash: '#AcRelatedOrganizations', value: 'Related Organizations', translationKey: 'RelatedOrganizations' },
    { key: 'AcIssues', hash: '#AcIssues', value: 'Issues', translationKey: 'Issues' },
    { key: 'AcContacts', hash: '#AcContacts', value: 'Contacts', translationKey: 'Contacts' },
    { key: 'AcFundingSummary', hash: '#AcFundingSummary', value: 'Funding Summary', translationKey: 'FundingInformation' },
    { key: 'AcAdditionalInfo', hash: '#AcAdditionalInfo', value: 'Additional Info', translationKey: 'AdditionalInfo' }
  ];

export const STATUS_REASON = 'status_reason';
export const OBJECTIVE = 'objective';
export const DESCRIPTION = 'description';
export const PROJECT_COMMENTS = 'project_comments';
export const CRIS_NUMBER = 'cris_number';

  /*export const RICH_TEXT_FIELDS = new Set([STATUS_REASON, OBJECTIVE, DESCRIPTION, PROJECT_COMMENTS,
    LESSONS_LEARNED, PROJECT_IMPACT, ACTIVITY_SUMMARY, CONDITIONALITIES, PROJECT_MANAGEMENT, RESULTS,
  ]);*/
export const RICH_TEXT_FIELDS = new Set([STATUS_REASON, OBJECTIVE, DESCRIPTION, PROJECT_COMMENTS ]);

//Funding
export const ACTUAL = 'Actual';
export const PLANNED = 'Planned';
export const PIPELINE = 'Pipeline';
export const COMMITMENTS = 'Commitments';
export const DISBURSEMENTS = 'Disbursements';
export const ADJUSTMENT_TYPES = [ACTUAL, PLANNED, PIPELINE];
export const TRANSACTION_TYPES = [COMMITMENTS, DISBURSEMENTS];

export const ACTUAL_COMMITMENTS = 'Actual Commitments';
export const ACTUAL_DISBURSEMENTS = 'Actual Disbursements';
export const PLANNED_COMMITMENTS = 'Planned Commitments';
export const PLANNED_DISBURSEMENTS = 'Planned Disbursements';
export const DELIVERY_RATE = 'Delivery Rate';

export const FIXED_EXCHANGE_RATE = "fixed_exchange_rate";
export const CURRENCY = "currency";
export const TRANSACTION_DATE = "transaction_date";
export const TRANSACTION_AMOUNT = "transaction_amount";

//Activity Internal Ids
export const ACTIVITY_INTERNAL_IDS = "activity_internal_ids";
export const INTERNAL_ID = "internal_id";
export const ORGANIZATION = "organization";

//Planning
export const PROPOSED_APPROVAL_DATE = "proposed_approval_date";
export const ACTUAL_APPROVAL_DATE = "actual_approval_date";
export const PROPOSED_START_DATE = "proposed_start_date";
export const ACTUAL_START_DATE = "actual_start_date";
export const CREATION_DATE = "creation_date";
export const PROPOSED_COMPLETION_DATE = "proposed_completion_date";
export const ACTUAL_COMPLETION_DATE = "actual_completion_date";

//Locations
export const LOCATIONS = 'locations';
export const LOCATION = 'location';
export const LOCATION_PERCENTAGE = 'location_percentage';
export const IMPLEMENTATION_LOCATION = 'implementation_location';
export const IMPLEMENTATION_LEVEL = 'implementation_level';

//National Plan Objective
export const NATIONAL_PLAN_OBJECTIVE = 'national_plan_objective';
export const PROGRAM = 'program';
export const PROGRAM_PERCENTAGE = 'program_percentage';

//Programs
export const PROGRAM_SETTINGS = 'program_settings';
export const PRIMARY_PROGRAMS = 'primary_programs';
export const SECONDARY_PROGRAMS = 'secondary_programs';

//Sectors
export const PRIMARY_SECTORS = 'primary_sectors';
export const SECONDARY_SECTORS = 'secondary_sectors';
export const SECTOR = 'sector';
export const SECTOR_PERCENTAGE = 'sector_percentage';

//Funding Section
export const FUNDINGS = 'fundings';
export const AMP_FUNDING_ID = 'amp_funding_id';
export const FUNDING_DONOR_ORG_ID = 'donor_organization_id';
export const SOURCE_ROLE = 'source_role';
export const TYPE_OF_ASSISTANCE = 'type_of_assistance';
export const FINANCING_INSTRUMENT = 'financing_instrument';
export const FUNDING_STATUS = 'funding_status';
export const FINANCING_ID = 'financing_id';
export const FUNDING_DETAILS = 'funding_details';
export const ADJUSTMENT_TYPE = 'adjustment_type';
export const TRANSACTION_TYPE = 'transaction_type';

//Aditional Info
export const TEAM = 'team';
export const CREATED_BY = 'created_by';
export const MODIFIED_BY = 'modified_by';
export const UPDATE_DATE = 'update_date';

//Related Orgs
export const RESPONSIBLE_ORGANIZATION = 'responsible_organization';
export const DONOR_ORGANIZATION = 'donor_organization';
export const EXECUTING_AGENCY = 'executing_agency';

//Issues
export const ISSUES = 'issues';
export const ISSUE_DATE = 'issue_date';
export const ISSUE_NAME = 'name';
export const MEASURES = 'measures';
export const MEASURE_NAME = 'name';
export const MEASURE_DATE = 'measure_date';
export const ACTORS = 'actors';
export const ACTOR_NAME = 'name';

//Contacts
export const DONOR_CONTACT_INFORMATION = 'donor_contact_information';
export const SECTOR_MINISTRY_CONTACT_INFORMATION = 'sector_ministry_contact_information';
export const IMPLEMENTING_AGENCY_CONTACT_INFORMATION = 'implementing/executing_agency_contact_information';
export const MARK_AS_PRIMARY = 'mark_as_primary';
export const CONTACT = 'contact';

//Structures
export const STRUCTURES = 'structures';
export const STRUCTURES_TITLE = 'title';
export const STRUCTURES_DESCRIPTION = 'description';
export const STRUCTURES_LATITUDE = 'latitude';
export const STRUCTURES_LONGITUDE = 'longitude';
export const STRUCTURES_COLOR = 'structure_color';
export const STRUCTURES_LAT = 'lat';
export const STRUCTURES_LNG = 'lng';
export const STRUCTURES_SHAPE = 'shape';
export const STRUCTURES_POINT = 'Point';
export const STRUCTURES_POLYGON = 'Polygon';
export const STRUCTURES_POLYLINE = 'Polyline';
export const STRUCTURES_COORDINATES = 'coordinates';


