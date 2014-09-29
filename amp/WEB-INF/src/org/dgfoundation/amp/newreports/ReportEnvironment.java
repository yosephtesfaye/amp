package org.dgfoundation.amp.newreports;

import javax.servlet.http.HttpServletRequest;

import org.dgfoundation.amp.ar.AmpARFilter;
import org.dgfoundation.amp.ar.ArConstants;
import org.digijava.kernel.request.TLSUtils;
import org.digijava.module.aim.helper.TeamMember;


/**
 * describes the per-run settings of the environment under which to run a report
 * @author Constantin Dolghier
 *
 */
public class ReportEnvironment {
	public final String locale;
	public final TeamMember viewer;
	public final AmpARFilter workspaceFilter;
	
	public ReportEnvironment(String locale, TeamMember viewer, AmpARFilter workspaceFilter) {
		this.locale = locale;
		this.viewer = viewer;
		this.workspaceFilter = workspaceFilter;
	}
	
	public static ReportEnvironment buildFor(HttpServletRequest request) {
		TLSUtils.populate(request);
		return new ReportEnvironment(
				TLSUtils.getEffectiveLangCode(), 
				(TeamMember) request.getSession().getAttribute("currentMember"), 
				(AmpARFilter) request.getSession().getAttribute(ArConstants.TEAM_FILTER));
	}
}
