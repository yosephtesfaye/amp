package org.dgfoundation.amp.newreports;

import javax.servlet.http.HttpServletRequest;

import org.dgfoundation.amp.ar.AmpARFilter;
import org.dgfoundation.amp.ar.ArConstants;
import org.digijava.kernel.request.TLSUtils;
import org.digijava.module.aim.helper.TeamMember;


/**
 * describes the per-run settings of the environment under which to run a report
 * current implementation limitation in AMSP: pledgesFilter is only applied in pledge reports (and those disregard workspace filters)
 * @author Constantin Dolghier
 *
 */
public class ReportEnvironment {
	
	public final String locale;
	public final IdsGeneratorSource workspaceFilter;
	public final IdsGeneratorSource pledgesFilter;
	
	/**
	 * the currency code used for rendering the report IFF the report specification does not specify any
	 */
	public final String defaultCurrencyCode;
	
//	public final TeamMember viewer;
//	public final IdsGeneratorSource workspaceFilter;

	public ReportEnvironment(String locale, IdsGeneratorSource workspaceFilter, String defaultCurrencyCode) {
		this(locale, workspaceFilter, null, defaultCurrencyCode);
	}

	public ReportEnvironment(String locale, IdsGeneratorSource workspaceFilter, IdsGeneratorSource pledgesFilter, String defaultCurrencyCode) {
		this.locale = locale;
		this.workspaceFilter = workspaceFilter;
		this.pledgesFilter = pledgesFilter;
		this.defaultCurrencyCode = defaultCurrencyCode;
	}
	
	public static ReportEnvironment buildFor(HttpServletRequest request) {
		TLSUtils.populate(request);
		return new ReportEnvironment(
				TLSUtils.getEffectiveLangCode(),
				new CompleteWorkspaceFilter(
						(TeamMember) request.getSession().getAttribute("currentMember"),
						(AmpARFilter) request.getSession().getAttribute(ArConstants.TEAM_FILTER)
						),
				AmpARFilter.getDefaultCurrency().getCurrencyCode());
	}	
}
