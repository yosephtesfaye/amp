package org.digijava.module.aim.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.digijava.module.aim.util.FeaturesUtil;
import org.digijava.module.aim.util.QuartzJobUtils;
import org.digijava.module.aim.helper.GlobalSettingsConstants;
import org.digijava.module.aim.helper.QuartzJobForm;
import org.digijava.module.aim.form.QuartzJobManagerForm;
import org.digijava.module.aim.util.QuartzJobClassUtils;
import org.quartz.SchedulerException;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzJobManager extends Action {
	private static SimpleDateFormat dateFormatOnly = new SimpleDateFormat(FeaturesUtil.getGlobalSettingValue(GlobalSettingsConstants.DEFAULT_DATE_FORMAT));
	private static SimpleDateFormat fullDateFormat = new SimpleDateFormat(FeaturesUtil.getGlobalSettingValue(GlobalSettingsConstants.DEFAULT_DATE_FORMAT) + " HH:mm:ss");
	private static java.text.DecimalFormat formatHM = new DecimalFormat("00");

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws java.lang.Exception {

		QuartzJobManagerForm qmform = (QuartzJobManagerForm) form;
		qmform.setInvalidEndDate(false);
		qmform.setInvalidTrigger(false);
		
		if ("saveJob".equals(qmform.getAction())) {
			QuartzJobForm job = new QuartzJobForm();
			job.setClassFullname(qmform.getClassFullname());
			if (qmform.getTriggerType() == 4) {
				job.setDayOfWeek(qmform.getSelectedDay());
			} else {
				job.setDayOfMonth(qmform.getSelectedMonthDay());
			}
			job.setStartDateTime(qmform.getStartDateTime());
			job.setStartH(qmform.getStartH());
			job.setStartM(qmform.getStartM());

			job.setExeTimeH(qmform.getExeTimeH());
			job.setExeTimeM(qmform.getExeTimeM());

			job.setName(qmform.getName());

			if (!"".equals(qmform.getEndDateTime())) {
				job.setEndDateTime(qmform.getEndDateTime());
				job.setEndH(qmform.getEndH());
				job.setEndM(qmform.getEndM());

			}

			Date startDate = fullDateFormat.parse(job.getStartDateTime()+" "+qmform.getStartH()+":"+qmform.getStartM()+":00");
			Date endDate = fullDateFormat.parse(job.getEndDateTime()+" "+qmform.getEndH()+":"+qmform.getEndM()+":00");

			job.setTriggerType(qmform.getTriggerType());

			if (startDate.after(endDate)) {
				qmform.setInvalidEndDate(true);
				return mapping.findForward("addJob");
			}

			try {

				if (qmform.getEditAction() != null && qmform.getEditAction()) {

					QuartzJobUtils.reScheduleJob(job);
				} else {
					// add job validation
					QuartzJobUtils.addJob(job);
				}
				qmform.reset();
			} catch (Exception e) {
				qmform.setInvalidTrigger(true);
				return mapping.findForward("addJob");
			}

		} else if ("addJob".equals(qmform.getAction())) {
			qmform.reset();
			return mapping.findForward("addJob");

		} else if ("editJob".equals(qmform.getAction())) {
			qmform.setEditAction(true);

			for (QuartzJobForm exJob : qmform.getJobs()) {
				if (exJob.getName().equals(qmform.getName())) {
					if (exJob.getEndDateTime() != null) {

						Date endDate = fullDateFormat.parse(exJob.getEndDateTime());
						qmform.setEndDateTime(dateFormatOnly.format(endDate));
						qmform.setEndH(formatHM.format(endDate.getHours()));
						qmform.setEndM(formatHM.format(endDate.getMinutes()));
					}

					if (exJob.getStartDateTime() != null) {

						Date startDate = fullDateFormat.parse(exJob.getStartDateTime());
						qmform.setStartDateTime(dateFormatOnly.format(startDate));
						qmform.setStartH(formatHM.format(startDate.getHours()));
						qmform.setStartM(formatHM.format(startDate.getMinutes()));

					}
					qmform.setName(exJob.getName());
					qmform.setClassFullname(exJob.getClassFullname());
					qmform.setJob(exJob);
					qmform.setSelectedMonthDay(exJob.getDayOfMonth());
					qmform.setSelectedDay(exJob.getDayOfWeek());
					if (exJob.getExeTimeH() != null) {
						qmform.setExeTimeH(formatHM.format(Long.parseLong(exJob.getExeTimeH())));
					}
					if (exJob.getExeTimeM() != null) {
						qmform.setExeTimeM(formatHM.format(Long.parseLong(exJob.getExeTimeM())));
						qmform.setTriggerType(exJob.getTriggerType());
					}
					break;
				}
			}
			return mapping.findForward("addJob");

		} else if ("deleteJob".equals(qmform.getAction())) {
			QuartzJobUtils.deleteJob(qmform.getName());
		} else if ("pauseAll".equals(qmform.getAction())) {
			QuartzJobUtils.pauseAll();
		} else if ("resumeAll".equals(qmform.getAction())) {
			QuartzJobUtils.resumeAll();
		} else if ("pauseJob".equals(qmform.getAction())) {
			QuartzJobUtils.pauseJob(qmform.getName());
		} else if ("resumeJob".equals(qmform.getAction())) {
			QuartzJobUtils.resumeJob(qmform.getName());
		} else if ("deleteJob".equals(qmform.getAction())) {
			QuartzJobUtils.deleteJob(qmform.getName());
		} else if ("serverTime".equals(qmform.getAction())) {
			String dt = fullDateFormat.format(new Date());
			OutputStreamWriter outputStream = new OutputStreamWriter(response.getOutputStream());
			PrintWriter out = new PrintWriter(outputStream, true);
			out.print(dt);
			out.close();
			outputStream.close();
			qmform.setAction(null);
			return null;
		} else {
			if ("runJobNow".equals(qmform.getAction())) {
				QuartzJobUtils.runJob(qmform.getName());
			}
		}

		qmform.setJcCol(QuartzJobClassUtils.getAllJobClasses());
		qmform.setJobs(QuartzJobUtils.getAllJobs());
		qmform.setAction(null);

		return mapping.findForward("forward");
	}

	public QuartzJobManager() {
	}
}
