<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/taglib/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglib/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/taglib/struts-html" prefix="html" %>
<%@ taglib uri="/taglib/digijava" prefix="digi" %>
<%@ taglib uri="/taglib/jstl-core" prefix="c" %>

<digi:context name="digiContext" property="context" />

<table width="100%" cellPadding="0" cellSpacing="0" vAlign="top" align="left" border=0>
<tr><td width="100%" vAlign="top" align="left">
<!--  AMP Admin Logo -->
<jsp:include page="teamPagesHeader.jsp" flush="true" />
<!-- End of Logo -->
</td></tr>
<tr><td width="100%" vAlign="top" align="left">
<table bgColor=#ffffff cellPadding=0 cellSpacing=0 width="100%" vAlign="top" align="center" border=0>
	<tr>
		<td class=r-dotted-lg width="10">&nbsp;</td>
		<td align=left vAlign=top class=r-dotted-lg>
			<table width="98%" cellSpacing="3" cellPadding="1" vAlign="top" align="left">
				<tr><td>
					<table width="100%" cellSpacing="1" cellPadding="1" vAlign="top">
						<tr>
							<td>
								<span class=crumb>
								<bean:define id="translation">
									<digi:trn key="aim:clickToViewMyDesktop">Click here to view MyDesktop </digi:trn>
								</bean:define>
								<digi:link href="/viewMyDesktop.do" styleClass="comment"  title="<%=translation%>">
									<digi:trn key="aim:portfolio">Portfolio</digi:trn>
								</digi:link>&nbsp;&gt;&nbsp;								
								<digi:trn key="aim:relatedLinks">Related Links</digi:trn>
								</span>
							</td>
						</tr>
					</table>
				</td></tr>
				<tr> <td>
					<digi:errors/>
				</td></tr>
				<tr><td>
					<table width="700" cellPadding=4 cellSpacing=1 bgcolor="#aaaaaa">		
						<tr>
							<td align="center" class="textalb">
								<digi:trn key="aim:relatedLinks">Related Links</digi:trn>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td>
								<bean:size id="linksCount" name="myLinks" scope="session" />
								<c:if test="${linksCount == 0}">
									<b>No Links</b>								
								</c:if>
								<c:if test="${linksCount > 0}">
									<table width="100%" cellPadding=5 cellSpacing=1 bgcolor="#aaaaaa">
									<c:forEach var="document" items="${myLinks}" varStatus="status">
										<tr bgcolor="#f4f4f2">
											<td>
												Title: <b><c:out value="${document.title}"/></b> <br>
												<c:if test="${!empty document.docDescription}">
													Description: <c:out value="${document.docDescription}"/><br>
												</c:if>
												<c:if test="${document.isFile == false}">
													<i>URL :
													<a href="<c:out value="${document.url}"/>" target="_blank">
													<c:out value="${document.url}"/></a></i>												
												</c:if>
												<c:if test="${document.isFile == true}">
													<i>File :
													<a href="<%=digiContext%>/cms/downloadFile.do?itemId=<c:out value="${document.docId}"/>">
													<c:out value="${document.fileName}"/></i></a>
												</c:if>		
												<br>
												
												<a href="/deleteMemberLink.do?id=<c:out value="${document.docId}"/>">Delete</a>
											</td>	
										</tr>
									</c:forEach>
									</table>
								</c:if>
							</td>
						</tr>
					</table>
				</td></tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</td>
		<td width="10">&nbsp;</td>
	</tr>
</table>
</td></tr>
</table>

