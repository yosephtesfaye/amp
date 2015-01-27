// Generated by delombok at Mon Mar 24 00:10:06 EET 2014
/**
 * 
 */
package org.digijava.module.aim.form.reportwizard;

import java.util.*;
import org.apache.struts.action.ActionForm;
import org.digijava.module.aim.dbentity.AmpColumns;
import org.digijava.module.aim.dbentity.AmpMeasures;
import org.digijava.module.aim.dbentity.AmpTeamMember;

/**
 * @author alex
 */
public class ReportWizardForm extends ActionForm {
	private Long reportId = null;
	private AmpTeamMember ampTeamMember = null;
	private Map<String, List<AmpColumns>> ampTreeColumns;
	private Collection<AmpMeasures> ampMeasures = null;
	private String reportType = "donor";
	private Boolean desktopTab = false;
	private Boolean hideActivities = false;
	private String reportPeriod = "A";
	
	/**
	 * report title in current language
	 * <b>DO NOT EVER READ IT FOR INCLUSION INTO AmpReports.name - ONLY USE IT FOR TRANSMITTING "DEFAULT LANGUAGE" NAME TO JAVASCRIPT-SIDE
	 */
	private String reportTitle = "";
	private String reportDescription = "";
	private String originalTitle = "";
	private Long[] selectedColumns = null;
	private Long[] selectedHierarchies = null;
	private Long[] selectedMeasures = null;
	private Boolean duplicateName = false;
	private Boolean noReportNameSupplied = false;
	private Boolean overwritingForeignReport = false;
	private Boolean publicReport = false;
	private Boolean workspaceLinked = false;
	private Boolean alsoShowPledges = false;
	private Boolean reportBeingEdited = false;
	private Boolean onlyShowProjectsRelatedPledges = false;
	private Boolean useFilters = false;
	private Boolean allowEmptyFundingColumns = false;
	private Boolean onePager = false;
	private String projecttitle = "Project Title";
	private Boolean budgetExporter = false;
	private Long reportCategory = new Long(0);
	private Boolean forceNameOverwrite = false;
	
	@java.lang.SuppressWarnings("all")
	public ReportWizardForm() {
	}
	
	@java.lang.SuppressWarnings("all")
	public Long getReportId() {
		return this.reportId;
	}
	
	@java.lang.SuppressWarnings("all")
	public AmpTeamMember getAmpTeamMember() {
		return this.ampTeamMember;
	}
	
	@java.lang.SuppressWarnings("all")
	public Map<String, List<AmpColumns>> getAmpTreeColumns() {
		return this.ampTreeColumns;
	}
	
	@java.lang.SuppressWarnings("all")
	public Collection<AmpMeasures> getAmpMeasures() {
		return this.ampMeasures;
	}
	
	@java.lang.SuppressWarnings("all")
	public String getReportType() {
		return this.reportType;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getDesktopTab() {
		return this.desktopTab;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getHideActivities() {
		return this.hideActivities;
	}
	
	@java.lang.SuppressWarnings("all")
	public String getReportPeriod() {
		return this.reportPeriod;
	}
	
	/**
	 * this should NOT normally be called server-side - only use it for server->client data transmission
	 * @return
	 */
	public String getReportTitle() {
		return this.reportTitle;
	}
	
	@java.lang.SuppressWarnings("all")
	public String getReportDescription() {
		return this.reportDescription;
	}
	
	@java.lang.SuppressWarnings("all")
	public String getOriginalTitle() {
		return this.originalTitle;
	}
	
	@java.lang.SuppressWarnings("all")
	public Long[] getSelectedColumns() {
		return this.selectedColumns;
	}
	
	@java.lang.SuppressWarnings("all")
	public Long[] getSelectedHierarchies() {
		return this.selectedHierarchies;
	}
	
	@java.lang.SuppressWarnings("all")
	public Long[] getSelectedMeasures() {
		return this.selectedMeasures;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getDuplicateName() {
		return this.duplicateName;
	}
	@java.lang.SuppressWarnings("all")
	public Boolean getnoReportNameSupplied() {
		return this.noReportNameSupplied;
	}	
	@java.lang.SuppressWarnings("all")
	public Boolean getOverwritingForeignReport() {
		return this.overwritingForeignReport;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getPublicReport() {
		return this.publicReport;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getWorkspaceLinked() {
		return this.workspaceLinked;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getUseFilters() {
		return this.useFilters;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getAllowEmptyFundingColumns() {
		return this.allowEmptyFundingColumns;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getOnePager() {
		return this.onePager;
	}
	
	@java.lang.SuppressWarnings("all")
	public String getProjecttitle() {
		return this.projecttitle;
	}
	
	@java.lang.SuppressWarnings("all")
	public Boolean getBudgetExporter() {
		return this.budgetExporter;
	}
	
	@java.lang.SuppressWarnings("all")
	public Long getReportCategory() {
		return this.reportCategory;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setReportId(final Long reportId) {
		this.reportId = reportId;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setAmpTeamMember(final AmpTeamMember ampTeamMember) {
		this.ampTeamMember = ampTeamMember;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setAmpTreeColumns(final Map<String, List<AmpColumns>> ampTreeColumns) {
		this.ampTreeColumns = ampTreeColumns;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setAmpMeasures(final Collection<AmpMeasures> ampMeasures) {
		this.ampMeasures = ampMeasures;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setReportType(final String reportType) {
		this.reportType = reportType;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setDesktopTab(final Boolean desktopTab) {
		this.desktopTab = desktopTab;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setHideActivities(final Boolean hideActivities) {
		this.hideActivities = hideActivities;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setReportPeriod(final String reportPeriod) {
		this.reportPeriod = reportPeriod;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setReportTitle(final String reportTitle) {
		this.reportTitle = reportTitle;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setReportDescription(final String reportDescription) {
		this.reportDescription = reportDescription;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setOriginalTitle(final String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setSelectedColumns(final Long[] selectedColumns) {
		this.selectedColumns = selectedColumns;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setSelectedHierarchies(final Long[] selectedHierarchies) {
		this.selectedHierarchies = selectedHierarchies;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setSelectedMeasures(final Long[] selectedMeasures) {
		this.selectedMeasures = selectedMeasures;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setDuplicateName(final Boolean duplicateName) {
		this.duplicateName = duplicateName;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setnoReportNameSupplied(final Boolean noReportNameSupplied) {
		this.noReportNameSupplied = noReportNameSupplied;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setOverwritingForeignReport(final Boolean overwritingForeignReport) {
		this.overwritingForeignReport = overwritingForeignReport;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setPublicReport(final Boolean publicReport) {
		this.publicReport = publicReport;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setWorkspaceLinked(final Boolean workspaceLinked) {
		this.workspaceLinked = workspaceLinked;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setUseFilters(final Boolean useFilters) {
		this.useFilters = useFilters;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setAllowEmptyFundingColumns(final Boolean allowEmptyFundingColumns) {
		this.allowEmptyFundingColumns = allowEmptyFundingColumns;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setOnePager(final Boolean onePager) {
		this.onePager = onePager;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setProjecttitle(final String projecttitle) {
		this.projecttitle = projecttitle;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setBudgetExporter(final Boolean budgetExporter) {
		this.budgetExporter = budgetExporter;
	}
	
	@java.lang.SuppressWarnings("all")
	public void setReportCategory(final Long reportCategory) {
		this.reportCategory = reportCategory;
	}
	
	public boolean getForceNameOverwrite(){
		return this.forceNameOverwrite;
	}
	
	public void setForceNameOverwrite(boolean forceNameOverwrite){
		this.forceNameOverwrite = forceNameOverwrite;
	}
	
	public boolean getAlsoShowPledges() {
		return this.alsoShowPledges;
	}
	
	public void setAlsoShowPledges(Boolean alsoShowPledges) {
		this.alsoShowPledges = alsoShowPledges == null ? false : alsoShowPledges;
	}

	public boolean getReportBeingEdited() {
		return this.reportBeingEdited;
	}	
	
	public void setReportBeingEdited(Boolean reportBeingEdited) {
		this.reportBeingEdited = reportBeingEdited == null ? false : reportBeingEdited;
	}
	
	
	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public boolean equals(final java.lang.Object o) {
		if (o == this) return true;
		if (!(o instanceof ReportWizardForm)) return false;
		final ReportWizardForm other = (ReportWizardForm)o;
		if (!other.canEqual((java.lang.Object)this)) return false;
		final java.lang.Object this$reportId = this.getReportId();
		final java.lang.Object other$reportId = other.getReportId();
		if (this$reportId == null ? other$reportId != null : !this$reportId.equals(other$reportId)) return false;
		final java.lang.Object this$ampTeamMember = this.getAmpTeamMember();
		final java.lang.Object other$ampTeamMember = other.getAmpTeamMember();
		if (this$ampTeamMember == null ? other$ampTeamMember != null : !this$ampTeamMember.equals(other$ampTeamMember)) return false;
		final java.lang.Object this$ampTreeColumns = this.getAmpTreeColumns();
		final java.lang.Object other$ampTreeColumns = other.getAmpTreeColumns();
		if (this$ampTreeColumns == null ? other$ampTreeColumns != null : !this$ampTreeColumns.equals(other$ampTreeColumns)) return false;
		final java.lang.Object this$ampMeasures = this.getAmpMeasures();
		final java.lang.Object other$ampMeasures = other.getAmpMeasures();
		if (this$ampMeasures == null ? other$ampMeasures != null : !this$ampMeasures.equals(other$ampMeasures)) return false;
		final java.lang.Object this$reportType = this.getReportType();
		final java.lang.Object other$reportType = other.getReportType();
		if (this$reportType == null ? other$reportType != null : !this$reportType.equals(other$reportType)) return false;
		final java.lang.Object this$desktopTab = this.getDesktopTab();
		final java.lang.Object other$desktopTab = other.getDesktopTab();
		if (this$desktopTab == null ? other$desktopTab != null : !this$desktopTab.equals(other$desktopTab)) return false;
		final java.lang.Object this$hideActivities = this.getHideActivities();
		final java.lang.Object other$hideActivities = other.getHideActivities();
		if (this$hideActivities == null ? other$hideActivities != null : !this$hideActivities.equals(other$hideActivities)) return false;
		final java.lang.Object this$reportPeriod = this.getReportPeriod();
		final java.lang.Object other$reportPeriod = other.getReportPeriod();
		if (this$reportPeriod == null ? other$reportPeriod != null : !this$reportPeriod.equals(other$reportPeriod)) return false;
		final java.lang.Object this$reportTitle = this.getReportTitle();
		final java.lang.Object other$reportTitle = other.getReportTitle();
		if (this$reportTitle == null ? other$reportTitle != null : !this$reportTitle.equals(other$reportTitle)) return false;
		final java.lang.Object this$reportDescription = this.getReportDescription();
		final java.lang.Object other$reportDescription = other.getReportDescription();
		if (this$reportDescription == null ? other$reportDescription != null : !this$reportDescription.equals(other$reportDescription)) return false;
		final java.lang.Object this$originalTitle = this.getOriginalTitle();
		final java.lang.Object other$originalTitle = other.getOriginalTitle();
		if (this$originalTitle == null ? other$originalTitle != null : !this$originalTitle.equals(other$originalTitle)) return false;
		if (!java.util.Arrays.deepEquals(this.getSelectedColumns(), other.getSelectedColumns())) return false;
		if (!java.util.Arrays.deepEquals(this.getSelectedHierarchies(), other.getSelectedHierarchies())) return false;
		if (!java.util.Arrays.deepEquals(this.getSelectedMeasures(), other.getSelectedMeasures())) return false;
		final java.lang.Object this$duplicateName = this.getDuplicateName();
		final java.lang.Object other$duplicateName = other.getDuplicateName();
		if (this$duplicateName == null ? other$duplicateName != null : !this$duplicateName.equals(other$duplicateName)) return false;
		final java.lang.Object this$overwritingForeignReport = this.getOverwritingForeignReport();
		final java.lang.Object other$overwritingForeignReport = other.getOverwritingForeignReport();
		if (this$overwritingForeignReport == null ? other$overwritingForeignReport != null : !this$overwritingForeignReport.equals(other$overwritingForeignReport)) return false;
		final java.lang.Object this$publicReport = this.getPublicReport();
		final java.lang.Object other$publicReport = other.getPublicReport();
		if (this$publicReport == null ? other$publicReport != null : !this$publicReport.equals(other$publicReport)) return false;
		final java.lang.Object this$workspaceLinked = this.getWorkspaceLinked();
		final java.lang.Object other$workspaceLinked = other.getWorkspaceLinked();
		if (this$workspaceLinked == null ? other$workspaceLinked != null : !this$workspaceLinked.equals(other$workspaceLinked)) return false;
		final java.lang.Object this$useFilters = this.getUseFilters();
		final java.lang.Object other$useFilters = other.getUseFilters();
		if (this$useFilters == null ? other$useFilters != null : !this$useFilters.equals(other$useFilters)) return false;
		final java.lang.Object this$allowEmptyFundingColumns = this.getAllowEmptyFundingColumns();
		final java.lang.Object other$allowEmptyFundingColumns = other.getAllowEmptyFundingColumns();
		if (this$allowEmptyFundingColumns == null ? other$allowEmptyFundingColumns != null : !this$allowEmptyFundingColumns.equals(other$allowEmptyFundingColumns)) return false;
		final java.lang.Object this$onePager = this.getOnePager();
		final java.lang.Object other$onePager = other.getOnePager();
		if (this$onePager == null ? other$onePager != null : !this$onePager.equals(other$onePager)) return false;
		final java.lang.Object this$projecttitle = this.getProjecttitle();
		final java.lang.Object other$projecttitle = other.getProjecttitle();
		if (this$projecttitle == null ? other$projecttitle != null : !this$projecttitle.equals(other$projecttitle)) return false;
		final java.lang.Object this$budgetExporter = this.getBudgetExporter();
		final java.lang.Object other$budgetExporter = other.getBudgetExporter();
		if (this$budgetExporter == null ? other$budgetExporter != null : !this$budgetExporter.equals(other$budgetExporter)) return false;
		final java.lang.Object this$reportCategory = this.getReportCategory();
		final java.lang.Object other$reportCategory = other.getReportCategory();
		if (this$reportCategory == null ? other$reportCategory != null : !this$reportCategory.equals(other$reportCategory)) return false;
		return true;
	}
	
	@java.lang.SuppressWarnings("all")
	public boolean canEqual(final java.lang.Object other) {
		return other instanceof ReportWizardForm;
	}
	
	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final java.lang.Object $reportId = this.getReportId();
		result = result * PRIME + ($reportId == null ? 0 : $reportId.hashCode());
		final java.lang.Object $ampTeamMember = this.getAmpTeamMember();
		result = result * PRIME + ($ampTeamMember == null ? 0 : $ampTeamMember.hashCode());
		final java.lang.Object $ampTreeColumns = this.getAmpTreeColumns();
		result = result * PRIME + ($ampTreeColumns == null ? 0 : $ampTreeColumns.hashCode());
		final java.lang.Object $ampMeasures = this.getAmpMeasures();
		result = result * PRIME + ($ampMeasures == null ? 0 : $ampMeasures.hashCode());
		final java.lang.Object $reportType = this.getReportType();
		result = result * PRIME + ($reportType == null ? 0 : $reportType.hashCode());
		final java.lang.Object $desktopTab = this.getDesktopTab();
		result = result * PRIME + ($desktopTab == null ? 0 : $desktopTab.hashCode());
		final java.lang.Object $hideActivities = this.getHideActivities();
		result = result * PRIME + ($hideActivities == null ? 0 : $hideActivities.hashCode());
		final java.lang.Object $reportPeriod = this.getReportPeriod();
		result = result * PRIME + ($reportPeriod == null ? 0 : $reportPeriod.hashCode());
		final java.lang.Object $reportTitle = this.getReportTitle();
		result = result * PRIME + ($reportTitle == null ? 0 : $reportTitle.hashCode());
		final java.lang.Object $reportDescription = this.getReportDescription();
		result = result * PRIME + ($reportDescription == null ? 0 : $reportDescription.hashCode());
		final java.lang.Object $originalTitle = this.getOriginalTitle();
		result = result * PRIME + ($originalTitle == null ? 0 : $originalTitle.hashCode());
		result = result * PRIME + java.util.Arrays.deepHashCode(this.getSelectedColumns());
		result = result * PRIME + java.util.Arrays.deepHashCode(this.getSelectedHierarchies());
		result = result * PRIME + java.util.Arrays.deepHashCode(this.getSelectedMeasures());
		final java.lang.Object $duplicateName = this.getDuplicateName();
		result = result * PRIME + ($duplicateName == null ? 0 : $duplicateName.hashCode());
		final java.lang.Object $overwritingForeignReport = this.getOverwritingForeignReport();
		result = result * PRIME + ($overwritingForeignReport == null ? 0 : $overwritingForeignReport.hashCode());
		final java.lang.Object $publicReport = this.getPublicReport();
		result = result * PRIME + ($publicReport == null ? 0 : $publicReport.hashCode());
		final java.lang.Object $workspaceLinked = this.getWorkspaceLinked();
		result = result * PRIME + ($workspaceLinked == null ? 0 : $workspaceLinked.hashCode());
		final java.lang.Object $useFilters = this.getUseFilters();
		result = result * PRIME + ($useFilters == null ? 0 : $useFilters.hashCode());
		final java.lang.Object $allowEmptyFundingColumns = this.getAllowEmptyFundingColumns();
		result = result * PRIME + ($allowEmptyFundingColumns == null ? 0 : $allowEmptyFundingColumns.hashCode());
		final java.lang.Object $onePager = this.getOnePager();
		result = result * PRIME + ($onePager == null ? 0 : $onePager.hashCode());
		final java.lang.Object $projecttitle = this.getProjecttitle();
		result = result * PRIME + ($projecttitle == null ? 0 : $projecttitle.hashCode());
		final java.lang.Object $budgetExporter = this.getBudgetExporter();
		result = result * PRIME + ($budgetExporter == null ? 0 : $budgetExporter.hashCode());
		final java.lang.Object $reportCategory = this.getReportCategory();
		result = result * PRIME + ($reportCategory == null ? 0 : $reportCategory.hashCode());
		return result;
	}
	
	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public java.lang.String toString() {
		return "ReportWizardForm(reportId=" + this.getReportId() + ", ampTeamMember=" + this.getAmpTeamMember() + ", ampTreeColumns=" + this.getAmpTreeColumns() + ", ampMeasures=" + this.getAmpMeasures() + ", reportType=" + this.getReportType() + ", desktopTab=" + this.getDesktopTab() + ", hideActivities=" + this.getHideActivities() + ", reportPeriod=" + this.getReportPeriod() + ", reportTitle=" + this.getReportTitle() + ", reportDescription=" + this.getReportDescription() + ", originalTitle=" + this.getOriginalTitle() + ", selectedColumns=" + java.util.Arrays.deepToString(this.getSelectedColumns()) + ", selectedHierarchies=" + java.util.Arrays.deepToString(this.getSelectedHierarchies()) + ", selectedMeasures=" + java.util.Arrays.deepToString(this.getSelectedMeasures()) + ", duplicateName=" + this.getDuplicateName() + ", overwritingForeignReport=" + this.getOverwritingForeignReport() + ", publicReport=" + this.getPublicReport() + ", workspaceLinked=" + this.getWorkspaceLinked() + ", useFilters=" + this.getUseFilters() + ", allowEmptyFundingColumns=" + this.getAllowEmptyFundingColumns() + ", onePager=" + this.getOnePager() + ", projecttitle=" + this.getProjecttitle() + ", budgetExporter=" + this.getBudgetExporter() + ", reportCategory=" + this.getReportCategory() + ")";
	}
}