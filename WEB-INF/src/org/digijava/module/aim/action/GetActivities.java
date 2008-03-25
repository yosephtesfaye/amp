package org.digijava.module.aim.action;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.digijava.kernel.exception.DgException;
import org.digijava.module.aim.dbentity.AmpActivity;
import org.digijava.module.aim.dbentity.AmpTheme;
import org.digijava.module.aim.dbentity.NpdSettings;
import org.digijava.module.aim.exception.AimException;
import org.digijava.module.aim.form.ActivitiesForm;
import org.digijava.module.aim.helper.ActivityItem;
import org.digijava.module.aim.helper.Constants;
import org.digijava.module.aim.helper.TeamMember;
import org.digijava.module.aim.util.ActivityUtil;
import org.digijava.module.aim.util.NpdUtil;
import org.digijava.module.aim.util.ProgramUtil;

/**
 * Returns XML of Activities list depending on request parameters. This action
 * is called asynchronously from NPD, but can be used in any other place where
 * Activity list is needed.
 * 
 * @author Irakli Kobiashvili
 * 
 */
public class GetActivities extends Action {
	public static final String PARAM_PROGRAM_ID = "programId";

	public static final String PARAM_STATUS = "status";

	public static final String PARAM_DONOR = "donorId";

	public static final String PARAM_YEAR_START = "startYear";

	public static final String PARAM_YEAR_END = "endYear";

	public static final String ROOT_TAG = "activityList";

	private static Logger logger = Logger.getLogger(GetActivities.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("got asynchronous request for Activities list");
		HttpSession session = request.getSession();
		TeamMember tm = (TeamMember) session.getAttribute(Constants.CURRENT_MEMBER);
		NpdSettings settings = NpdUtil.getCurrentSettings(tm.getTeamId());
		// TODO AMP-2539 we need some decision about filtering activities for teams or team members.

		int maxPages=1;
		
		response.setContentType("text/xml");
		ActivitiesForm actForm = (ActivitiesForm) form;
		logger.debug("programId=" + actForm.getProgramId() + " statusCode=" + actForm.getStatusId());
		OutputStreamWriter outputStream =null;
		
		try {
			Date fromYear = yearToDate(actForm.getStartYear(), false);
			Date toYear = yearToDate(actForm.getEndYear(), true);

			//count activities with same filter but without pagination. this should be fast.
			logger.debug("counting activities");
			Integer count=getActivitiesCount(actForm.getProgramId(),actForm.getStatusId(), actForm.getDonorId(), fromYear,toYear, null, tm, false);
			//calculate pagination
			Integer pageStart=null;
			Integer rowCount=null;
			if (count!=null && count.intValue()>0 && settings!=null && settings.getActListPageSize()!=null && actForm.getCurrentPage()!=null && actForm.getCurrentPage()>0){
				rowCount=settings.getActListPageSize();
				pageStart=(actForm.getCurrentPage()-1)*rowCount;
				maxPages=count/settings.getActListPageSize();
			}
			
			logger.debug("retriving activities");
			Collection<AmpActivity> activities = getActivities(actForm.getProgramId(),actForm.getStatusId(), actForm.getDonorId(), fromYear,toYear, null, null, pageStart,rowCount, false);

			//convert activities to xml
			logger.debug("Converting activities to XML");
			String xml = activities2XML(activities,maxPages);

			logger.debug("Setting activties XML in the response");
			outputStream =  new OutputStreamWriter( response.getOutputStream(),"UTF-8");
			PrintWriter out = new PrintWriter(outputStream, true);
			
			out.println(xml);
//			outputStream.write(xml.getBytes());
			out.close();
			// return xml
			logger.debug("closing and returning response XML of NPD Activities");
			outputStream.close();
		} catch (Exception e) {
			logger.info(e);
			if (outputStream != null) {
				try {
					outputStream.write(stackTrace2XML(e));
				} catch (IOException e1) {
					logger.info(e1);
				}
			}
		}
		return null;
	}

	/**
	 * Retrieves Activities filtered according params.
	 * @param ampThemeId filter activities assigne to programm(Theme) specified ith this id.
	 * @param statusCode filter activities, get anly with this status.
	 * @param donorOrgId 
	 * @param fromDate
	 * @param toDate
	 * @param locationId
	 * @param teamMember
	 * @param recurse
	 * @return
	 * @throws AimException
	 */
	private Collection<AmpActivity> getActivities(Long ampThemeId,
			String statusCode,
			Long donorOrgId,
			Date fromDate,
			Date toDate,
			Long locationId,
			TeamMember teamMember,
			Integer pageStart,
			Integer rowCount, 
			boolean recurse) throws AimException, DgException{


		Collection<AmpActivity> result=null;

		//search actvities in db, with pagination.
		result = ActivityUtil.searchActivities(ampThemeId,
				statusCode,
				donorOrgId,
				fromDate,
				toDate,
				locationId,
				teamMember,
				pageStart,
				rowCount);

		if (recurse){
			Collection<AmpTheme> children = ProgramUtil.getSubThemes(ampThemeId);
			if (children!= null && children.size() > 0){
				for (AmpTheme prog : children) {
//					Collection<AmpActivity> subActivities = ActivityUtil.searchActivities(
//							prog.getAmpThemeId(), statusCode, donorOrgId,
//							fromDate, toDate, locationId, teamMember);
//					if (subActivities!= null && subActivities.size()>0){
//						result.addAll(subActivities);
//					}
					Collection<AmpActivity> childsActivities=getActivities(prog.getAmpThemeId(), statusCode, donorOrgId, fromDate, toDate, locationId, teamMember, pageStart, rowCount, recurse);
					if (childsActivities!=null && childsActivities.size()>0){
						if(result==null){
							result=childsActivities;
						}else{
							result.addAll(childsActivities);
						}
					}
				}
			}
		}

		//Set<AmpActivity> activities = new TreeSet<AmpActivity>(new ActivityUtil.ActivityIdComparator());
		List<AmpActivity> sortedActivities = null;
		if (result!=null){
			sortedActivities= new ArrayList<AmpActivity>(result);
			Collections.sort(sortedActivities);
		}

		return sortedActivities;
	}

	private Integer getActivitiesCount(Long ampThemeId,
			String statusCode,
			Long donorOrgId,
			Date fromDate,
			Date toDate,
			Long locationId,
			TeamMember teamMember,
			boolean recurse) throws AimException, DgException{


		int result=0;

		result = ActivityUtil.searchActivitiesCount(ampThemeId,
				statusCode,
				donorOrgId,
				fromDate,
				toDate,
				locationId,
				teamMember);

		if (recurse){
			Collection<AmpTheme> children = ProgramUtil.getSubThemes(ampThemeId);
			if (children!= null && children.size() > 0){
				for (AmpTheme prog : children) {
//					Collection<AmpActivity> subActivities = ActivityUtil.searchActivities(
//							prog.getAmpThemeId(), statusCode, donorOrgId,
//							fromDate, toDate, locationId, teamMember);
//					if (subActivities!= null && subActivities.size()>0){
//						result.addAll(subActivities);
//					}
					Integer childsActivities=getActivitiesCount(prog.getAmpThemeId(), statusCode, donorOrgId, fromDate, toDate, locationId, teamMember, recurse);
					if (childsActivities!=null){
						result+=childsActivities;
					}
				}
			}
		}

		return result;
	}
	
	
	
	/**
	 * Converts year represented as String to date. It can create last day or
	 * first day of the year depending on the second parameter. todo: this
	 * should be changed to last and first second of the year.
	 * 
	 * @param year
	 * @param lastSecondOfYear
	 * @return
	 * @throws AimException
	 */
	private Date yearToDate(String year, boolean lastSecondOfYear)
			throws AimException {
		if (year != null) {
			try {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.valueOf(year).intValue());
				if (lastSecondOfYear) {
					cal.set(Calendar.MONTH, Calendar.DECEMBER);
					cal.set(Calendar.DAY_OF_MONTH, 31);
				} else {
					cal.set(Calendar.MONTH, Calendar.JANUARY);
					cal.set(Calendar.DAY_OF_MONTH, 1);
				}
				return cal.getTime();
			} catch (Exception e) {
				logger.error(e);
				throw new AimException("Cannot convert year: " + year
						+ " to int. Invalid request param", e);
			}
		}
		return null;
	}

	/**
	 * Converts Exception stack trace to XML.
	 * used to display trace instead of results in ajax calls.
	 * @param e 
	 * @return String representing XML of error
	 */
	private String stackTrace2XML(Exception e) {
		String result = "<error>";
		result += "<frame>" + e + "</frame>";
		StackTraceElement[] traceArray = e.getStackTrace();
		if (traceArray != null) {
			for (int i = 0; i < traceArray.length; i++) {
				String frame = "<frame>" + traceArray[i].toString()
						+ "</frame>";
				result += frame;
			}
		}
		result += "</error>";
		return result;
	}

	/**
	 * Constructs XML from Activities This method converts every AmpActivity db
	 * bean to ActivtyItem helper beans. Then getXml() method of the helper bean
	 * is used to get portions of the activity xml.
	 * TODO we should have XML file and velocity here. and probably Schema too...
	 * 
	 * @param acts
	 *            Collection of AmpActivity db beans
	 * @return XML representing list of the activities.
	 * @see AmpActivity
	 * @see ActivityItem
	 */
	private String activities2XML(Collection<AmpActivity> acts,int maxPages) throws Exception {
		double proposedSum = 0;
		double actualSum = 0;
		double plannedSum = 0;
		DecimalFormat mf=NpdUtil.getNumberFormatter();
		String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; 
		result += "<" + ROOT_TAG;
		String temp = "";
		if (acts != null && acts.size() > 0) {
			for (AmpActivity activity : acts) {
				//create helper bean from activity
				ActivityItem item = new ActivityItem(activity);
				//get already calculated amounts from helper
				ActivityUtil.ActivityAmounts amounts = item.getAmounts();
				//calculate totals
				proposedSum += amounts.getProposedAmout();
				actualSum += amounts.getActualAmount();
				plannedSum += amounts.getPlannedAmount();
				//generate one activity portion of XML from helper
				temp += item.getXml();
			}
		}
		result += " proposedSum=\"" + mf.format(proposedSum)+ "\" ";
		result += " actualSum=\"" + mf.format(actualSum)+ "\" ";
		result += " plannedSum=\"" + mf.format(plannedSum)+ "\" ";
		result += " totalPages=\""+maxPages+"\" ";
		result += ">" + temp + "</" + ROOT_TAG + ">";
		return result;
	}

}
