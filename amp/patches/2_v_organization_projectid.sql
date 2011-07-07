CREATE OR REPLACE VIEW v_organization_projectid AS SELECT aaii.amp_activity_id, CONCAT(org.name,' - ', aaii.internal_id) as orgname_id, aaii.amp_org_id FROM amp_activity_internal_id aaii, amp_organisation org WHERE aaii.amp_org_id = org.amp_org_id;