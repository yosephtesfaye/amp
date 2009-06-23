<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="/taglib/struts-bean" prefix="bean"%>
<%@ taglib uri="/taglib/struts-logic" prefix="logic"%>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/taglib/struts-html" prefix="html"%>
<%@ taglib uri="/taglib/struts-nested" prefix="nested"%>
<%@ taglib uri="/taglib/digijava" prefix="digi"%>
<%@ taglib uri="/taglib/aim" prefix="aim" %>
<%@ taglib uri="/taglib/fmt" prefix="fmt" %>

<digi:form action="/parisindicator" type="org.digijava.module.parisindicator.form.PIForm" name="parisIndicatorForm">
	<table cellspacing="0" cellpadding="0" border="1" 
	 width="100%" style="font-family: Arial, Helvetica, sans-serif; padding-right:5px; padding-left:5px; padding-top:5px;border-top-style:hidden;border-right-style:hidden;border-left-style:hidden;border-bottom-style:hidden">
	    <tr align="center"  bgcolor="#CCCCFF">
	        <td width="15%" height="33">
	            <div align="center">
	                <strong><digi:trn key="aim:donors">Donor(s)</digi:trn></strong>
	            </div>
	        </td>
	        <td width="5%" height="33">
	            <div align="center">
	                <strong><digi:trn key="aim:disbursmentYear">Disbursement Year</digi:trn></strong>
	            </div>
	        </td>
			<td width="20%" height="33">
			  <div align="center">
			      <strong><digi:trn>Budget support aid flows provided in the context of programme based approach</digi:trn></strong>
			  </div>
			</td>
			<td width="20%" height="33">
			  <div align="center">
			      <strong><digi:trn>Other aid flows provided in the context of programme based approach</digi:trn></strong>
			  </div>
			</td>
			<td width="20%" height="33">
	            <div align="center">
	                <strong><digi:trn>Total aid flows provided</digi:trn></strong>
	            </div>
	        </td>
	        <td width="20%" height="33">
                <div align="center">
                    <strong><digi:trn>Proportion of aid flows provided in the context of programme based approach</digi:trn></strong>
                </div>
            </td>
		</tr>
		<logic:empty name="parisIndicatorForm" property="mainTableRows">
	        <tr>
	            <td width="100%" align="center" height="65" colspan="6" />
	                <div align="center">
	                    <strong><font color="red"><digi:trn key="aim:noSurveyDataFound">No survey data found.</digi:trn></font></strong>
	                </div>
	            </td>
	        </tr>
	    </logic:empty>
	    <logic:notEmpty name="parisIndicatorForm" property="mainTableRows">
	       <%int counter = 0; %>
	       <bean:define id="color" value="" type="String"/>
	       <logic:iterate id="element" name="parisIndicatorForm" property="mainTableRows" indexId="index" 
	        type="org.digijava.module.parisindicator.helper.PIReport9Row">
	           <logic:equal name="element" property="year" value="${parisIndicatorForm.selectedStartYear}">
                   <%counter++;%>
               </logic:equal>
	           <%if(counter%2 == 0) color = "bgcolor=#EBEBEB"; else color = "";%>
	           <tr <%=color%> >
	               <logic:equal name="element" property="year" value="${parisIndicatorForm.selectedStartYear}">
		               <td align="center" rowspan="${parisIndicatorForm.selectedEndYear + 1 - parisIndicatorForm.selectedStartYear}" height="65">
		                   <strong><digi:trn><bean:write name="element" property="donorGroup.orgGrpName"/></digi:trn></strong>
		               </td>
	               </logic:equal>
	               <td align="center">
	                   <bean:write name="element" property="year"/>
	               </td>
	               <td align="center">
                       <aim:formatNumber value="${element.column1}"/>
                   </td>
                   <td align="center">
                       <aim:formatNumber value="${element.column2}"/>
                   </td>
                   <td align="center">
                       <aim:formatNumber value="${element.column3}"/>
                   </td>
                   <td align="center">
                       <fmt:formatNumber type="number" value="${element.column4}" pattern="###" maxFractionDigits="0" />%
                   </td>
	           </tr>
	       </logic:iterate>
	    </logic:notEmpty>
	</table>
</digi:form>