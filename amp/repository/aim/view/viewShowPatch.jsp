<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/taglib/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglib/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/taglib/struts-html" prefix="html" %>
<%@ taglib uri="/taglib/digijava" prefix="digi" %>
<%@ taglib uri="/taglib/jstl-core" prefix="c" %>
<%@ taglib uri="/taglib/jstl-functions" prefix="fn" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.digijava.module.aim.form.ListAppliedPatchesForm" %>

<digi:instance property="aimListAppliedPatchesForm" />

<!--  AMP Admin Logo -->
<jsp:include page="teamPagesHeader.jsp"  />
<!-- End of Logo -->
<html:hidden property="event" value="view"/>

<table bgColor=#ffffff cellpadding="0" cellspacing="0" width=760>
  <tr>
    <td class=r-dotted-lg width=14>&nbsp;</td>
    <td align=left class=r-dotted-lg valign="top" width=750>
      <table cellPadding=5 cellspacing="0" width="100%" border="0">
        <tr>
          <!-- Start Navigation -->
          <td height=33>
          
          <span class=crumb>
            <c:set var="clickToViewAdmin">
              <digi:trn key="aim:clickToViewAdmin">Click here to goto Admin Home</digi:trn>
            </c:set>
            <digi:link href="/admin.do" styleClass="comment" title="${clickToViewAdmin}" >
              <digi:trn key="aim:AmpAdminHome">
              Admin Home
              </digi:trn>
            </digi:link>&nbsp;&gt;&nbsp;
            <digi:link href="/ListAppliedPatches.do" styleClass="comment" title="${clickToViewAdmin}">
              <digi:trn key="aim:ListAppliedPatches">
              List Applied Patches
              </digi:trn>
            </digi:link>
          </span>
          </td>
          <!-- End navigation -->
        </tr>
        
        <tr>
        	<td>
        		<bean:write name="aimListAppliedPatchesForm" property="content"/>
        	</td>
        	       	
        </tr>
        	
	</table>
</td>
</tr>
</table>
