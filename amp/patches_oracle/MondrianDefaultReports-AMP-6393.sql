DELETE FROM off_line_reports;
INSERT INTO off_line_reports (id, name, query, teamid, ispublic, measures, columns, ownerId,creationdate) 
VALUES (1, 'Report by Sectors', 'select NON EMPTY {[Measures].[Actual Commitments], [Measures].[Actual Disbursements], [Measures].[Actual Expenditures]} ON COLUMNS, NON EMPTY {([Primary Sector].[All Primary Sectors], [Activity].[All Activities])} ON ROWS from [Donor Funding]', 
null, 1, 'Actual Commitments,Actual Disbursements,Actual Expenditures,sector Percentage ', 'Primary Sector,Primary Sectors,Activities', null,null);
INSERT INTO off_line_reports (id, name, query, teamid, ispublic, measures, columns, ownerId,creationdate) 
VALUES (2, 'Report by Financing Instrument and Donor Information', 'select NON EMPTY Crossjoin({[Donor Dates]}, {[Measures].[Actual Commitments]}) ON COLUMNS, NON EMPTY Crossjoin({[Financing Instrument]}, Crossjoin({[Terms of Assistance]}, Crossjoin({[Donor]}, Crossjoin({[Primary Sector]}, {[Activity]})))) ON ROWS from [Donor Funding]', 
null, 1, 'Donor Dates,Actual Commitments', 'Financing Instrument,Terms of Assistance,Donor,Primary Sector', null,null);

INSERT INTO off_line_reports (id, name, query, teamid, ispublic, measures, columns, ownerId,creationdate) 
VALUES
(3, 'Report by Donors', 'select NON EMPTY {[Measures].[Actual Commitments]} ON COLUMNS, {[Donor]} ON ROWS from [Donor Funding]', 
null, 0, 'Actual Commitments', 'Donor', null,null);

INSERT INTO off_line_reports (id, name, query, teamid, ispublic, measures, columns, ownerId,creationdate) 
VALUES
(4, 'Report by funding years', 'select NON EMPTY {[Measures].[Actual Commitments], [Measures].[Actual Disbursements], [Measures].[Planned Commitments], [Measures].[Planned Disbursements]} ON COLUMNS, {[Donor Dates].[All Periods]} ON ROWS from [Donor Funding]', 
null, 0, 'Actual Commitments,Actual Disbursements,Planned Commitments,Planned Disbursements', 'Donor Dates', null,null);
