<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/taglib/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglib/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/taglib/struts-html" prefix="html" %>
<%@ taglib uri="/taglib/digijava" prefix="digi" %>
<%@ taglib uri="/taglib/jstl-core" prefix="c" %>
<%@ taglib uri="/taglib/jstl-functions" prefix="fn" %>
<%@ taglib uri="/taglib/aim" prefix="aim" %>
<script language="JavaScript" type="text/javascript" src="<digi:file src="module/aim/scripts/common.js"/>"></script>

<script language="JavaScript" type="text/javascript">
	<jsp:include page="scripts/calendar.js.jsp" flush="true" />
</script>
<jsp:include page="scripts/newCalendar.jsp" flush="true" />
<jsp:include page="addCurrencyRate.jsp" flush="true" />
<style type="text/css">
<!--
div.fileinputs {
	position: relative;
	height: 30px;
	width: 300px;
}
input.file {
	width: 300px;
	margin: 0;
}
input.file.hidden {
	position: relative;
	text-align: right;
	-moz-opacity:0 ;
	filter:alpha(opacity: 0);
	width: 300px;
	opacity: 0;
	z-index: 2;
}

div.fakefile {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 300px;
	padding: 0;
	margin: 0;
	z-index: 1;
	line-height: 90%;
}
div.fakefile input {
	margin-bottom: 5px;
	margin-left: 0;
	width: 217px;
}
div.fakefile2 {
	position: absolute;
	top: 0px;
	left: 217px;
	width: 300px;
	padding: 0;
	margin: 0;
	z-index: 1;
	line-height: 90%;
}
div.fakefile2 input{
	width: 83px;
}
-->
</style>

<script language="JavaScript">

function addExchangeRate() {
	myAddExchangeRate();
}

function loadExchangeRate() {
	openNewWindow(500, 300);
	<digi:context name="loadExchangeRate" property="context/module/moduleinstance/loadCurrencyRates.do" />
  	document.aimCurrencyRateForm.action = "<%= loadExchangeRate %>";
	document.aimCurrencyRateForm.target = popupPointer.name;
	document.aimCurrencyRateForm.submit();
}

function updateRates()
{
	if(confirm('<digi:trn key="aim:updateCurrencyFromUploadedFile">Do you want to update the Currency rates from the uploaded file ?</digi:trn>'))
	{
		<digi:context name="updateRates" property="context/module/moduleinstance/saveCurrencyRate.do~doAction=file"/>
		document.aimCurrencyRateForm.action = "<%= updateRates %>";
		document.aimCurrencyRateForm.target = "_self";
		document.aimCurrencyRateForm.submit();
	}
	else
		return false;
}

function editExchangeRate(date,code) {
	myEditExchangeRate(date, code);
}

function checkall() {
	var selectbox = document.aimCurrencyRateForm.checkAll;
	var items = document.aimCurrencyRateForm.selectedRates;
	if (document.aimCurrencyRateForm.selectedRates.checked == true ||
						 document.aimCurrencyRateForm.selectedRates.checked == false) {
			  document.aimCurrencyRateForm.selectedRates.checked = selectbox.checked;
	} else {
		for(i=0; i<items.length; i++){
			document.aimCurrencyRateForm.selectedRates[i].checked = selectbox.checked;
		}
	}
}

function deleteRates() {
	document.aimCurrencyRateForm.doAction.value = "delete";
	<digi:context name="showCurrRates" property="context/module/moduleinstance/showCurrencyRates.do" />
	document.aimCurrencyRateForm.action = "<%=showCurrRates %>";
	document.aimCurrencyRateForm.target = "_self";
	document.aimCurrencyRateForm.submit();
}

function selectFile() {
	document.aimCurrencyRateForm.ratesFile.value = document.aimCurrencyRateForm.file.value;
	document.aimCurrencyRateForm.doAction.value = "loadRates";
	document.aimCurrencyRateForm.target = "_self";
	document.aimCurrencyRateForm.submit();
}

function fnSubmit() {
	<digi:context name="showCurrRates" property="context/module/moduleinstance/showCurrencyRates.do" />
	document.aimCurrencyRateForm.action = "<%=showCurrRates %>";
	document.aimCurrencyRateForm.target = "_self";
	document.aimCurrencyRateForm.submit();
}

</script>

<script type="text/javascript">
	var W3CDOM = (document.createElement && document.getElementsByTagName);

	function initFileUploads() {
		if (!W3CDOM) return;
		var fakeFileUpload = document.createElement('div');
		fakeFileUpload.className = 'fakefile';
		fakeFileUpload.appendChild(document.createElement('input'));

		var fakeFileUpload2 = document.createElement('div');
		fakeFileUpload2.className = 'fakefile2';


		var button = document.createElement('input');
		button.type = 'button';

		button.value = '<digi:trn key="aim:browse">Browse...</digi:trn>';
		fakeFileUpload2.appendChild(button);

		fakeFileUpload.appendChild(fakeFileUpload2);
		var x = document.getElementsByTagName('input');
		for (var i=0;i<x.length;i++) {
			if (x[i].type != 'file') continue;
			if (x[i].parentNode.className != 'fileinputs') continue;
			x[i].className = 'file hidden';
			var clone = fakeFileUpload.cloneNode(true);
			x[i].parentNode.appendChild(clone);
			x[i].relatedElement = clone.getElementsByTagName('input')[0];

 			x[i].onchange = x[i].onmouseout = function () {
				this.relatedElement.value = this.value;
			}
		}
	}

</script>

<digi:errors/>
<digi:instance property="aimCurrencyRateForm" />

<digi:form action="/showCurrencyRates.do" enctype="multipart/form-data">

<input type="hidden" name="selectedDate">
<html:hidden property="updateCRateId"/>
<html:hidden property="doAction"/>
<html:hidden property="ratesFile"/>
<input type="hidden" name="currUrl" value="">

<table width="100%" cellspacing="0" cellpadding="0" valign="top" align="left">
<tr><td>
<!-- AMP Admin Logo -->
<jsp:include page="teamPagesHeader.jsp" flush="true" />
<!-- End of Logo -->
</td></tr>
<tr><td>
<table bgColor=#ffffff cellpadding="0" cellspacing="0" width=1000 align=center>
	<tr>
		<td align=left class=r-dotted-lg valign="top" width=750>
			<table cellPadding=5 cellSpacing=3 width="100%" style="font-size:12px;">
				<tr>
					<!-- Start Navigation -->
					<td colspan="2"><span class=crumb>
						<digi:link href="/admin.do" styleClass="comment" title="Click here to goto Admin Home">
						<digi:trn key="aim:AmpAdminHome">
						Admin Home
						</digi:trn>
						</digi:link>&nbsp;&gt;&nbsp;
						<digi:trn key="aim:currencyRateManager">Currency Rate Manager</digi:trn>
						</span>
					</td>
					<!-- End navigation -->
				</tr>
				<tr>
					<td colspan="2" height=25 valign="center" bgcolor=#c7d4db align=center><span class=subtitle-blue>
						<digi:trn>
							<b>Exchange Rates</b>
						</digi:trn>
						</span>
					</td>
				</tr>
				<tr>
					<td noWrap width="750" vAlign="top">
						<table width="100%" cellspacing="2" cellPadding="2" vAlign="top" align="left">
							<tr><td>
								<!-- Filters -->

								<table cellpadding="1" cellspacing="1" align="left" width="100%">
								<tr><td>
								<table cellpadding="0" cellSpacing=2 align="left" border="0" style="font-size:12px;">
									<tr>
										<td vAlign="center">
											<b><digi:trn key="aim:filterBy">Filter By</digi:trn>:</b>
										</td>
										<td vAlign="center">
											<digi:trn key="aim:currencyCode">Currency Code</digi:trn>
										</td>
										<td vAlign="center">
											<html:select property="filterByCurrCode" styleClass="inp-text">
												<html:option value="">--<digi:trn key="aim:all">All</digi:trn>--</html:option>
												<html:optionsCollection name="aimCurrencyRateForm" property="currencyCodes"
												value="currencyCode" label="currencyCode" />&nbsp;&nbsp;&nbsp;
											</html:select>
										</td>
										<td vAlign="center">
											<digi:trn key="aim:ratesOfDate">Rates of date</digi:trn>
										</td>
										<%--
										<td bgcolor="#f4f4f2" vAlign="center">
											<digi:trn key="aim:dateFrom">From</digi:trn>
										</td>
										--%>
										<td bgcolor="#f4f4f2" vAlign="center">
											<table cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<html:text property="filterByDateFrom" size="10" readonly="true"
														styleId="filterByDateFrom" styleClass="inp-text"/>
													</td>
													<td align="left" vAlign="center">&nbsp;
														<a id="date1" href='javascript:pickDate("date1",document.aimCurrencyRateForm.filterByDateFrom)'>
															<img src="../ampTemplate/images/show-calendar.gif" alt="Click to View Calendar" border="0">
														</a>
													</td>
												</tr>
											</table>
										</td>
										<td vAlign="center">
                                        &nbsp;&nbsp;&nbsp;&nbsp;
											<%--<digi:trn key="aim:andPrev7Days">and previous</digi:trn>--%>
											<html:select property="timePeriod" styleClass="inp-text">
												<logic:iterate id="period" name="aimCurrencyRateForm" property="timePeriods" >
													<c:set var="translation">
														<digi:trn key="aim:timeperiods${period.label}">${period.label}</digi:trn>
													</c:set>						
													<html:option value="${period.value}">${translation}</html:option>
												</logic:iterate>
											</html:select>
										</td>
										
										<td vAlign="center">
                                          <c:set var="trnGoBtn">
                                            <digi:trn key="aim:goBtn"> Go </digi:trn>
                                          </c:set>
                                          <html:submit onclick="javascrip:fnSubmit()" value="${trnGoBtn}"  styleClass="buttonx"/>
										</td>
									</tr>
								</table>
								</td></tr>
								<tr><td>
								<table cellpadding="0" cellSpacing=2 align="left" border="0" vAlign="center" style="font-size:12px;">
									<tr>
										<td vAlign="left" width="170" align="center">
											<digi:trn key="aim:numRecordsPerPage">Number of records per page</digi:trn>:
										</td>
										<td vAlign="left" width="50" align="center">
											<html:text property="numResultsPerPage" size="3" styleClass="inp-text"/>
										</td>
										<td  vAlign="left" align="center">
                                          <c:set var="trnViewBtn">
                                            <digi:trn key="aim:viewBtn"> View </digi:trn>
                                          </c:set>
                                          <html:submit value="${trnViewBtn}" styleClass="buttonx"/>
										</td>
									</tr>
								</table>
								</td>
                                </tr>

                                <tr>
                                  <td>
                                    <table cellpadding="0" cellSpacing=2 align="left" border="0" vAlign="center" style="font-size:12px;">
                                      <tr>
                                        <td vAlign="left" align="center">
                                          <FONT color=red>*</FONT>
                                          <a title="<digi:trn key="aim:LocationoftheFile">URI Location of the document to be attached</digi:trn>"><digi:trn key="aim:file">File</digi:trn>
										  </a>
                                        </td>
                                        <td vAlign="middle" align="center">
										  &nbsp;&nbsp;&nbsp;<img src= "../ampTemplate/images/help.gif" border="0" title="<digi:trn key="aim:currencyFormatHint"> The file should have 3 columns: the first column contains currency codes (ex. CAD, or ETB), the second column contains rates (per 1 US dollar), and the 3rd column contains the dates (in format  dd-mm-yyyy).</digi:trn>"/>
										</td>                                   
                                        
                                        <td vAlign="left" align="left">
                                        <!-- <html:file name="aimCurrencyRateForm" property="currRateFile" size="50" styleClass="dr-menu"/> -->
                                        <c:set var="trnUpdateValues">
                                            <digi:trn key="aim:UpdateValues">Update Values</digi:trn>
                                        </c:set>
 
                                        <a title="<digi:trn key="aim:FileLocation">Location of the document to be attached</digi:trn>">
										 	<div class="fileinputs">  <!-- We must use this trick so we can translate the Browse button. AMP-1786 -->
												<input id="currRateFile" name="currRateFile" type="file" class="file"/>
												<input type="button" value="${trnUpdateValues}" class="button" onclick="return updateRates()">
											</div>
                                        </a>
                                       
                                        </td>
                                    </table>
                                  </td>
                                  
                                </tr>
                                </table>


							</td></tr>
							<tr><td bgcolor="#ffffff" valign="top" align="left">
								<!-- Exchange rates table -->
								<div style="clear:both;">&nbsp;</div>
								<table cellSpacing="1" cellPadding="2" vAlign="top" align="left" class="inside" width="100%" style="font-size:12px;">
									<tr>
										<td align="center" width="3" class="inside" bgcolor=#F2F2F2>
											<input type="checkbox" name="checkAll" onclick="checkall()">
										</td>
										<td align="center" width="40" onMouseOver="this.className='colHeaderOver'"
										onMouseOut="this.className='colHeaderLink'" class="inside" bgcolor=#F2F2F2>
											<b><digi:trn key="aim:currCode">Code</digi:trn></b>
										</td>
										<td align="center" width="200" onMouseOver="this.className='colHeaderOver'"
										onMouseOut="this.className='colHeaderLink'" class="inside" bgcolor=#F2F2F2>
											<b><digi:trn key="aim:currencyName">Currency Name</digi:trn></b>
										</td>
										<td align="center" width="40" onMouseOver="this.className='colHeaderOver'"
										onMouseOut="this.className='colHeaderLink'" class="inside" bgcolor=#F2F2F2>
											<b><digi:trn> Source Currency Code</digi:trn></b>
										</td>
										<td align="center" width="200" onMouseOver="this.className='colHeaderOver'"
										onMouseOut="this.className='colHeaderLink'" class="inside" bgcolor=#F2F2F2>
											<b><digi:trn>Source Currency Name</digi:trn></b>
										</td>
										<td align="center" width="80" onMouseOver="this.className='colHeaderOver'"
										onMouseOut="this.className='colHeaderLink'" class="inside" bgcolor=#F2F2F2>
											<b><digi:trn key="aim:exchangeRateDate">Date</digi:trn></b>
										</td>
										<td align="center" onMouseOver="this.className='colHeaderOver'"
										onMouseOut="this.className='colHeaderLink'" class="inside" bgcolor=#F2F2F2>
											<b><digi:trn key="aim:exchangeRate">Rate</digi:trn></b>
										</td>
									</tr>
									<c:if test="${empty aimCurrencyRateForm.currencyRates}">
									<tr>
										<td colspan="7" align="center" class="inside">
											<digi:trn key="aim:noCurrencyRates">No currency rates</digi:trn>
										</td>
									</tr>
									</c:if>
									<% int index = 0; %>
									<c:if test="${!empty aimCurrencyRateForm.currencyRates}">
									<c:forEach var="cRates" items="${aimCurrencyRateForm.currencyRates}">
									<%
									if (index%2 == 0) { %>
									<tr class="rowNormal">
									<% } else { %>
									<tr class="rowAlternate">
									<% }
									index++;%>
										<td align="center" width="3" class="inside">
											<html:multibox property="selectedRates">
												<c:out value="${cRates.id}"/>
											</html:multibox>
										</td>
										<td align="left" class="inside">
                                          <digi:trn>${cRates.currencyCode}</digi:trn>
										</td>
										<td align="left" class="inside">
											<c:out value="${cRates.currencyName}"/>
										</td>
										<td align="left" class="inside">
                                         	${cRates.fromCurrencyCode}
										</td>
										<td align="left" class="inside">
											<c:out value="${cRates.fromCurrencyName}"/>
										</td>
										<td align="center" class="inside">
											<a href="javascript:editExchangeRate('<c:out value="${cRates.exchangeRateDate}"/>','<c:out value="${cRates.currencyCode}"/>')">
											<c:out value="${cRates.exchangeRateDate}"/>
											</a>
										</td>
										<td align="right" nowrap="nowrap" class="inside">
											<aim:formatNumber  maxFractionDigits="10" value="${cRates.exchangeRate}"> </aim:formatNumber>
										</td>
									</tr>
									</c:forEach>
									</c:if>
								</table>
							</td></tr>
							<tr>
                              <td>
                                <c:set var="trnDelBtn">
                                  <digi:trn key="aim:deleteSelectedRates">Delete Selected Rates</digi:trn>
                                </c:set>
                                <input type="button" value="${trnDelBtn}" class="buttonx" onclick="deleteRates()">
								<hr />
                              </td>
                            </tr>
							<c:if test="${!empty aimCurrencyRateForm.pages}">
							<tr><td>
								<!-- Pagination -->
								<table width="460" cellSpacing="1" cellPadding="2" vAlign="top" align="left">
									<tr>
										<td>
											<bean:size name="aimCurrencyRateForm" property="pages" id="totpages"/>
											<digi:trn key="aim:page">Page</digi:trn> <u><c:out value="${aimCurrencyRateForm.currentPage}"/></u> <digi:trn key="aim:of">of</digi:trn>
											<u><c:out value="${totpages}"/></u>
										</td>
									</tr>
									<tr>
										<td>
											<digi:trn key="aim:pages">Pages</digi:trn> 
											<jsp:useBean id="urlParams" type="java.util.Map" class="java.util.HashMap"/>
											<c:forEach var="currPage" items="${aimCurrencyRateForm.pages}">
												<c:if test="${currPage == aimCurrencyRateForm.currentPage}">
													<c:out value="${currPage}"/>
												</c:if>
												<c:if test="${currPage != aimCurrencyRateForm.currentPage}">
													<c:set target="${urlParams}" property="page">
														<c:out value="${currPage}"/>
													</c:set>
													<digi:link href="/showCurrencyRates.do" name="urlParams">
													<c:out value="${currPage}"/></digi:link>
												</c:if>
												|
											</c:forEach>
										</td>
									</tr>
								</table>
							</td></tr>
							</c:if>

							<!--here-->

							<c:if test="${empty aimCurrencyRateForm.pages}">
							<tr><td>
								<!-- Pagination -->
								<table width="460" cellSpacing="1" cellPadding="2" vAlign="top" align="left">
									<tr>
										<td>
											 1 <digi:trn key="aim:of">of</digi:trn> 1
										</td>
									</tr>
								</table>
							</td></tr>
							</c:if>

							<!--end end-->


							<tr><td>
							<hr />
								<table width="450" cellSpacing="3" cellPadding="1" vAlign="top" align="left">
									<tr>
										<td align="left">
											<a href="javascript:addExchangeRate()">
                                            <digi:trn key="aim:AddNewExchangeRate">Add new exchange rate</digi:trn>
											</a>
										</td>
										<td align="right">
										<c:if test="${aimCurrencyRateForm.lastRateUpdate != null}">
											<digi:trn key="aim:LastUpdate">Last Update: </digi:trn>
											<c:out value="${aimCurrencyRateForm.lastRateUpdate}"/>
										</c:if>
										</td>
									</tr>
								</table>
							</td></tr>
							<%--
							<tr><td bgcolor="#ffffff">
								<table width="450" cellSpacing="1" cellPadding="3" vAlign="top" align="left" bgcolor="#aaaaaa">
									<tr bgcolor="#eeeeee">
										<td align="center" colspan="2">
											<b>
											Load currency rates from a CSV file</b>
										</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td align="center">
											<table width="350" cellSpacing="3" cellPadding="1" vAlign="top" align="center">
												<tr>
													<td align="left" width="320">
														<html:file name="aimCurrencyRateForm" property="ratesFile"
														size="40" styleClass="dr-menu"/>
														<input type="file" name="file" size="50" class="dr-menu">
													</td>
													<td align="left">
														<input type="button" value="Load" class="dr-menu" onclick="selectFile()">
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td></tr>
							--%>
						</table>
					</td>
					
					<td valign="top">
									<table align="center" cellpadding="0" cellspacing="0" width="90%"
												border="0">
												<tr>
													<td><!-- Other Links -->
													<table cellpadding="0" cellspacing="0" width="100" style="font-size:12px;">
														<tr>
															<td bgColor=#c9c9c7 class=box-title><digi:trn
																key="aim:otherLinks">
															<b style="padding-left:5px;">Other links</b>
															</digi:trn></td>
															<td background="module/aim/images/corner-r.gif" height="17" width=17></td>
														</tr>
													</table>
													</td>
												</tr>
												<tr>
													<td bgColor="#ffffff" class="box-border">
													<table cellPadding=5 cellspacing="1" width="100%" style="font-size:12px;" class="inside">
														<tr>
															<td class="inside"><digi:img src="module/aim/images/arrow-014E86.gif"
																width="15" height="10" /> <c:set var="translation">
																<digi:trn >Click here to go back to admin home page</digi:trn>
															</c:set> <digi:link href="/admin.do"
																title="${translation}">
																<digi:trn>Admin Home</digi:trn>
															</digi:link></td>
														</tr>
														<tr>
															<td class="inside">
																	<digi:img src="module/aim/images/arrow-014E86.gif"
																		width="15" height="10" /> 
																	<c:set var="translation">
																		<digi:trn>Click here to go to the Currency Manager</digi:trn>
																	</c:set> 
																	<digi:link href="/currencyManager.do" title="${translation}">
																			<digi:trn>Currency Manager</digi:trn>
																	</digi:link>
															</td>
														</tr>
														<tr>
															<td class="inside">
																	<digi:img src="module/aim/images/arrow-014E86.gif"
																		width="15" height="10" /> 
																	<c:set var="translation">
																		<digi:trn>Click here to go to Select Filteres Currency Rates</digi:trn>
																	</c:set> 
																	<digi:link href="/selectFilteredRates.do" title="${translation}">
																			<digi:trn>Select Filtered Rates</digi:trn>
																	</digi:link>
															</td>
														</tr>
		
														<!-- end of other links -->
													</table>
													</td>
												</tr>
											</table>
					</td>
					
				</tr>
			</table>
		</td>
	</tr>
</table>
</td></tr>
</table>
</digi:form>

<script type="text/javascript">
	initFileUploads();
</script>
