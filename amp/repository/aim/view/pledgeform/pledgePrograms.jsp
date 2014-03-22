<%-- renders the programs part of the Pledge Form --%>
<%-- the HTML is ready to be included in the page per se, so no css/javascript includes here! --%>
<%@page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/taglib/jstl-core" prefix="c" %>
<%@ taglib uri="/taglib/aim" prefix="aim" %>
<%@ taglib uri="/taglib/struts-html" prefix="html" %>
<%@ taglib uri="/taglib/digijava" prefix="digi" %>
<%@ taglib uri="/taglib/fieldVisibility" prefix="field" %>
<%@ taglib uri="/taglib/featureVisibility" prefix="feature" %>
<%@ taglib uri="/taglib/moduleVisibility" prefix="module" %>
<%@ page import="org.digijava.module.fundingpledges.form.PledgeForm"%>

<digi:instance property="pledgeForm" />

<div id='pledge_form_programs_data'>
	<div class="highlight text-center h5 bold"><digi:trn key="aim:Program">Program</digi:trn></div>
		<c:if test="${empty pledgeForm.allUsedRootProgs}">
			<div class="text-center"><h3><digi:trn>No Programs</digi:trn></h3></div>
		</c:if>
	<c:if test="${not empty pledgeForm.allUsedRootProgs}">
		<c:forEach var="rootTheme" items="${pledgeForm.allUsedRootProgs}">
			<div class="amp-subgroup">
			<div class="highlight text-center h6 bold"><c:out value="${rootTheme.name}" /></div>
			<table class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th class="col-xs-8 text-right"><digi:trn>Program Name</digi:trn></th>
						<th class="col-xs-3 text-center"><digi:trn>Percentage</digi:trn></th>
						<th class="col-xs-1 text-center"><digi:trn>Delete</digi:trn></th>
					</tr>
				</thead>
				<tbody>	
					<c:forEach var="prog" items="${pledgeForm.selectedProgs}" varStatus="index">
						<c:set var="indexLoc" value="${indexLoc+1}" />
						<c:if test="${prog.rootId eq rootTheme.keyAsLong}">
							<tr>
								<td class="col-xs-8 text-right"><c:out value="${prog.hierarchicalName}" /></td>
								<td class="col-xs-3">
									<html:text name="prog" indexed="true" property="percentage" size="5"  onblur="programsController.onPercentageBlur(this);" styleClass="form-control input-sm input-pledges-programs" />
								</td>
								<td class="col-xs-1"><button type="button" onclick="programsController.onDelete(this);" class="btn btn-danger btn-xs"><digi:trn>Delete</digi:trn></button></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</c:forEach>
	</c:if>
</div>
