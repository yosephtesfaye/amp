package org.dgfoundation.amp.test.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.digijava.kernel.Constants;
import org.digijava.kernel.entity.Locale;
import org.digijava.kernel.exception.DgException;
import org.digijava.kernel.request.Site;
import org.digijava.kernel.request.SiteDomain;
import org.digijava.kernel.user.User;
import org.digijava.kernel.util.SiteUtils;
import org.digijava.module.aim.action.Login;
import org.digijava.module.aim.dbentity.AmpTeam;
import org.digijava.module.aim.dbentity.AmpTeamMember;
import org.digijava.module.aim.helper.TeamMember;
import org.digijava.module.aim.util.DbUtil;
import org.digijava.module.aim.util.TeamMemberUtil;

public class TestUtil {

	public static void setSiteDomain(HttpServletRequest request) throws DgException {

		// AMP usually is the site #3
		Site s = SiteUtils.getSite(3L);
		SiteDomain sd = new SiteDomain();
		sd.setSite(s);
		request.setAttribute(Constants.CURRENT_SITE, sd);

	}

	public static void setLocaleEn(HttpServletRequest request) {
		Locale lang = new Locale();
		lang.setCode("en");
		request.setAttribute(Constants.NAVIGATION_LANGUAGE, lang);

	}

	public static void setCurrentMemberFirstATLTeam(HttpSession session) throws Exception {
		User user = DbUtil.getUser("atl@amp.org");

		if (user == null) {
			throw new Exception("There is not ATL user ");
		}
		java.util.List<AmpTeamMember> list = TeamMemberUtil.getTeamMemberbyUserId(user.getId());

		if (list.size() < 1) {
			throw new Exception("There is not teamember for the user ATL");
		}
		Long id=list.toArray(new Long[list.size()])[0];
		AmpTeamMember teamember= TeamMemberUtil.getAmpTeamMember(id);
		TeamMember finalTeamMember=new TeamMember();
		finalTeamMember.setMemberId(teamember.getAmpTeamMemId());
		finalTeamMember.setTeamId(teamember.getAmpTeam().getAmpTeamId());
		session.setAttribute("currentMember",finalTeamMember);
		
	}

}
