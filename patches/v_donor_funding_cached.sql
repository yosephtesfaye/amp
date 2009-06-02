CREATE OR REPLACE VIEW `v_donor_funding_cached` AS 
  select 
    `f`.`amp_activity_id` AS `amp_activity_id`,
    `f`.`amp_funding_id` AS `amp_funding_id`,
    `fd`.`amp_fund_detail_id` AS `amp_fund_detail_id`,
    `d`.`name` AS `donor_name`,
    `fd`.`transaction_type` AS `transaction_type`,
    `fd`.`adjustment_type` AS `adjustment_type`,
    `fd`.`transaction_date` AS `transaction_date`,
    ((`fd`.`transaction_amount` * `sa`.`sector_percentage`) / 100) AS `transaction_amount`,
    `c`.`currency_code` AS `currency_code`,
    `cval`.`id` AS `terms_assist_id`,
    `cval`.`category_value` AS `terms_assist_name`,
    `fd`.`fixed_exchange_rate` AS `fixed_exchange_rate`,
    `b`.`org_grp_name` AS `org_grp_name`,
    `ot`.`org_type` AS `donor_type_name`,
    `cval2`.`category_value` AS `financing_instrument_name`,
    `cval2`.`id` AS `financing_instrument_id`,
    `d`.`amp_org_id` AS `org_grp_id`,
    `ot`.`amp_org_type_id` AS `org_type_id`,
    `fd`.`disbursement_order_rejected` AS `disb_ord_rej`,
    `getSectorName`(`getParentSectorId`(`s`.`amp_sector_id`)) AS `sectorname`,
    `getParentSectorId`(`s`.`amp_sector_id`) AS `amp_sector_id`,
    `sa`.`sector_percentage` AS `sector_percentage`,
    `ss`.`sec_scheme_name` AS `sec_scheme_name`,
    `cc`.`name` AS `classification_name`,
    `cc`.`classification_id` AS `classification_id`,
    `ss`.`amp_sec_scheme_id` AS `amp_sec_scheme_id`,
    `sa`.`classification_config_id` AS `sec_act_id`,
    `cc`.`id` AS `cc_id`,
    `s`.`sector_code` AS `sector_code` 
  from 
    (((((((((((`amp_funding` `f` join `amp_funding_detail` `fd` on((`f`.`amp_funding_id` = `fd`.`AMP_FUNDING_ID`))) join `amp_category_value` `cval`) join `amp_currency` `c`) join `amp_organisation` `d`) join `amp_org_group` `b`) join `amp_org_type` `ot`) join `amp_category_value` `cval2`) join `amp_activity_sector` `sa` on((`f`.`amp_activity_id` = `sa`.`amp_activity_id`))) join `amp_sector` `s` on((`sa`.`amp_sector_id` = `s`.`amp_sector_id`))) join `amp_sector_scheme` `ss`) join `amp_classification_config` `cc`) 
  where 
    ((`cval2`.`id` = `f`.`financing_instr_category_value`) and (`c`.`amp_currency_id` = `fd`.`amp_currency_id`) and (`f`.`amp_funding_id` = `fd`.`AMP_FUNDING_ID`) and (`cval`.`id` = `f`.`type_of_assistance_category_va`) and (`d`.`amp_org_id` = `f`.`amp_donor_org_id`) and (`d`.`org_grp_id` = `b`.`amp_org_grp_id`) and (`ot`.`amp_org_type_id` = `d`.`org_type_id`) and (`cc`.`name` = _latin1'Primary') and (`sa`.`classification_config_id` = `cc`.`id`) and (`cc`.`classification_id` = `ss`.`amp_sec_scheme_id`)) 
  group by 
    `getParentSectorId`(`s`.`amp_sector_id`),`fd`.`transaction_type`,`fd`.`amp_fund_detail_id`,`f`.`amp_funding_id`,`fd`.`adjustment_type`,`s`.`sector_code` 
  order by 
    `f`.`amp_activity_id`,`getSectorName`(`getParentSectorId`(`s`.`amp_sector_id`)),`fd`.`transaction_type`,`f`.`amp_funding_id`;