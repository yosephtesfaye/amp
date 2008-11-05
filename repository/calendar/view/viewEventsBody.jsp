<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/taglib/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglib/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/taglib/struts-html" prefix="html" %>
<%@ taglib uri="/taglib/digijava" prefix="digi" %>
<%@ taglib uri="/taglib/jstl-core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="org.digijava.module.calendar.form.CalendarViewForm" %>
<%@ taglib uri="/taglib/jstl-functions" prefix="fn" %>

<script language="JavaScript1.2" type="text/javascript" src="<digi:file src="module/aim/scripts/dscript120.js"/>"></script>
<script language="JavaScript1.2" type="text/javascript"  src="<digi:file src="module/aim/scripts/dscript120_ar_style.js"/>"></script>
<!-- this is for the nice tooltip widgets -->
<DIV id="TipLayer"  style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<script langauage="JavaScript">
	var evnt="<digi:trn key='calendar:event'>Event Name</digi:trn>"
</script>
<style>
<!--
.headersColor { 
	background:#3754A1 ;
	color:#FFFFFF;	
	text-decoration:none;
	height: 100%;
}
-->
</style>

<digi:instance property="calendarViewForm"/>

<table bgcolor="#f4f4f2"  width="100%" cellspacing="" cellpadding="1" height="100%">
	<tr>
    	<td bgcolor="#ffffff">
    		<jsp:include page="viewEventsButtons.jsp" flush="false" />
    	</td> 
    <tr>
  	<tr>
    	<td style="border-top: 1px solid #7B9EBD;border-left: 1px solid #7B9EBD; border-right: 1px solid #7B9EBD">
	      <table border="0" width="100%" height="40px" bgcolor="#f4f4f2" >
	        <tr >
	          <c:if test="${calendarViewForm.view != 'custom'}">	
	            <td align="right" width="40%" vAlign="middle">
	              <digi:img src="module/calendar/images/calenderLeftArrow1.jpg"/>
	              <a href="#"  onclick="submitFilterForm('${calendarViewForm.view}', '${calendarViewForm.dateNavigator.leftTimestamp}');return(false);"><digi:trn key="aim:last">Last</digi:trn></a>
	            </td>
	          </c:if>
	          <td align="center" >
	            <span id="calendarFont" style="font-size:14px;font-weight:bold;">
	              <c:if test="${calendarViewForm.view == 'yearly'}">
	              ${calendarViewForm.baseDateBreakDown.year}
	              </c:if>
	              <c:if test="${calendarViewForm.view == 'monthly'}">
	
	                <digi:trn key="aim:calendar:basemonthNameLong:${calendarViewForm.baseDateBreakDown.monthNameLong}">${calendarViewForm.baseDateBreakDown.monthNameLong}</digi:trn>,&nbsp;
	                ${calendarViewForm.baseDateBreakDown.year}
	
	              </c:if>
	              <c:if test="${calendarViewForm.view == 'weekly'}">
	
	                <digi:trn key="aim:calendar:startmonthNameShort:${calendarViewForm.startDateBreakDown.monthNameShort}">${calendarViewForm.startDateBreakDown.monthNameShort}</digi:trn>
	                ${calendarViewForm.startDateBreakDown.dayOfMonth},&nbsp;
	                ${calendarViewForm.startDateBreakDown.year}&nbsp;-&nbsp;
	                <digi:trn key="aim:calendar:endmonthNameShort:${calendarViewForm.endDateBreakDown.monthNameShort}">${calendarViewForm.endDateBreakDown.monthNameShort}</digi:trn>
	                ${calendarViewForm.endDateBreakDown.dayOfMonth},&nbsp;
	                ${calendarViewForm.endDateBreakDown.year}
	              </c:if>
	              <c:if test="${calendarViewForm.view == 'daily'}">
	                <digi:trn key="aim:calendar:dailymonthNameLong:${calendarViewForm.baseDateBreakDown.monthNameLong}">${calendarViewForm.baseDateBreakDown.monthNameLong}</digi:trn>
		                ${calendarViewForm.baseDateBreakDown.dayOfMonth},&nbsp;
	                ${calendarViewForm.baseDateBreakDown.year}
	              </c:if>
	              <c:if test="${calendarViewForm.view == 'custom'}">
	                <digi:trn key="aim:calendar:startmonthNameLong:${calendarViewForm.startDateBreakDown.monthNameLong}">${calendarViewForm.startDateBreakDown.monthNameLong}</digi:trn>
	                ${calendarViewForm.startDateBreakDown.dayOfMonth},&nbsp;
	                ${calendarViewForm.startDateBreakDown.year}&nbsp;-&nbsp;
	                <digi:trn key="aim:calendar:endmonthNameLong:${calendarViewForm.endDateBreakDown.monthNameLong}">${calendarViewForm.endDateBreakDown.monthNameLong}</digi:trn>
	                ${calendarViewForm.endDateBreakDown.dayOfMonth},&nbsp;
	                ${calendarViewForm.endDateBreakDown.year}
	              </c:if>
		            </span>
	          </td>
	          <c:if test="${calendarViewForm.view != 'custom'}">
	            <td align="left" width="40%">
	              <a href="#" style="text-decoration:none" onclick="submitFilterForm('${calendarViewForm.view}', '${calendarViewForm.dateNavigator.rightTimestamp}');return(false);"><digi:trn key="aim:next">Next</digi:trn></a>	<digi:img src="module/calendar/images/calenderRightArrow1.jpg"/>
	            </td>
	          </c:if>
	        </tr>
	      </table>
	    </td>
	  </tr>	 
	  <tr>
	  	<td width="95%" align="center" style="border-bottom: 1px solid #7B9EBD;border-left: 1px solid #7B9EBD; border-right: 1px solid #7B9EBD">
	  		<table width="100%">
	  			<tr>
				    <td align="center" vAlign="middle">
				    	<table width="100%" cellpadding="1" cellspacing="1" align="center">
				        	<c:if test="${calendarViewForm.view != 'custom'}">
				        		<!-- Monthly view start -->
				            	<c:if test="${calendarViewForm.view == 'monthly'}">
				            		<tr align="center" vAlign="middle">
				                		<td>
				                  			<table width="99%" border="0" align="center" >
							                    <tr>
							                    	<td align="left" valign="top" bgcolor="#3754A1" style="font-size:12px;color:White;font-family:Tahoma;"><digi:trn key="aim:mon">Mon</digi:trn></td>
							                    	<td width="1px" bgcolor="#7B9EBD"/>
							                      	<td align="left" valign="top" bgcolor="#3754A1" style="font-size:12px;color:White;font-family:Tahoma;"><digi:trn key="aim:tue">Tue</digi:trn></td>
							                      	<td width="1px" bgcolor="#7B9EBD"/>
							                      	<td align="left" valign="top" bgcolor="#3754A1" style="font-size:12px;color:White;font-family:Tahoma;"><digi:trn key="aim:wed">Wed</digi:trn></td>
							                      	<td width="1px" bgcolor="#7B9EBD"/>
							                      	<td align="left" valign="top" bgcolor="#3754A1" style="font-size:12px;color:White;font-family:Tahoma;"><digi:trn key="aim:thu">Thu</digi:trn></td>
							                      	<td width="1px" bgcolor="#7B9EBD"/>
							                      	<td align="left" valign="top" bgcolor="#3754A1" style="font-size:12px;color:White;font-family:Tahoma;"><digi:trn key="aim:fr">Fri</digi:trn></td>
							                      	<td width="1px" bgcolor="#7B9EBD"/>
							                      	<td align="left" valign="top" bgcolor="#3754A1" style="font-size:12px;color:White;font-family:Tahoma;"><digi:trn key="aim:sat">Sat</digi:trn></td>
							                      	<td width="1px" bgcolor="#7B9EBD"/>
							                      	<td align="left" valign="top" bgcolor="#3754A1" style="font-size:12px;color:White;font-family:Tahoma;"><digi:trn key="aim:sun">Sun</digi:trn></td>
							                      	<td width="1px" bgcolor="#7B9EBD"/>
							                    </tr>
							                    <tr height="4px" bgcolor="#e8eef7">
				                        				<td colspan="14" />
				                      			</tr>
				                   				<c:forEach var="row" items="${calendarViewForm.dateNavigator.items}">
				                   					<!-- In this row,if in monthly view other months' dates are shown,they should be of 'inactive' color-->
							                    	<tr vAlign="middle" bgcolor="#ffffff">
							                        	<c:forEach var="item" items="${row}" >
							                          		<td vAlign="top" style="padding-right: 0px;padding-left:0px; border-right: 0px;border-left: 0px;">
									                          	<c:if test="${!item.enabled}">
									                          		<span style="color:#cbcbcb">
									                          			${item.dayOfMonth}
									                          		</span>
									                          	</c:if>
									                          	<c:if test="${item.enabled}">
									                          		<span>${item.dayOfMonth}</span>
									                          	</c:if>
							                          		</td>
							                          		<td width="1px" bgcolor="#e8eef7"></td>
								                       </c:forEach>
								                    </tr>
				                      				<c:forEach var="ampCalendarGraph" items="${calendarViewForm.ampCalendarGraphs}">
				                        				<c:set var="startDay">${ampCalendarGraph.ampCalendar.calendarPK.startDay}</c:set>
				                        				<c:set var="endDay">${ampCalendarGraph.ampCalendar.calendarPK.endDay}</c:set>
				                        				<c:set var="endMonth">${ampCalendarGraph.ampCalendar.calendarPK.endMonth+1}</c:set>
				                        				<c:set var="startMonth">${ampCalendarGraph.ampCalendar.calendarPK.startMonth+1}</c:set>
				                        				<c:set var="currentMonth">${calendarViewForm.baseDateBreakDown.month}</c:set>
				                        				<c:set var="iterationBeginIndex">
												         <c:if test="${(fn:length(ampCalendarGraph.graphItems)-1) <0}"> 0</c:if>
												         <c:if test="${(fn:length(ampCalendarGraph.graphItems)-1) >=0}"> ${fn:length(ampCalendarGraph.graphItems)-1}</c:if>
												        </c:set>
												        <c:set var="eventName">
												        	<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}" begin="${fn:length(ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem)-1}">
												        		${ampCalendarEventItem.title}
												        	</c:forEach>
												        </c:set>
												        <tr height="2px" bgcolor="#ffffff">
					                        				<td colspan="14" />
					                      				</tr>
				                        				<tr vAlign="middle" bgcolor="#ffffff">
				                          					<c:forEach var="item" items="${row}">			                          						 
				                            					<td valign="top" vAlign="top" width="14%" style="padding:0px; border-right: 0px;border-left: 0px;" onmouseover="stm([evnt,'${eventName}'],Style[14])" onmouseout="htm()">		                              						
				                              						<!-- Stars Month= Current Month -->                              						
				                              						<c:if test="${startMonth==currentMonth}">
				                              							<c:if test="${endMonth==currentMonth}">
					                                						<c:if test="${item.dayOfMonth >=startDay && item.dayOfMonth<=endDay && item.enabled}">			                                							
					                                  							<div style="margin:0px;padding:0px;height:100%;font-weight:Bold;text-align:center;color:Black;border:1px solid ${ampCalendarGraph.ampCalendar.eventType.color};background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
					                                                        		<c:if test="${item.dayOfMonth==startDay && item.enabled}">
					                                                        			<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
					                                                        				<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
								                                                        		${ampCalendarEventItem.title}
								                                                        	</c:forEach>				     
					                                                        			</digi:link>				         
					                                                        		</c:if>
					                                                        		&nbsp; 				                                                        						                                                        					                                                        				
					                                                      		</div>			                                                      		
					                                						</c:if>
					                              						</c:if>
				                              							
				                              							<c:if test="${endMonth!=currentMonth}">
					                               	 						<c:if test="${item.dayOfMonth>=startDay && item.enabled}">
					                                  							<div style="margin:0px;padding:0px;height:100%;font-weight:Bold;text-align:center;color:Black;border:1px solid ${ampCalendarGraph.ampCalendar.eventType.color};background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
					                                                        		<c:if test="${item.dayOfMonth==startDay && item.enabled}">
					                                                        			<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
					                                                        				<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
								                                                        		${ampCalendarEventItem.title}
								                                                        	</c:forEach>				     
					                                                        			</digi:link>				         
					                                                        		</c:if>
					                                                        		&nbsp;                                               					                                                        				
					                                                      		</div>				                                                      		
					                                						</c:if>				                                						
					                                						<c:if test="${item.dayOfMonth<endDay && !item.enabled}">
											                                  	<div style="margin:0px;padding:0px;height:100%;font-weight:Bold;text-align:center;color:Black;border:1px solid ${ampCalendarGraph.ampCalendar.eventType.color};background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
					                                                        		<c:if test="${item.dayOfMonth==startDay && item.enabled}">
					                                                        			<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
					                                                        				<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
								                                                        		${ampCalendarEventItem.title}
								                                                        	</c:forEach>				     
					                                                        			</digi:link>				                                                        			    
					                                                        		</c:if>
					                                                        		&nbsp;   		                                                        				
					                                                      		</div>	
					                                						</c:if>
					                              						</c:if>			                              									                                						
				                              						</c:if>
				                              						
				                              						<!-- Start Month != Current Month -->			                              						
				                              						<c:if test="${startMonth!=currentMonth && currentMonth==endMonth}">			                                						
				                                						<c:if test="${item.dayOfMonth>startDay && !item.enabled}">
											                                <div style="margin:0px;padding:0px;font-weight:Bold;text-align:center;color:Black;border:1px solid ${ampCalendarGraph.ampCalendar.eventType.color};background-color:${ampCalendarGraph.ampCalendar.eventType.color};">				                                                        	
					                                                        	&nbsp;				                                                        		
					                                                        </div>
				                                						</c:if>
				                                						<c:if test="${item.dayOfMonth<=endDay && item.enabled}">
											                                <div style="margin:0px;padding:0px;font-weight:Bold;text-align:center;color:Black;border:1px solid ${ampCalendarGraph.ampCalendar.eventType.color};background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
					                                                        	<c:if test="${item.dayOfMonth==startDay && !item.enabled}">
					                                                        		<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
					                                                        			<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
						                                                        			${ampCalendarEventItem.title}-VVV2
						                                                        		</c:forEach>
					                                                        		</digi:link>				                                                        		 
					                                                        	</c:if>
					                                                        	&nbsp;	
					                                                        </div>
				                                						</c:if>		                                						
				                              						</c:if>
				                              						
				                              						<c:if test="${startMonth!=currentMonth && endMonth!=currentMonth}">
				                                						<div style="margin:0px;padding:0px;font-weight:Bold;text-align:center;color:Black;border:1px solid ${ampCalendarGraph.ampCalendar.eventType.color};background-color:${ampCalendarGraph.ampCalendar.eventType.color};">				                                                     			                                                        				
					                                                    &NBSP;
					                                                    </div>	
				                              						</c:if>		                              						
				                              						
				                              					</td>
				                              					<!-- define whether td's bgcolor should be red or not -->
				                              					<c:set var="backgrColor">
				                              						<c:choose>
				                              							<c:when test="${startMonth==currentMonth}">
				                              								<c:choose>
				                              									<c:when test="${endMonth==currentMonth}">
				                              										<c:choose>
				                              											<c:when test="${item.dayOfMonth >=startDay && item.dayOfMonth<=endDay && item.enabled}">
				                              												${ampCalendarGraph.ampCalendar.eventType.color}
				                              											</c:when>
				                              											<c:otherwise>#e8eef7</c:otherwise>
				                              										</c:choose>
				                              									</c:when>
				                              									<c:when test="${endMonth!=currentMonth}">
				                              										<c:choose>
				                              											<c:when test="${(item.dayOfMonth>=startDay && item.enabled) ||(item.dayOfMonth<endDay && !item.enabled)}">
				                              												${ampCalendarGraph.ampCalendar.eventType.color}
				                              											</c:when>
				                              											<c:otherwise>#e8eef7</c:otherwise>
				                              										</c:choose>
				                              									</c:when>
				                              								</c:choose>
				                              							</c:when>
				                              							<c:when test="${startMonth!=currentMonth && currentMonth==endMonth}">
				                              								<c:choose>
				                              									<c:when test="${(item.dayOfMonth>startDay && !item.enabled) || (item.dayOfMonth<=endDay && item.enabled)}">
				                              										${ampCalendarGraph.ampCalendar.eventType.color}
				                              									</c:when>
				                              									<c:otherwise>#e8eef7</c:otherwise>
				                              								</c:choose>
				                              							</c:when>
				                              							<c:otherwise>${ampCalendarGraph.ampCalendar.eventType.color}</c:otherwise>
				                              						</c:choose>
				                              					</c:set>
				                              					<td width="1px" bgcolor="${backgrColor}"/>
								                          </c:forEach>
								                        </tr>
				                		        	</c:forEach>
				                      				<tr height="4px" bgcolor="#e8eef7">
				                        				<td colspan="14" />
				                      				</tr>
				                    			</c:forEach>
				                  			</table>
				    					</td>
				              		</tr>
								</c:if>
				                <!-- Monthly view End -->
				                <!-- Daily View Start -->
				                <c:if test="${calendarViewForm.view == 'daily'}">
				                	<tr>
				                    	<td style="padding:30px;text-align:center;">
				                        	<table align="center" style="min-width:700px;" width="100%">
				                            	<tr>
				                                	<td>
				                                    	<div style="overflow:auto;height:500px;border:2px solid #e8eef7;">
				                                        	<table width="100%">
				                                            	<c:forEach var="hour" begin="0" end="24">
				                                              		<tr style="height:40px;">
				                                                		<td align="left" style="border-top:2px solid #e8eef7;color:White;background-color:#3754A1;vertical-align:top;width:70px;padding:6px;font-size:12px;font-family: Tahome">
				                                                  			<c:if test="${hour < 12}">
				                                                    			<c:if test="${hour < 10}">
				                                                      				<c:set var="hoursToDisplay" value="0${hour}:00"/>
				                                                    			</c:if>
				                                                    			<c:if test="${hour > 9}">
				                                                      				<c:set var="hoursToDisplay" value="${hour}:00"/>
				                                                    			</c:if>
				                                                   	 			${hoursToDisplay} <digi:trn key="aim:am">AM</digi:trn>
				                                                  			</c:if>
				                                                  			<c:if test="${hour > 11}">
				                                                   				<c:if test="${hour < 13}">
				                                                      				${hour}:00 <digi:trn key="aim:mp">PM</digi:trn>
							                                                    </c:if>
							                                                    <c:if test="${hour > 12}">
				                                                      				<c:if test="${(hour - 12) < 10}">
				                                                        				<c:set var="hoursToDisplay" value="0${hour - 12}:00"/>
				                                                      				</c:if>
				                                                      				<c:if test="${(hour - 12) > 9}">
				                                                        				<c:set var="hoursToDisplay" value="${hour - 12}:00"/>
				                                                      				</c:if>
				                                                      				${hoursToDisplay} <digi:trn key="aim:am">PM</digi:trn>
				                                                    			</c:if>
				                                                  			</c:if>
				                                                		</td>
				                                                		<td style="border-top:2px solid #e8eef7;">
				                                                  			<c:forEach var="ampCalendarGraph" items="${calendarViewForm.ampCalendarGraphs}">
				                                                    			<c:set var="startHours">${ampCalendarGraph.ampCalendar.calendarPK.startHour}</c:set>
				                                                    			<c:set var="endHours">${ampCalendarGraph.ampCalendar.calendarPK.endHour}</c:set>
				                                                    			<c:if test="${hour>=startHours && hour<=endHours}">
				                                                      				<div style="margin:2px;padding:2px;font-weight:Bold;text-align:center;color:Black;border:1px solid Black;background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
				                                                         				<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~~method=preview~resetForm=true">
								                                                        	<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
					                                                        					${ampCalendarEventItem.title}
					                                                        				</c:forEach>
								                                                        </digi:link>
				                                                      				</div>
				                                                    			</c:if>
				                                                  			</c:forEach>
						                                           			&nbsp;
				                                                		</td>
				                                              		</tr>
				                                            	</c:forEach>
				                                            	<tr height="2px">
				                                              		<td style="border-top:1px solid #e8eef7;color:White;">&nbsp; </td>
				                                              		<td style="border-top:1px solid #e8eef7;color:White;">&nbsp;</td>
				                                            	</tr>
				                                          	</table>
				                                        </div>
				                                     </td>
												</tr>
				                              </table>
				                           </td>
				                         </tr>
				                       </c:if>
				  					
				            	</c:if>
								<!-- Daily View End -->
								
								<!-- yearly view Start -->
								<c:if test="${calendarViewForm.view == 'yearly'}">
									<tr valign="top" bgcolor="#ffffff">
				                    	<td style="text-align:center;" valign="top">
				                        	<div style="vertical-align: top;width: 100%;height: 100%">				                              					                                        	
				                                	<c:forEach var="row" items="${calendarViewForm.dateNavigator.items}"  varStatus="stat">
				  										<c:forEach var="item" items="${row}">
				  											<c:set var="monthIndex">
															   	<c:if test="${item.month=='Jan'}">1</c:if>
															    <c:if test="${item.month=='Feb'}">2</c:if>
																<c:if test="${item.month=='Mar'}">3</c:if>
																<c:if test="${item.month=='Apr'}">4</c:if>
																<c:if test="${item.month=='May'}">5</c:if>
																<c:if test="${item.month=='Jun'}">6</c:if>
																<c:if test="${item.month=='Jul'}">7</c:if>
																<c:if test="${item.month=='Aug'}">8</c:if>
																<c:if test="${item.month=='Sep'}">9</c:if>
																<c:if test="${item.month=='Oct'}">10</c:if>
																<c:if test="${item.month=='Nov'}">11</c:if>
																<c:if test="${item.month=='Dec'}">12</c:if>
															</c:set> 
															<table width="100%" style="border:solid 2px #e8eef7">
					  											<tr>
					  												<td align="left" width="70" class="headersColor" >											        						
																    	<a href="#" style="text-decoration:none;color:White;font-size:12px;font-family:Tahoma;text-align: center;" onclick="submitFilterForm('${calendarViewForm.view}', '${item.timestamp}');return(false);">
																        	<digi:trn key="aim:calendar${item.month}">${item.month}</digi:trn>
																        </a>
																	</td> 
																	<td width="770px">  
																		<c:if test="${fn:length(calendarViewForm.ampCalendarGraphs)!=0}">
																			<c:if test="${calendarViewForm.view == 'yearly'}">																			
																				<c:set var="eventsAmountForThisMonth"> <bean:write name="calendarViewForm" property="eventsAmountIndexed[${monthIndex-1}]"/></c:set>					
																				<div <c:if test="${eventsAmountForThisMonth>5}"> style="overflow:scroll;width: 790px;"</c:if>>
																					<table style="padding:6px;width: 100%;">
																		       			<tr height="25px">
																		       				<c:forEach var="ampCalendarGraph" items="${calendarViewForm.ampCalendarGraphs}">
																			           			<c:set var="startMonth">${ampCalendarGraph.ampCalendar.calendarPK.startMonth+1}</c:set>																					           																					           								              			
																				           			<c:if test="${monthIndex== startMonth}">																				           				
																				           				<td align="left" width="155px"> 
																					                   		<div style="width:155;overflow:hidden;background-color:${ampCalendarGraph.ampCalendar.eventType.color};height:100%">
																					                   			<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
																					                       			<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
																					                       				${ampCalendarEventItem.title}
																					                       			</c:forEach>
																					                   			</digi:link>
																					                   		</div>
																					               		</td>
																				           			</c:if>																			           		
																								</c:forEach>
																		           			</tr>
																		           		</table>
																		          	</div>
																				</c:if>
																			</c:if>
																		</td> 
					  												</tr>
					  												<tr height="1px" bgcolor="#ffffff"></tr>
					  											</table>
				  											</c:forEach>
				  										</c:forEach>				                                   
				                                 </div>				                                     
				                         	</td>
				                    </tr>
								</c:if>
								<!-- yearly view End   -->
								
								<!-- Weekly view Start -->
								<c:if test="${calendarViewForm.view == 'weekly'}">
									<tr>
				                    	<td style="padding:30px;text-align:center;">
				                        	<table align="center" style="min-width:700px;" width="100%">
				                            	<tr>
				                                	<td width="100%">
				                                    	<div style="border:2px solid #e8eef7;width: 100%">
				                                        	<table width="100%">			                                        		
					                                        		<c:forEach var="row" items="${calendarViewForm.dateNavigator.items}">
					  													<c:forEach var="item" items="${row}">
					  														<c:if test="${calendarViewForm.view == 'weekly' && item.selected}">				  																															 		
																				<tr style="width: 100%">
																					<td align="left" vAlign="top" width="30px" style="font-size:12px;color:White;background-color: #3754A1;font-family:Tahoma;border-bottom:1px solid #ffffff;">
																					   	<span id="calenderSubFont" style="width:25px">
																					       	<digi:trn key="aim:dayOfWeek${item.dayOfWeek}">${item.dayOfWeek}</digi:trn>
																					   	</span>
																					   	<br>
																					   	<a href="#" style="font-size:10px;color:White;font-family:Tahoma;" onclick="submitFilterForm('${calendarViewForm.view}', '${item.timestamp}');return(false);">
																					  		${item.dayOfMonth}<c:if test="${item.dayOfMonth>3}"><digi:trn key="calendar:dayPrefix">th</digi:trn></c:if>
																					   		<digi:trn key="calendar:${item.month}">${item.month}</digi:trn>
																					   	</a>
																					</td>
																					<td width="95%">
																						<table width="100%" cellpadding="2">																						
																							<c:forEach var="ampCalendarGraph" items="${calendarViewForm.ampCalendarGraphs}">																																										
																								<c:set var="startDay">${ampCalendarGraph.ampCalendar.calendarPK.startDay}</c:set>
																						        <c:set var="endDay">${ampCalendarGraph.ampCalendar.calendarPK.endDay}</c:set>
														                        				<c:set var="endMonth">${ampCalendarGraph.ampCalendar.calendarPK.endMonth+1}</c:set>
														                        				<c:set var="startMonth">${ampCalendarGraph.ampCalendar.calendarPK.startMonth+1}</c:set>
														                        				<c:set var="currentMonth">${calendarViewForm.baseDateBreakDown.month}</c:set>
														                        				<c:set var="iterationBeginIndex">
														                        					<c:if test="${(fn:length(ampCalendarGraph.graphItems)-1) <0}"> 0</c:if>
														                        					<c:if test="${(fn:length(ampCalendarGraph.graphItems)-1) >=0}"> ${fn:length(ampCalendarGraph.graphItems)-1}</c:if>
														                        				</c:set>			                        				
														                        				<c:if test="${startMonth==currentMonth}">
														                              				<c:if test="${endMonth==currentMonth}">
															                                			<c:if test="${item.dayOfMonth >=startDay && item.dayOfMonth<=endDay && item.selected}">
															                                				<tr>
																                                				<td align="center" style="padding:6px;border-bottom:solid 1px #CCECFF;width: 70px;" width="80">																	                                			
																			                                		<div style="font-weight:Bold;text-align:center;color:Black;background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
																			                                			<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
																												          	<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
																												          		${ampCalendarEventItem.title}
																												           	</c:forEach>
																												      	</digi:link>
																			                                		</div>																		                                		
																		                                		</td>
																	                                		</tr>
															                                			</c:if>
															                              			</c:if>
														                              							
														                              				<c:if test="${endMonth!=currentMonth}">
															                               	 			<c:if test="${(item.dayOfMonth>=startDay || (item.dayOfMonth<startDay && item.dayOfMonth<endDay)) && item.selected}">
															                               	 				<tr>
															                               	 					<td align="center" style="padding:6px;border-bottom:solid 1px #CCECFF;width: 70px;" width="80">																	                                			
																			                                		<div style="font-weight:Bold;text-align:center;color:Black;background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
																			                                			<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
																												          	<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
																												          		${ampCalendarEventItem.title}
																												           	</c:forEach>
																												      	</digi:link>
																			                                		</div>																		                                		
																		                                		</td>
															                               	 				</tr>								                               	 																									        	
															                                			</c:if>
															                              			</c:if>
														                              			</c:if>
														                              						
														                              			<!-- Start Month != Current Month -->											                              			 		                              						
														                              			<c:if test="${startMonth!=currentMonth && currentMonth==endMonth}">			                                						
														                                			<c:if test="${(item.dayOfMonth>=startDay || item.dayOfMonth<=endDay) && item.selected}">
														                                				<tr>
														                                					<td align="center" style="padding:6px;border-bottom:solid 1px #CCECFF;width: 70px;" width="80">																	                                			
																			                                	<div style="font-weight:Bold;text-align:center;color:Black;background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
																			                                		<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
																											          	<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
																											          		${ampCalendarEventItem.title}
																											           	</c:forEach>
																											      	</digi:link>
																			                                	</div>																		                                		
																		                                	</td>
																                                		</tr>				                                  
														                                			</c:if>
														                              			</c:if>
														                              						
														                              			<c:if test="${startMonth!=currentMonth && endMonth!=currentMonth}">
														                              				<tr>
															                              				<td align="center" style="padding:6px;border-bottom:solid 1px #CCECFF;width: 70px;" width="80">																	                                			
																			                             	<div style="font-weight:Bold;text-align:center;color:Black;background-color:${ampCalendarGraph.ampCalendar.eventType.color};">
																			                                	<digi:link href="/showCalendarEvent.do~ampCalendarId=${ampCalendarGraph.ampCalendar.calendarPK.calendar.id}~method=preview~resetForm=true">
																											       	<c:forEach var="ampCalendarEventItem" items="${ampCalendarGraph.ampCalendar.calendarPK.calendar.calendarItem}">
																											       		${ampCalendarEventItem.title}
																											       	</c:forEach>
																											   	</digi:link>
																			                                </div>																		                                		
																		                                </td>
																	                              	</tr>									                              					                                						
														                              			</c:if>											                              																																								
																							</c:forEach>																						
																						</table>																				
																					</td>
																				</tr>
																				<tr height="3px" bgcolor="#e8eef7">
					  																<td bgcolor="#e8eef7" colspan="2"/>
					  															</tr>
																			</c:if>																		
					  													</c:forEach>
					  												</c:forEach>
				  											</table>
				  										</div>
				  									</td>
				  								</tr>
				  							</table>
				  						</td>
				  					</tr>
				  				</c:if>
				  				<!-- Weekly view End -->							
							</tr>            
				         </table>
					</td>
				</tr>
	  		</table>
	  	</td>
 	</tr> 
</table>
