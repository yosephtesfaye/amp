/**
 * 
 */
package org.dgfoundation.amp.permissionmanager.components.features.sections;

import java.util.Set;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.dgfoundation.amp.onepager.components.features.AmpFeaturePanel;
import org.dgfoundation.amp.onepager.components.fields.AbstractAmpAutoCompleteTextField;
import org.dgfoundation.amp.onepager.components.fields.AmpComboboxFieldPanel;
import org.dgfoundation.amp.onepager.models.AmpOrganisationSearchModel;
import org.dgfoundation.amp.onepager.yui.AmpAutocompleteFieldPanel;
import org.dgfoundation.amp.permissionmanager.components.features.fields.AmpPMAjaxPagingNavigator;
import org.dgfoundation.amp.permissionmanager.components.features.tables.AmpPMOrganizationsUsersTableFeaturePanel;
import org.dgfoundation.amp.permissionmanager.web.PMUtil;
import org.digijava.kernel.user.User;
import org.digijava.module.admin.exception.AdminException;
import org.digijava.module.admin.util.DbUtil;
import org.digijava.module.aim.dbentity.AmpOrganisation;

/**
 * @author dan
 *
 */
public class AmpPMSearchOrganizationsFeaturePanel extends AmpFeaturePanel {

	protected ListView<AmpOrganisation> idsList;
	
	/**
	 * @param id
	 * @param fmName
	 * @throws Exception
	 */
	public AmpPMSearchOrganizationsFeaturePanel(String id, String fmName) throws Exception {
		super(id, fmName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param model
	 * @param fmName
	 * @throws Exception
	 */
	public AmpPMSearchOrganizationsFeaturePanel(String id, IModel<User> model, String fmName) throws Exception {
		super(id, model, fmName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param userModel
	 * @param fmName
	 * @param hideLabel
	 * @throws Exception
	 */
	public AmpPMSearchOrganizationsFeaturePanel(String id,final IModel<User> userModel, String fmName, boolean hideLabel) throws Exception {
		super(id, userModel, fmName, hideLabel);

		final AmpPMOrganizationsUsersTableFeaturePanel orgsTable = new AmpPMOrganizationsUsersTableFeaturePanel("orgsListTable", userModel, "Verified Organizations", false);
		orgsTable.setTableWidth(510);
		add(orgsTable);
		//add(new PagingNavigator("workspacesNavigator", (PageableListView)workspacesTable.getList()));
		final AmpPMAjaxPagingNavigator paginator = new AmpPMAjaxPagingNavigator("verifiedOrgsNavigator", (PageableListView)orgsTable.getList()); 
		add(paginator);
		idsList = orgsTable.getList();
		
		
//		final AbstractAmpAutoCompleteTextField<AmpOrganisation> autoComplete = new AbstractAmpAutoCompleteTextField<AmpOrganisation>(AmpOrganisationSearchModel.class) {
		final AmpAutocompleteFieldPanel<AmpOrganisation> autoComplete = new AmpAutocompleteFieldPanel<AmpOrganisation>("searchOrganizations", "Search Organizations",AmpOrganisationSearchModel.class) {

			@Override
			protected String getChoiceValue(AmpOrganisation choice) {
				return choice.getAcronymAndName();
			}

			@Override
			public void onSelect(AjaxRequestTarget target,AmpOrganisation choice) {
				boolean choiceNotExist = PMUtil.addOrganizationToUser(userModel.getObject(), choice);
//				Set<AmpOrganisation> set = userModel.getObject().getAssignedOrgs();
//				set.add(choice);
				if(!choiceNotExist) return;
				idsList.removeAll();
				target.addComponent(idsList.getParent());
				try {
					DbUtil.updateUser(userModel.getObject());
				} catch (AdminException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public Integer getChoiceLevel(AmpOrganisation choice) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		AttributeModifier sizeModifier = new AttributeModifier("size",new Model(35));
		autoComplete.add(sizeModifier);
		add(autoComplete);
	//	final AmpComboboxFieldPanel<AmpOrganisation> searchOrgs=new AmpComboboxFieldPanel<AmpOrganisation>("searchOrganizations", "Search Organizations", autoComplete);
		

//		add(new Link("saveButton"){
//			@Override
//			public void onClick() {
//				try {
//					DbUtil.updateUser(model.getObject());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//		add(new Link("resetButton"){
//			@Override
//			public void onClick() {
//				model.getObject().getAssignedOrgs().clear();
//				model.getObject().getAssignedOrgs().addAll(org.digijava.module.aim.util.DbUtil.getUser(model.getObject().getEmail()).getAssignedOrgs());
//				idsList.removeAll();
//			}
//		});
//
//		add(new Link("cancelButton"){
//			@Override
//			public void onClick() {
//			}
//			
//		});
			
		
	}

}
