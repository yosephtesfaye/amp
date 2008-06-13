<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/taglib/struts-bean" prefix="bean"%>
<%@ taglib uri="/taglib/struts-logic" prefix="logic"%>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/taglib/struts-html" prefix="html"%>
<%@ taglib uri="/taglib/digijava" prefix="digi"%>
<%@ taglib uri="/taglib/jstl-core" prefix="c"%>

<digi:ref href="css/styles.css" type="text/css" rel="stylesheet" />

<digi:instance property="helpForm" />
<digi:form action="/helpActions.do?actionType=searchHelpTopic">


	<div id="content" class="yui-skin-sam" style="width: 100%;">
	<div id="demo" class="yui-navset"
		style="font-family: Arial, Helvetica, sans-serif;">

	<ul class="yui-nav">
		<li class="selected"><a
			title='<digi:trn key="aim:helpSearch">Search</digi:trn>'>
		<div>
		<digi:trn key="aim:helpSearch">Search</digi:trn>
		</div>
		</a></li>
	</ul>
	<div class="yui-content"
		style="height: auto; font-size: 11px; font-family: Verdana, Arial, Helvetica, sans-serif;">
	<div style="padding: 2; text-align: center">
		<html:text size="10" property="keywords" /> <c:set var="searchtpc">
		<digi:trn key="help:SearchText">Search Topic</digi:trn>
	</c:set> <input type="submit" class="dr-menu" value="${searchtpc}" /></div>
	</div>
	</div>
	</div>
	
	


</digi:form>