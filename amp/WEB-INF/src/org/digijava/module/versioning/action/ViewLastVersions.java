package org.digijava.module.versioning.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.actions.TilesAction;
import org.digijava.module.aim.dbentity.AmpActivityVersion;
import org.digijava.module.aim.dbentity.AmpTeam;
import org.digijava.module.aim.helper.Constants;
import org.digijava.module.aim.helper.TeamMember;
import org.digijava.module.aim.util.ActivityUtil;
import org.digijava.module.aim.util.TeamUtil;
import org.digijava.module.versioning.form.AmpLastVersionsForm;

public class ViewLastVersions extends TilesAction {

	public ActionForward execute(ComponentContext context, ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		TeamMember tm = (TeamMember) session.getAttribute(Constants.CURRENT_MEMBER);
		AmpTeam currentTeam = null;
		if (tm != null) {
			currentTeam = TeamUtil.getAmpTeam(tm.getTeamId());
		}
		List<AmpActivityVersion> updatedAcitvities = ActivityUtil.getLastUpdatedActivities(currentTeam);
		session.setAttribute(Constants.MY_LAST_VERSIONS, updatedAcitvities);
		return null;
	}
}