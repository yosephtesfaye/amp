package org.digijava.module.aim.action ;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.digijava.module.aim.dbentity.AmpCurrency;
import org.digijava.module.aim.dbentity.AmpOrganisation;
import org.digijava.module.aim.dbentity.AmpSector;
import org.digijava.module.aim.dbentity.AmpTeam;
import org.digijava.module.aim.form.CommitmentbyDonorForm;
import org.digijava.module.aim.helper.Constants;
import org.digijava.module.aim.helper.FilterProperties;
import org.digijava.module.aim.helper.TeamMember;
import org.digijava.module.aim.util.CurrencyUtil;
import org.digijava.module.aim.util.DbUtil;
import org.digijava.module.aim.util.LocationUtil;
import org.digijava.module.aim.util.ReportUtil;
import org.digijava.module.aim.util.SectorUtil;
import org.digijava.module.aim.util.TeamUtil;

public class ViewQuarterlyDateRange extends Action
{
	private static Logger logger = Logger.getLogger(ViewQuarterlyDateRange.class) ;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws java.lang.Exception
	{		
		CommitmentbyDonorForm formBean = (CommitmentbyDonorForm) form;
		DecimalFormat mf = new DecimalFormat("###,###,###,###,###") ;
		HttpSession session = request.getSession();
		TeamMember teamMember=(TeamMember)session.getAttribute("currentMember");
		if(teamMember==null)
			return mapping.findForward("index");
//		logger.debug(" Team Member "+teamMember.getAppSettings().getPerspective());
		String perspective = "DN";
		
		if(formBean.getPerspective() == null)
		{
			perspective = teamMember.getAppSettings().getPerspective();
		}
		else
			perspective = formBean.getPerspectiveFilter();
		if(perspective.equals("Donor"))
			perspective="DN";
		if(perspective.equals("MOFED"))
			perspective="MA";
		formBean.setPerspectiveFilter(perspective);
		Long ampTeamId=teamMember.getTeamId();
		logger.info("Team Id: " + ampTeamId);
		ArrayList dbReturnSet= null ;
		Iterator iter = null;
		Iterator iterst = null ;
		Iterator iterSub = null ;
		Long ampStatusId=null;
		Long ampOrgId=null;
		Long ampSectorId=null;
		String region=null;
		Long ampModalityId=null;
		String ampCurrencyCode=null;
		int ampAdjustmentId=1;
		Collection reports=new ArrayList();
		Long All=new Long(0);
		int startYear = 0;
		int startMonth = 0;
		int startDay = 0;
		int closeYear = 0;
		int closeMonth = 0;
		int closeDay = 0;
		int fromYr = 0;
		int toYr = 0;
		int year=0;
		int fiscalCalId=0;
		ArrayList filters=null;
		String filterSet= "";
		int filterCnt=0;
		AmpCurrency ampCurrency=null;
		Calendar c=Calendar.getInstance();
		year=c.get(Calendar.YEAR);
		logger.debug("Year: " + year);

		//Set all the filters
		formBean.setFilterFlag("false");
		formBean.setAdjustmentFlag("false");
		formBean.setStatusColl(new ArrayList()) ;
		formBean.setSectorColl(new ArrayList()) ;
		formBean.setRegionColl(new ArrayList()) ;
		formBean.setDonorColl(new ArrayList()) ;
		formBean.setModalityColl(new ArrayList()) ;
		formBean.setCurrencyColl(new ArrayList()) ;
		formBean.setAmpFromYears(new ArrayList());
		formBean.setAmpToYears(new ArrayList());
		formBean.setAmpStartYears(new ArrayList());
		formBean.setAmpCloseYears(new ArrayList());
		formBean.setAmpStartDays(new ArrayList());
		formBean.setAmpCloseDays(new ArrayList());
		formBean.setFiscalYears(new ArrayList());
		filters=DbUtil.getTeamPageFilters(ampTeamId,Constants.DATERANGE);
		logger.info("Filter Size: " + filters.size());
		if(filters.size()==0)
			formBean.setGoFlag("false");
		if(filters.size()>0)
		{
			formBean.setGoFlag("true");
			if(filters.indexOf(Constants.PERSPECTIVE)!=-1)
			{
				formBean.setFilterFlag("true");
				filterSet = filterSet + "PERSPECTIVE - ";
				filterCnt++;
			}
			
			if(filters.indexOf(Constants.ACTUAL_PLANNED)!=-1)
			{
				formBean.setAdjustmentFlag("true");
				filterSet = filterSet + "ADJUSTMENT - ";
				filterCnt++;
			}
			
			if(filters.indexOf(Constants.STATUS)!=-1)
			{
				filterSet = filterSet + "STATUS - ";
				filterCnt++;
				dbReturnSet=DbUtil.getAmpStatus();
				formBean.setStatusColl(dbReturnSet) ;
			}
			
			if(filters.indexOf(Constants.SECTOR)!=-1)
			{
				filterSet = filterSet + "SECTOR - ";
				filterCnt++;
				formBean.setSectorColl(new ArrayList()) ;
				dbReturnSet=SectorUtil.getAmpSectors();
				iter = dbReturnSet.iterator() ;
				while(iter.hasNext())
				{
					AmpSector ampSector=(AmpSector) iter.next();
					if(ampSector.getName().length()>20)
					{
						String temp=ampSector.getName().substring(0,20) + "...";
						ampSector.setName(temp);
					}
					formBean.getSectorColl().add(ampSector);
					dbReturnSet=SectorUtil.getAmpSubSectors(ampSector.getAmpSectorId());
					iterSub=dbReturnSet.iterator();
					while(iterSub.hasNext())
					{
						AmpSector ampSubSector=(AmpSector) iterSub.next();
						String temp=" -- " + ampSubSector.getName();
						if(temp.length()>20)
						{
							temp=temp.substring(0,20) + "...";
							ampSubSector.setName(temp);
						}
						ampSubSector.setName(temp);
						formBean.getSectorColl().add(ampSubSector);
					}
				}
			}
			
			if(filters.indexOf(Constants.REGION)!=-1)
			{
				filterSet = filterSet + "REGION - ";
				filterCnt++;
				dbReturnSet=LocationUtil.getAmpLocations();
				formBean.setRegionColl(dbReturnSet) ;
			}
			
			if(filters.indexOf(Constants.DONORS)!=-1)
			{
				filterSet = filterSet + "DONORS - ";
				filterCnt++;
				dbReturnSet=DbUtil.getAmpDonors(ampTeamId);
				iter = dbReturnSet.iterator() ;
				formBean.setDonorColl(new ArrayList()) ;
				while ( iter.hasNext() )
				{
					AmpOrganisation ampOrganisation = (AmpOrganisation) iter.next() ;
					if(ampOrganisation.getAcronym().length()>20)
					{
						String temp=ampOrganisation.getAcronym().substring(0,20) + "...";
						ampOrganisation.setAcronym(temp);
					}
					formBean.getDonorColl().add(ampOrganisation);
				}
			}
			
			if(filters.indexOf(Constants.FINANCING_INSTRUMENT)!=-1)
			{
				filterSet = filterSet + "MODALITY - ";
				filterCnt++;
				dbReturnSet=DbUtil.getAmpModality();
				formBean.setModalityColl(dbReturnSet);
			}
			
			if(filters.indexOf(Constants.CURRENCY)!=-1)
			{
				filterSet = filterSet + "CURRENCY - ";
				filterCnt++;
				dbReturnSet=CurrencyUtil.getAmpCurrency();
				formBean.setCurrencyColl(dbReturnSet) ;	
			}
			
			if(filters.indexOf(Constants.CALENDAR)!=-1)
			{
				filterCnt+=10;
				formBean.setFiscalYears(DbUtil.getAllFisCalenders());
			}
			
			if(filters.indexOf(Constants.YEAR_RANGE)!=-1)
			{
				for(int i=(year-15);i<(year+1);i++)
				{
					formBean.getAmpFromYears().add(new Long(i));
					formBean.getAmpToYears().add(new Long(i));
				}
			}
			if(filters.indexOf(Constants.STARTDATE_CLOSEDATE)!=-1)
			{
				for(int i=(year-15);i<(year+1);i++)
				{
					formBean.getAmpStartYears().add(new Long(i));
					formBean.getAmpCloseYears().add(new Long(i));
				}
				for(int i=1;i<=31;i++)
				{
					formBean.getAmpStartDays().add(new Long(i));
					formBean.getAmpCloseDays().add(new Long(i));
				}
			}
		}		
		if(formBean.getFiscalCalId()==0)
		{
			fiscalCalId=teamMember.getAppSettings().getFisCalId().intValue();
			formBean.setFiscalCalId(fiscalCalId);
		}
		else
			fiscalCalId =  formBean.getFiscalCalId();

		if(formBean.getAmpStatusId()==null || formBean.getAmpStatusId().intValue()==0)
			ampStatusId=All;
		else
			ampStatusId=formBean.getAmpStatusId();
			
		if(formBean.getAmpSectorId()==null || formBean.getAmpSectorId().intValue() == 0)
			ampSectorId=All;
		else
			ampSectorId=formBean.getAmpSectorId();	
		
		if(formBean.getAmpLocationId()==null || formBean.getAmpLocationId().equals("All"))
			region="All";
		else
			region=formBean.getAmpLocationId();
		
		if(formBean.getAmpOrgId()==null || formBean.getAmpOrgId().intValue()==0)
			ampOrgId=All;
		else
			ampOrgId=formBean.getAmpOrgId();
		
		if(formBean.getAmpModalityId()==null || formBean.getAmpModalityId().intValue()==0)
			ampModalityId=All;
		else
			ampModalityId=formBean.getAmpModalityId();
		
		if(formBean.getAmpCurrencyCode()==null || formBean.getAmpCurrencyCode().equals("0"))
		{
			ampCurrency=CurrencyUtil.getAmpcurrency(teamMember.getAppSettings().getCurrencyId());
			ampCurrencyCode=ampCurrency.getCurrencyCode();
			formBean.setAmpCurrencyCode(ampCurrencyCode);
		}
		else
			ampCurrencyCode=formBean.getAmpCurrencyCode();

		//for storing the value of year filter 
		if(formBean.getAmpToYear()==null || formBean.getAmpToYear().intValue()==0)
			toYr=year;
		else
	  		toYr = formBean.getAmpToYear().intValue();

		if(formBean.getAmpFromYear()==null || formBean.getAmpFromYear().intValue()==0)
			fromYr=toYr-2;
		else
	  		fromYr = formBean.getAmpFromYear().intValue();
	  	
//		for storing the values of start date and close date selected from filter
		if(formBean.getStartYear()==null || formBean.getStartYear().intValue()==0)
			startYear=year-15;
		else
			startYear = formBean.getStartYear().intValue();

		if(formBean.getStartMonth()==null || formBean.getStartMonth().intValue()==0)
			startMonth=1;
		else
			startMonth = formBean.getStartMonth().intValue();
		
		if(formBean.getStartDay()==null || formBean.getStartDay().intValue()==0)
			startDay = 1;
		else
			startDay = formBean.getStartDay().intValue();
	
		if(formBean.getCloseYear()==null || formBean.getCloseYear().intValue()==0)
			closeYear=year;
		else
			closeYear = formBean.getCloseYear().intValue();
		
		if(formBean.getCloseMonth()==null || formBean.getCloseMonth().intValue()==0)
			closeMonth=12;
		else
			closeMonth = formBean.getCloseMonth().intValue();
		
		if(formBean.getCloseDay()==null || formBean.getCloseDay().intValue()==0)
			closeDay = 31;
		else
			closeDay = formBean.getCloseDay().intValue();

		if(formBean.getAmpAdjustmentId()==null)
			ampAdjustmentId=Constants.ACTUAL;
		else
			ampAdjustmentId=formBean.getAmpAdjustmentId().intValue();

		if(request.getParameter("view")!=null)
		{
			if(request.getParameter("view").equals("reset"))
			{
				perspective = teamMember.getAppSettings().getPerspective();
				if(perspective.equals("Donor"))
					perspective="DN";
				if(perspective.equals("MOFED"))
					perspective="MA";
				formBean.setPerspectiveFilter(perspective);
				formBean.setAmpAdjustmentId(new Integer(Constants.ACTUAL));
				ampAdjustmentId=Constants.ACTUAL;
				formBean.setAmpStatusId(All);
				ampStatusId=All;
				formBean.setAmpOrgId(All);
				ampOrgId=All;
				formBean.setAmpSectorId(All);
				ampSectorId=All;
				formBean.setAmpModalityId(All);
				ampModalityId=All;
				formBean.setAmpFromYear(All);
				formBean.setAmpToYear(All);
				toYr=year;
				fromYr=toYr-2;
				formBean.setStartYear(All);
				formBean.setStartMonth(All);
				formBean.setStartDay(All);
				formBean.setCloseYear(All);
				formBean.setCloseMonth(All);
				formBean.setCloseDay(All);
				startYear=year-15;
				startMonth=1;
				startDay = 1;
				closeYear=year;
				closeMonth=12;
				closeDay = 31;
				fiscalCalId=teamMember.getAppSettings().getFisCalId().intValue();
				formBean.setFiscalCalId(fiscalCalId);
				formBean.setAmpCurrencyCode(ampCurrencyCode);
				formBean.setAmpLocationId("All");				
				region="All";
				
			}
		}
		
		String startDate=null;
		String closeDate=null;
		if(formBean.getStartYear()!=null && formBean.getStartYear().intValue()>0) 
			startDate = startYear + "-" + startMonth + "-" + startDay;
		if(formBean.getCloseYear()!=null && formBean.getCloseYear().intValue()>0)
			closeDate = closeYear + "-" + closeMonth + "-" + closeDay;

		Collection pageRecords = new ArrayList();
		int page=0;
		if (request.getParameter("page") == null) 
		{
			page = 1;
			reports=ReportUtil.getAmpReportQuarterlyDateRange(ampTeamId,fromYr,toYr,perspective,ampCurrencyCode,ampModalityId,ampStatusId,ampOrgId,ampSectorId,fiscalCalId,startDate,closeDate,region,ampAdjustmentId);
			session.setAttribute("ampReports",reports);

		}
		else 
		{
			page = Integer.parseInt(request.getParameter("page"));
			reports=(ArrayList)session.getAttribute("ampReports");
		}
		int stIndex = ((page - 1) * 10) + 1;
		int edIndex = page * 10;
		if (edIndex > reports.size()) 
		{
			edIndex = reports.size();
		}
		Vector vect = new Vector();
		vect.addAll(reports);
					 
		for (int i = (stIndex-1);i < edIndex;i ++) 
		{
			pageRecords.add(vect.get(i));
		}

		Collection pages = null;
		int numPages=0;
		if (reports.size() > 10) 
		{
			pages = new ArrayList();
			numPages = reports.size()/10;
			if(reports.size()%10>0)
				numPages++;
			for (int i = 0;i < numPages;i++) 
			{
				Integer pageNum=new Integer(i+1);
				pages.add(pageNum);
			}
		 }
	
		formBean.setYrCount( (toYr -fromYr) + 1 );
		
//		---------------Set the Filters along with thier properties -------------------
		FilterProperties filter = new FilterProperties();
		filter.setAmpTeamId(ampTeamId);
		filter.setCurrencyCode(ampCurrencyCode);
		Integer fics = new Integer(fiscalCalId);
		filter.setCalendarId(fics);
		filter.setRegionId(region);
		filter.setModalityId(ampModalityId);
		filter.setOrgId(ampOrgId);
		filter.setStatusId(ampStatusId);
		filter.setSectorId(ampSectorId);
		filter.setFromYear(fromYr);
		filter.setToYear(toYr);
		filter.setStartDate(startDate);
		filter.setCloseDate(closeDate);
		if(perspective.equals("DN"))
			filter.setPerspective("Donor");
		if(perspective.equals("MA"))
			filter.setPerspective("MOFED");
		
		//String setFiltersNames = DbUtil.setFilterDetails(filter);
		//formBean.setFiltersNames(setFiltersNames);
		
		String filterNames[] =new String[2];
		filterNames = DbUtil.setFilterDetails(filter);
		for(int i=0; i< filterNames.length; i++)
		{
			logger.info("Filter HTML : + " + filterNames[i]);
		}
		formBean.setFilter(filterNames);
		int yearRange=(toYr-fromYr)+1;
		formBean.setFiscalYearRange(new ArrayList());
		for(int yr=fromYr;yr<=toYr;yr++)
			formBean.getFiscalYearRange().add(new Integer(yr));
		formBean.setTotalColumns(4*yearRange+6);
//---------------------------------------------------		

		formBean.setPages(pages);
		formBean.setReport(pageRecords);
		formBean.setPage(new Integer(page));
		formBean.setAllReports(reports);
		formBean.setFilterCnt(filterCnt);
		AmpTeam ampTeam=TeamUtil.getAmpTeam(ampTeamId);
		formBean.setReportName("Quarterly Team Project Detail Report");
		formBean.setWorkspaceType(ampTeam.getType());
		formBean.setWorkspaceName(ampTeam.getName());
		if(perspective.equals("DN"))
			formBean.setPerspective("Donor");
		if(perspective.equals("MA"))
			formBean.setPerspective("MOFED");
		return mapping.findForward("forward");
	}
}
