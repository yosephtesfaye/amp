<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/taglib/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglib/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/taglib/struts-html" prefix="html" %>
<%@ taglib uri="/taglib/digijava" prefix="digi" %>
<%@ taglib uri="/taglib/jstl-core" prefix="c" %>

<DIV id="TipLayer"
  style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>


<jsp:useBean id="bcparams" type="java.util.Map" class="java.util.HashMap"/>
<c:set target="${bcparams}" property="tId" value="-1"/>
<c:set target="${bcparams}" property="dest" value="teamLead"/>

<script type="text/javascript">

<!--
	function listReports()
	{
		document.aimTeamReportsForm.addReport.value="List of Unassigned Reports";
		document.aimTeamReportsForm.action="/updateTeamReports.do";
		document.aimTeamReportsForm.submit();
	}


	function validate() {
		<c:set var="message">
        <digi:trn key="aim:teamWorkspaceSetup:selectReportToRemove">
        Please choose a report to remove
        </digi:trn>
        </c:set>
		if (document.aimTeamReportsForm.selReports.checked != null) {
			if (document.aimTeamReportsForm.selReports.checked == false) {				
				alert("${message}");
				return false;
			}
		} else {
			var length = document.aimTeamReportsForm.selReports.length;
			var flag = 0;
			for (i = 0; i < length; i ++) {
				if (document.aimTeamReportsForm.selReports[i].checked == true) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				alert("${message}");
				return false;
			}
		}
		return true;
	}
        function confirmDelete() {
		var valid = validate();
		if (valid == true) {
                    <c:set var="message">
                  <digi:trn key="aim:teamWorkspaceSetup:removeSelectedReports">
                  Are you sure you want to remove selected reports?
                  </digi:trn>
                  </c:set>
			var flag = confirm("${message}");
			if(flag == false)
			  return false;
			else
				return true;
		} else {
			return false;
		}
	}


-->

</script>

<digi:instance property="aimTeamReportsForm" />

<digi:form action="/updateTeamReports.do" method="post">
<html:hidden property="addReport"/>
<html:hidden property="showReportList"/>
<table width="100%" cellpadding="0" cellspacing="0" vAlign="top" align="left">
<tr><td width="100%" vAlign="top" align="left">
<jsp:include page="teamPagesHeader.jsp" flush="true" />
</td></tr>

									<c:if test="${aimTeamReportsForm.showReportList == true}">
										<c:set var="selectedTab" value="3" scope="request"/>
									</c:if>
									<c:if test="${aimTeamReportsForm.showReportList == false}">
										<c:set var="selectedTab" value="8" scope="request"/>
									</c:if>									
									<c:set var="selectedSubTab" value="0" scope="request"/>
										
										<table width="1000" border="0" cellspacing="0" cellpadding="0" align="center">
											<tr>
											<td>
												<div class="breadcrump_cont">
													<span class="sec_name">
														<digi:trn key="aim:teamWorkspaceSetup">Team Workspace Setup</digi:trn>
													</span>
													
													<span class="breadcrump_sep">|</span>
													<digi:link href="/viewMyDesktop.do" title="${translation}" styleClass="l_sm">
														<digi:trn key="aim:portfolio">Portfolio</digi:trn>
													</digi:link>
													<span class="breadcrump_sep"><b>�</b></span>
													<c:set var="translation">
														<digi:trn key="aim:clickToViewWorkspaceOverview">Click here to view Workspace Overview</digi:trn>
													</c:set>
													<digi:link href="/workspaceOverview.do" name="bcparams" styleClass="l_sm" title="${translation}">
													<digi:trn key="aim:teamWorkspaceSetup">Team Workspace Setup</digi:trn></digi:link>
													<span class="breadcrump_sep"><b>�</b></span>
													<span class="bread_sel">
														<c:if test="${aimTeamReportsForm.showReportList == true}">
															<digi:trn key="aim:reportList">Report List</digi:trn>
														</c:if>
														<c:if test="${aimTeamReportsForm.showReportList == false}">
															<digi:trn key="aim:tabsList">Tab List</digi:trn>
														</c:if>	
													</span>
												</div>
											</td>
										</tr>
										<tr>
											<td valign="top">
												<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
										
										
									<jsp:include page="teamSetupMenu.jsp" flush="true" />
									
									<br>
									<div id="private">
										<img src= "/repository/contentrepository/view/images/make_public.gif">
										<c:if test="${aimTeamReportsForm.showReportList == true}">
											<digi:trn key="aim:teamReportListViewableByAllWorkspaceMembers">
												Indicates the report is viewable by all workspace members.
											</digi:trn>
										</c:if>
										<c:if test="${aimTeamReportsForm.showReportList == false}">
											<digi:trn key="aim:teamTabListViewableByAllWorkspaceMembers">
												Indicates the tab is viewable by all workspace members.
											</digi:trn>
										</c:if>
										<br/>
										<digi:trn key="aim:clicktomakethisprivate">Click here to make this private</digi:trn>	
									</div>
									<div id="private">
										<img src= "/repository/contentrepository/view/images/make_private.gif">
										<c:if test="${aimTeamReportsForm.showReportList == true}">
											<digi:trn key="aim:teamReportListViewableByManagerdAndCreator">
											Indicates the report is viewable by the Workspace Manager and the Creator of the Report
											</digi:trn>
										</c:if>
										<c:if test="${aimTeamReportsForm.showReportList == false}">
											<digi:trn key="aim:teamTabListViewableByManagerAndCreator">
											Indicates the tab  viewable by the Workspace Manager and the Creator of the Report
											</digi:trn>
										</c:if>
										<br>
										<digi:trn key="aim:clicktomakethispublic">Click here to make this public</digi:trn>
									</div>
						
									<table class="inside normal" width="100%" cellpadding="0" cellspacing="0">
										<tr>
									  	<td width="5" align="center" background="/TEMPLATE/ampTemplate/img_2/ins_bg.gif" class="inside">
									  		<input type="checkbox" id="checkAll">
									  	</td>
									    <td width=25% background="/TEMPLATE/ampTemplate/img_2/ins_bg.gif" class="inside">
									    	<b class="ins_title">
									    		<c:if test="${aimTeamReportsForm.showReportList == true}">
														<digi:trn key="aim:reportListWorkspace">List of Reports in the Workspace</digi:trn>
													</c:if>
													<c:if test="${aimTeamReportsForm.showReportList == false}">
														<digi:trn key="aim:tabListWorkspace">List of Tabs in the Workspace</digi:trn>
													</c:if>
									    	</b>
									    </td>
									    <td width=15% background="/TEMPLATE/ampTemplate/img_2/ins_bg.gif" class="inside">
									    	<b class="ins_title">
									    		<digi:trn key="aim:reportOwnerName">Owner</digi:trn>
									    	</b>
									    </td>
									    <td width=15% background="/TEMPLATE/ampTemplate/img_2/ins_bg.gif" class="inside">
									    	<b class="ins_title">
									    		<digi:trn key="aim:reportType">Type</digi:trn>
									    	</b>
									    </td>
									    <td width=15% background="/TEMPLATE/ampTemplate/img_2/ins_bg.gif" class="inside">
									    	<b class="ins_title">
									    		<digi:trn key="aim:hierarchies">Hierarchies</digi:trn>
									    	</b>
									    </td>
									    <td width=18% background="/TEMPLATE/ampTemplate/img_2/ins_bg.gif" class="inside">
									    	&nbsp;
									    </td>
									     <td width=7% background="/TEMPLATE/ampTemplate/img_2/ins_bg.gif" class="inside">
									    	<b class="ins_title">
									    		<digi:trn key="aim:visibility">Visibility</digi:trn>
									    	</b>
									    </td>
										</tr>
										<logic:empty name="aimTeamReportsForm" property="reports">
											<tr>
												<td class="inside">
													<c:if test="${aimTeamReportsForm.showReportList == true}">
														<digi:trn key="aim:noReportsPresent">No reports present</digi:trn>
													</c:if>
													<c:if test="${aimTeamReportsForm.showReportList == false}">
														<digi:trn key="aim:noTabsPresent">No tabs present</digi:trn>
													</c:if>															
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="aimTeamReportsForm" property="reports">
											<logic:iterate name="aimTeamReportsForm" property="reports" id="reports" type="org.digijava.module.aim.helper.ReportsCollection">
												<bean:define id="ampReports" name="reports" property="report" type="org.digijava.module.aim.dbentity.AmpReports" />
													<tr>
														<td width="5" align="center" class="inside">
															<html:multibox property="selReports" >
																<bean:write name="ampReports" property="ampReportId" />
															</html:multibox>
														</td>
														<td class="inside">
															<digi:trn key="aim:reportMemberSpecificName:${ampReports.name}"><bean:write name="ampReports" property="name" /></digi:trn>
														</td>
														<td class="inside">
															<logic:present name="ampReports" property="ownerId">
                              	<bean:write name="ampReports" property="ownerId.user.name" />
                              </logic:present>
														</td>
														<td class="inside">
															<li>
                                  <%
                                    if (ampReports.getType()!=null && ampReports.getType().equals(new Long(1))) {
                                  %>
                                      <digi:trn key="aim:donorType">donor</digi:trn>
                                <%
                                    }
                                    else if (ampReports.getType()!=null && ampReports.getType().equals(new Long (3))){
                                %>
                                      <digi:trn key="aim:regionalType">regional</digi:trn>
                                <%
                                    }
                                    else if (ampReports.getType()!=null && ampReports.getType().equals(new Long(2))){
                                %>
                                      <digi:trn key="aim:componentType">component</digi:trn>
                                <%
                                    }
                                    else if (ampReports.getType()!=null && ampReports.getType().equals(new Long(4))){
                                %>
                                      <digi:trn key="aim:contributionType">contribution</digi:trn>
                                <%}%>
                            </li>
                              <logic:equal name="ampReports" property="drilldownTab" value="true">
                                <li>
                                  <digi:trn key="aim:typeDrilldownTab">Desktop Tab</digi:trn>
                                </li>
                              </logic:equal>
                              <logic:equal name="ampReports" property="publicReport" value="true">
                                <li>
                                  <digi:trn key="aim:typePublicReport">Public Report</digi:trn>
                                </li>
                              </logic:equal>
                              <logic:equal name="ampReports" property="hideActivities" value="true">
                                <li>
                                  <digi:trn key="aim:typeSummaryReport">Summary Report</digi:trn>
                                </li>
                              </logic:equal>                                  
                              <logic:equal name="ampReports" property="options" value="A">
                                <li>
                                	<digi:trn key="aim:annualreport">Annual</digi:trn>
                                </li>
                              </logic:equal>
                              <logic:equal name="ampReports" property="options" value="Q">
                                <li>
                                	<digi:trn key="aim:quarterlyreport">Quarterly</digi:trn>
                                </li>
                              </logic:equal>
                              <logic:equal name="ampReports" property="options" value="M">
                                <li>
                                	<digi:trn key="aim:monthlyreport">Monthly</digi:trn>	
                                </li>
                              </logic:equal>
														</td>
														<td class="inside">
															<logic:iterate name="ampReports" property="hierarchies" id="hierarchy" >
                                <li>
                                	<digi:trn key="aim:report:${hierarchy.column.columnName}">
                                		${hierarchy.column.columnName}
                                	</digi:trn>
                                </li>
                              </logic:iterate>
														</td>
														<td class="inside">
															<div style='position:relative;display:none;' id='report-<bean:write name="ampReports" property="ampReportId"/>'> 
                                <logic:iterate name="ampReports" property="columns" id="column" indexId="index"  >
                                  <%if (index.intValue()%2==0){ %>
                                    <li>                                      
                                    	<digi:trn key="aim:report:${column.column.columnName}">
                                      	<bean:write name="column" property="column.columnName" />
                                    	</digi:trn>
                                  <% } else {%>
                                    ,
                                    	<digi:trn key="aim:report:${column.column.columnName}">
                                      	<bean:write name="column" property="column.columnName" />
                                    	</digi:trn>
                                    </li>
                                  <%} %>
                                </logic:iterate>
                              </div>
                              <span align="center" style="text-transform: capitalize;" onMouseOver="stm(['<digi:trn key="aim:teamreports:columns">columns</digi:trn>',document.getElementById('report-<bean:write name="ampReports" property="ampReportId"/>').innerHTML],Style[1])" onMouseOut="htm()">[ <u style="text-transform:capitalize;" ><digi:trn key="aim:teamreports:columns">Columns</digi:trn></u> ]&nbsp;
                              </span>

                              <div style='position:relative;display:none;' id='measure-<bean:write name="ampReports" property="measures"/>'> 
                                <logic:iterate name="ampReports" property="measures" id="measure" indexId="index"  >
                                  <li>
                                  	<digi:trn key="aim:reportBuilder:${measure.measure.aliasName}">                                      
                                    		${measure.measure.aliasName}
                                    	</digi:trn>
                                  </li>
                                </logic:iterate>
                              </div>										                                
                              <span align="center" style="text-transform: capitalize;white-space: no-wrap;"  onMouseOver="stm(['<digi:trn key="aim:teamreports:measures">measures</digi:trn>',document.getElementById('measure-<bean:write name="ampReports" property="measures"/>').innerHTML],Style[1])" onMouseOut="htm()">[ <u><digi:trn key="aim:teamreports:measures">Measures</digi:trn></u> ]<br />
                              </span>
														</td>
														<td class="inside">
															
															
															<jsp:useBean id="urlParams" type="java.util.Map" class="java.util.HashMap"/>
															<c:set target="${urlParams}" property="id">
																<bean:write name="ampReports" property="ampReportId" />
															</c:set>
															<logic:equal name="reports" property="teamView" value="false">
																<c:set target="${urlParams}" property="status" value="team" />
																	<c:set var="translation">
																		<digi:trn key="aim:clickToMakeThisPublic">Click here to make this public</digi:trn>
																	</c:set>
																	
																	<c:if test="${aimTeamReportsForm.showReportList == true}">
																		<c:set target="${urlParams}" property="returnPage">teamReportList</c:set>
																	</c:if>	
																	<c:if test="${aimTeamReportsForm.showReportList == false}">
																		<c:set target="${urlParams}" property="returnPage">teamDesktopTabList</c:set>
																	</c:if>	
																	
																	<digi:link href="/changeTeamReportStatus.do" name="urlParams" title="${translation}" >
																		<img hspace="2" title="<digi:trn key="aim:teamReportListMakePublic">Make this public</digi:trn>" src= "/repository/contentrepository/view/images/make_private.gif" border="0">
																	</digi:link>
															</logic:equal>
															
															<logic:equal name="reports" property="teamView" value="true">
																<c:set target="${urlParams}" property="status" value="member" />
																	<c:set var="translation">
																		<digi:trn key="aim:clickToMakeThisPrivate">Click here to make this private</digi:trn>
																	</c:set>

																	<c:if test="${aimTeamReportsForm.showReportList == true}">
																		<c:set target="${urlParams}" property="returnPage">teamReportList</c:set>
																	</c:if>	
																	<c:if test="${aimTeamReportsForm.showReportList == false}">
																		<c:set target="${urlParams}" property="returnPage">teamDesktopTabList</c:set>
																	</c:if>	

																	<digi:link href="/changeTeamReportStatus.do" name="urlParams" title="${translation}" >
																		<img hspace="2" title="<digi:trn key="aim:teamReportListMakePrivate">Make this private</digi:trn>" src= "/repository/contentrepository/view/images/make_public.gif" border="0">
																	</digi:link>
															</logic:equal>
															
														</td>
														
														
													</tr>
											</logic:iterate>
										</logic:notEmpty>
										<tr><td colspan="7"><digi:errors /></td></tr>
									</table>
									
									<br>
									<div class="buttons" align="center">
										<html:submit  styleClass="buttonx_sm btn" property="removeReports"  onclick="return confirmDelete() ">
											<c:if test="${aimTeamReportsForm.showReportList == true}">
												<digi:trn key="btn:removeSelectedReports">Remove selected reports</digi:trn>
											</c:if>
											<c:if test="${aimTeamReportsForm.showReportList == false}">
												<digi:trn key="btn:removeSelectedTabs">Remove selected tabs</digi:trn>
											</c:if>																			
										</html:submit>
									</div>
							
										
										</div>
										</div>											
												
											</td>
										</tr>
									</table>	
									
</td></tr>
</table>

</digi:form>

<script language="javascript">
		$("#checkAll").bind("change", function (obj){
		$("input[name=selReports]").attr("checked", $("#checkAll").attr("checked"));
	}
	);
</script>
