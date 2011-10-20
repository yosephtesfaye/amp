/**
 * Copyright (c) 2010 Development Gateway (www.developmentgateway.org)
 *
 */
package org.dgfoundation.amp.onepager.components.features.sections;

import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.dgfoundation.amp.onepager.OnePagerUtil;
import org.dgfoundation.amp.onepager.components.AmpComponentPanel;
import org.dgfoundation.amp.onepager.components.features.tables.AmpLocationFormTableFeature;
import org.dgfoundation.amp.onepager.components.fields.AmpCategorySelectFieldPanel;
import org.dgfoundation.amp.onepager.models.AmpCategoryValueByKeyModel;
import org.dgfoundation.amp.onepager.util.AmpFMTypes;
import org.dgfoundation.amp.onepager.web.pages.OnePager;
import org.digijava.module.aim.dbentity.AmpActivityLocation;
import org.digijava.module.aim.dbentity.AmpActivityVersion;
import org.digijava.module.aim.helper.GlobalSettingsConstants;
import org.digijava.module.aim.util.FeaturesUtil;
import org.digijava.module.categorymanager.dbentity.AmpCategoryValue;
import org.digijava.module.categorymanager.util.CategoryConstants;

/**
 * Location section of the one pager form
 * 
 * @author mpostelnicu@dgateway.org since Oct 7, 2010
 * @see OnePager
 */
public class AmpLocationFormSectionFeature extends AmpFormSectionFeaturePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8671173324087460636L;

	/**
	 * @param id
	 * @param fmName
	 * @param regionalFundingFeature 
	 * @throws Exception
	 */
	public AmpLocationFormSectionFeature(String id, String fmName,
			final IModel<AmpActivityVersion> am, AmpComponentPanel regionalFundingFeature) throws Exception {
		super(id, fmName, am);
		this.fmType = AmpFMTypes.MODULE;

	
		
		AmpCategorySelectFieldPanel implementationLevel = new AmpCategorySelectFieldPanel(
				"implementationLevel",
				CategoryConstants.IMPLEMENTATION_LEVEL_KEY,
				new AmpCategoryValueByKeyModel(
						new PropertyModel<Set<AmpCategoryValue>>(am,
								"categories"),
						CategoryConstants.IMPLEMENTATION_LEVEL_KEY),
				CategoryConstants.IMPLEMENTATION_LEVEL_NAME, true, true, null, AmpFMTypes.MODULE);
		add(implementationLevel);

		final AmpCategorySelectFieldPanel implementationLocation = new AmpCategorySelectFieldPanel(
				"implementationLocation",
				CategoryConstants.IMPLEMENTATION_LOCATION_KEY,
				new AmpCategoryValueByKeyModel(
						new PropertyModel<Set<AmpCategoryValue>>(am,
								"categories"),
						CategoryConstants.IMPLEMENTATION_LOCATION_KEY),
				CategoryConstants.IMPLEMENTATION_LOCATION_NAME, true, true,
				null, implementationLevel.getChoiceModel(), AmpFMTypes.MODULE);
		implementationLocation.setOutputMarkupId(true);
		add(implementationLocation);

		final AmpLocationFormTableFeature locationsTable = new AmpLocationFormTableFeature(
				"locationsTable", "Locations", am, (AmpRegionalFundingFormSectionFeature) regionalFundingFeature, implementationLocation, implementationLevel);

		
		// add behavior to update implementation location when implementation
		// level choice changes
		// when chaging implementation level, remove all locations
		implementationLevel.getChoiceContainer().add(
				new AjaxFormComponentUpdatingBehavior("onchange") {
					private static final long serialVersionUID = -8419230552388122030L;
					
					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						target.addComponent(implementationLocation);
						
						String activityFormOnePager = FeaturesUtil.getGlobalSettingValue(
								GlobalSettingsConstants.MIXED_IMPLEMENTATION_LOCATION);
						if ("false".equals(activityFormOnePager)){
							Set<AmpActivityLocation> set = locationsTable.getSetModel().getObject();
							if(set.size()>0) {
								locationsTable.getSetModel().getObject().clear();
								locationsTable.getList().removeAll();
								target.appendJavascript(OnePagerUtil.getToggleChildrenJS(locationsTable));
								target.addComponent(locationsTable);
							}
						}
					}
				});

		//when changing implementation location, remove all locations
		implementationLocation.getChoiceContainer().add(
						new AjaxFormComponentUpdatingBehavior("onchange") {
							private static final long serialVersionUID = -8419230552388122030L;
							
							@Override
							protected void onUpdate(AjaxRequestTarget target) {
								Set<AmpActivityLocation> set = locationsTable.getSetModel().getObject();
								String mixedImplementationLocation = FeaturesUtil.getGlobalSettingValue(
										GlobalSettingsConstants.MIXED_IMPLEMENTATION_LOCATION);
								if ("false".equals(mixedImplementationLocation)){
									if(set.size()>0) {
										locationsTable.getSetModel().getObject().clear();
										locationsTable.getList().removeAll();
										target.appendJavascript(OnePagerUtil.getToggleChildrenJS(locationsTable));
										target.addComponent(locationsTable);
									}
								}
							}
						});
		
		
		
		// add location table
		
		add(locationsTable);
	}

}
