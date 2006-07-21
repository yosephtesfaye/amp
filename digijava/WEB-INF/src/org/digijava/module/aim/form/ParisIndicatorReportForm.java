/*
 * Created on 12/05/2006
 * @author akashs
 * 
 */
package org.digijava.module.aim.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ParisIndicatorReportForm extends ActionForm {
	
	private Collection indicatorsColl = null;
	private String indicatorId = null;
	private String indicatorCode = null;
	private String indicatorName = null;
	private String numColsCalculated = null;	// number of columns in one donor-row
	
	// holds collection of ParisIndicator helper objects for first report-table of all but indicator-7 
	private Collection donorsColl = null;
	// holds collection of ParisIndicator helper objects for second report-table of indicator-5a & 5b
	private ArrayList donorsCollIndc5 = null;
	
	// Filters for ParisIndicator reports
	private Integer startYear = null;
	private Integer closeYear = null;
	private String currency = null;
	private String perspective = null;
	private String orgGroup = null;
	private String status = null;			
	private String termAssist = null;			// defunct
	private String financingInstrument = null;
	private Collection yearColl = null;
	private Collection currencyColl = null;
	private Collection orgGroupColl = null;
	private Collection statusColl = null;
	private Collection termAssistColl = null;
	private Collection financingInstrumentColl = null;
	
	private Boolean reset = Boolean.FALSE;
	private Boolean filterFlag = Boolean.FALSE;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		if (reset.booleanValue()) {
			startYear = new Integer(Calendar.getInstance().get(Calendar.YEAR) - 2);
			closeYear = new Integer(Calendar.getInstance().get(Calendar.YEAR));
			currency = "USD";
			perspective = "ma";
			orgGroup = "all";
			status = "all";
			termAssist = "all";
			financingInstrument = "all";
			numColsCalculated = "4";
			reset = Boolean.FALSE;
			filterFlag = Boolean.FALSE;
		}
	}
	
	/**
	 * @return Returns the indicatorsColl.
	 */
	public Collection getIndicatorsColl() {
		return indicatorsColl;
	}
	/**
	 * @param indicatorsColl The indicatorsColl to set.
	 */
	public void setIndicatorsColl(Collection indicatorsColl) {
		this.indicatorsColl = indicatorsColl;
	}
	/**
	 * @return Returns the indicatorId.
	 */
	public String getIndicatorId() {
		return indicatorId;
	}
	/**
	 * @param indicatorId The indicatorId to set.
	 */
	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}
	/**
	 * @return Returns the donorsColl.
	 */
	public Collection getDonorsColl() {
		return donorsColl;
	}
	/**
	 * @param donorsColl The donorsColl to set.
	 */
	public void setDonorsColl(Collection donorsColl) {
		this.donorsColl = donorsColl;
	}
	
	/**
	 * @return Returns the closeYear.
	 */
	public Integer getCloseYear() {
		return closeYear;
	}
	/**
	 * @param closeYear The closeYear to set.
	 */
	public void setCloseYear(Integer closeYear) {
		this.closeYear = closeYear;
	}
	/**
	 * @return Returns the currencyColl.
	 */
	public Collection getCurrencyColl() {
		return currencyColl;
	}
	/**
	 * @param currencyColl The currencyColl to set.
	 */
	public void setCurrencyColl(Collection currencyColl) {
		this.currencyColl = currencyColl;
	}
	/**
	 * @return Returns the currencyId.
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currencyId The currencyId to set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return Returns the financingInstrument.
	 */
	public String getFinancingInstrument() {
		return financingInstrument;
	}
	/**
	 * @param financingInstrument The financingInstrument to set.
	 */
	public void setFinancingInstrument(String financingInstrument) {
		this.financingInstrument = financingInstrument;
	}
	/**
	 * @return Returns the financingInstrumentColl.
	 */
	public Collection getFinancingInstrumentColl() {
		return financingInstrumentColl;
	}
	/**
	 * @param financingInstrumentColl The financingInstrumentColl to set.
	 */
	public void setFinancingInstrumentColl(Collection financingInstrumentColl) {
		this.financingInstrumentColl = financingInstrumentColl;
	}
	/*
	public String getOrgGroup() {
		return orgGroup;
	}
	public void setOrgGroup(String orgGroup) {
		this.orgGroup = orgGroup;
	}*/
	/**
	 * @return Returns the orgGroupColl.
	 */
	public Collection getOrgGroupColl() {
		return orgGroupColl;
	}
	/**
	 * @param orgGroupColl The orgGroupColl to set.
	 */
	public void setOrgGroupColl(Collection orgGroupColl) {
		this.orgGroupColl = orgGroupColl;
	}
	/**
	 * @return Returns the startYear.
	 */
	public Integer getStartYear() {
		return startYear;
	}
	/**
	 * @param startYear The startYear to set.
	 */
	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the statusColl.
	 */
	public Collection getStatusColl() {
		return statusColl;
	}
	/**
	 * @param statusColl The statusColl to set.
	 */
	public void setStatusColl(Collection statusColl) {
		this.statusColl = statusColl;
	}
	/*
	public String getTermAssist() {
		return termAssist;
	}
	public void setTermAssist(String termAssist) {
		this.termAssist = termAssist;
	} */
	/**
	 * @return Returns the termAssistColl.
	 */
	public Collection getTermAssistColl() {
		return termAssistColl;
	}
	/**
	 * @param termAssistColl The termAssistColl to set.
	 */
	public void setTermAssistColl(Collection termAssistColl) {
		this.termAssistColl = termAssistColl;
	}
	/**
	 * @return Returns the yearColl.
	 */
	public Collection getYearColl() {
		return yearColl;
	}
	/**
	 * @param yearColl The yearColl to set.
	 */
	public void setYearColl(Collection yearColl) {
		this.yearColl = yearColl;
	}
	/**
	 * @return Returns the indicatorName.
	 */
	public String getIndicatorName() {
		return indicatorName;
	}
	/**
	 * @param indicatorName The indicatorName to set.
	 */
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	/**
	 * @return Returns the indicatorCode.
	 */
	public String getIndicatorCode() {
		return indicatorCode;
	}
	/**
	 * @param indicatorCode The indicatorCode to set.
	 */
	public void setIndicatorCode(String indicatorCode) {
		this.indicatorCode = indicatorCode;
	}
	
	/**
	 * @return Returns the reset.
	 */
	public Boolean getReset() {
		return reset;
	}
	/**
	 * @param reset The reset to set.
	 */
	public void setReset(Boolean reset) {
		this.reset = reset;
	}
	/**
	 * @return Returns the filterFlag.
	 */
	public Boolean getFilterFlag() {
		return filterFlag;
	}
	/**
	 * @param filterFlag The filterFlag to set.
	 */
	public void setFilterFlag(Boolean filterFlag) {
		this.filterFlag = filterFlag;
	}
	/**
	 * @return Returns the donorsCollIndc5.
	 */
	public ArrayList getDonorsCollIndc5() {
		return donorsCollIndc5;
	}
	/**
	 * @param donorsCollIndc5 The donorsCollIndc5 to set.
	 */
	public void setDonorsCollIndc5(ArrayList donorsCollIndc5) {
		this.donorsCollIndc5 = donorsCollIndc5;
	}
	/**
	 * @return Returns the numColsCalculated.
	 */
	public String getNumColsCalculated() {
		return numColsCalculated;
	}
	/**
	 * @param numColsCalculated The numColsCalculated to set.
	 */
	public void setNumColsCalculated(String numColsCalculated) {
		this.numColsCalculated = numColsCalculated;
	}

	public String getPerspective() {
		return perspective;
	}

	public void setPerspective(String perspective) {
		this.perspective = perspective;
	}

	public String getOrgGroup() {
		return orgGroup;
	}

	public void setOrgGroup(String orgGroup) {
		this.orgGroup = orgGroup;
	}

	public String getTermAssist() {
		return termAssist;
	}

	public void setTermAssist(String termAssist) {
		this.termAssist = termAssist;
	}
}
