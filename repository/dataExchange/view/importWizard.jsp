<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="/taglib/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglib/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/taglib/struts-html" prefix="html" %>
<%@ taglib uri="/taglib/digijava" prefix="digi" %>
<%@ taglib uri="/taglib/jstl-core" prefix="c" %>

<%@ taglib uri="/taglib/fieldVisibility" prefix="field" %>
<%@ taglib uri="/taglib/featureVisibility" prefix="feature" %>
<%@ taglib uri="/taglib/moduleVisibility" prefix="module" %>
<%@ taglib uri="/taglib/jstl-functions" prefix="fn" %>



<script type="text/javascript" src="/TEMPLATE/ampTemplate/script/yui/yahoo-dom-event.js"></script>

<script type="text/javascript">
  if (YAHOOAmp != null){
    var YAHOO = YAHOOAmp;
  }
  var tree;
  
</script>

    <link rel="stylesheet" type="text/css" href="/TEMPLATE/ampTemplate/css/yui/treeview.css" />
    <link rel="stylesheet" type="text/css" href="/TEMPLATE/ampTemplate/css/yui/fonts-min.css" />
    <link rel="stylesheet" type="text/css" href="/TEMPLATE/ampTemplate/css/yui/tabview.css" />

    <script type="text/javascript" src="/TEMPLATE/ampTemplate/script/yui/logger-min.js"></script>
    <script type="text/javascript" src="/TEMPLATE/ampTemplate/script/yui/treeview-debug.js"></script>
    <script type="text/javascript" src="/TEMPLATE/ampTemplate/script/yui/tabview-min.js"></script>

    <script type="text/javascript" src="/repository/dataExchange/view/scripts/TaskNode.js"></script>

<style type="text/css">

.ygtvcheck0 { background: url(/TEMPLATE/ampTemplate/images/yui/check0.gif) 0 0 no-repeat; width:16px; cursor:pointer }
.ygtvcheck1 { background: url(/TEMPLATE/ampTemplate/images/yui/check1.gif) 0 0 no-repeat; width:16px; cursor:pointer }
.ygtvcheck2 { background: url(/TEMPLATE/ampTemplate/images/yui/check2.gif) 0 0 no-repeat; width:16px; cursor:pointer }

#expandcontractdiv {border:1px solid #336600; background-color:#FFFFCC; margin:0 0 .5em 0; padding:0.2em;}
#treeDiv1 { background: #fff }
</style> 


	<link rel="stylesheet" type="text/css" href="<digi:file src='module/aim/scripts/tab/assets/tabview.css'/>">
	<link rel="stylesheet" type="text/css" href="<digi:file src='module/aim/scripts/panel/assets/border_tabs.css'/>">
	<link rel="stylesheet" type="text/css" href="<digi:file src='module/aim/css/reportWizard/reportWizard.css'/>">
	<link rel="stylesheet" type="text/css" href="<digi:file src='module/aim/css/filters.css'/>">

	<br>
	<br>

  
	<script type="text/javascript">
	YAHOOAmp.namespace("YAHOOAmp.amp.dataExchangeImport");
	YAHOOAmp.amp.dataExchangeImport.numOfSteps	= 3;
		
	YAHOOAmp.amp.dataExchangeImport.tabLabels	= new Array("tab_select_filed", "tab_additional_filed", "tab_filter");
		
        function navigateTab(value){
        	YAHOOAmp.amp.dataExchangeImport.tabView.set("activeIndex", YAHOO.amp.dataExchange.tabView.get("activeIndex")+value);
        }
		
		
		function initializeDragAndDrop() {
			var height			= Math.round(YAHOO.util.Dom.getDocumentHeight() / 2.3);
			
			YAHOOAmp.amp.dataExchangeImport.tabView 		= new YAHOO.widget.TabView('wizard_container');
			YAHOOAmp.amp.dataExchangeImport.tabView.addListener("contentReady", treeInit);
		}


    function treeInit() {
      YAHOOAmp.amp.dataExchangeImport.tabView     = new YAHOO.widget.TabView('wizard_container');
      buildRandomTaskNodeTree();
    }
    
    //handler for expanding all nodes
    YAHOOAmp.util.Event.on("expand", "click", function(e) {
      tree.expandAll();
      YAHOOAmp.util.Event.preventDefault(e);
    });
    
    //handler for collapsing all nodes
    YAHOOAmp.util.Event.on("collapse", "click", function(e) {
      tree.collapseAll();
      YAHOOAmp.util.Event.preventDefault(e);
    });

    //handler for checking all nodes
    YAHOOAmp.util.Event.on("check", "click", function(e) {
      checkAll();
      YAHOOAmp.util.Event.preventDefault(e);
    });
    
    //handler for unchecking all nodes
    YAHOOAmp.util.Event.on("uncheck", "click", function(e) {
      uncheckAll();
      YAHOOAmp.util.Event.preventDefault(e);
    });


    YAHOOAmp.util.Event.on("getchecked", "click", function(e) {
      YAHOOAmp.util.Event.preventDefault(e);
    });

    //Function  creates the tree and 
    //builds between 3 and 7 children of the root node:
      function buildRandomTaskNodeTree() {
    
      //instantiate the tree:
          tree = new YAHOOAmp.widget.TreeView("dataExportTree");
            

      //The tree is not created in the DOM until this method is called:
          tree.draw();
      }


      function checkAll() {
          var topNodes = tree.getRoot().children;
          for(var i=0; i<topNodes.length; ++i) {
              topNodes[i].checkAll();
          }
      }

      function uncheckAll() {
          var topNodes = tree.getRoot().children;
          for(var i=0; i<topNodes.length; ++i) {
              topNodes[i].unCheckAll();
          }
      }

      // Gets the labels of all of the fully checked nodes
      // Could be updated to only return checked leaf nodes by evaluating
      // the children collection first.
       function getCheckedNodes(nodes) {
           nodes = nodes || tree.getRoot().children;
           checkedNodes = [];
           for(var i=0, l=nodes.length; i<l; i=i+1) {
               var n = nodes[i];
               //if (n.checkState > 0) { // if we were interested in the nodes that have some but not all children checked
               if (n.checkState === 2) {
                   checkedNodes.push(n); // just using label for simplicity
                   alert(n.aid+' '+n.label);
                   if (n.hasChildren()) {
                       checkedNodes = checkedNodes.concat(getCheckedNodes(n.children));
                    }
               }
           }

           return checkedNodes;
       }  


       function exportActivity(){
//        getCheckedNodes();
          var selTeamId = document.getElementById('teamId');

          if (selTeamId.value != "-1"){
            var form = document.getElementById('form');
            form.action = "/dataExchange/export.do?method=export";
            form.target="_self"
            form.submit();
          } else {
              alert('please select one team');
          }
      }
           
		YAHOOAmp.util.Event.addListener(window, "load", treeInit) ;
	</script>


<table bgColor=#ffffff cellPadding=0 cellSpacing=0 width="85%">
	<tr>
		<td valign="bottom">
				
		</td>
	</tr>
	<tr>
		<td align="left" vAlign="top">
		<digi:form action="/export.do?method=export" method="post" styleId="form">
		<span id="formChild" style="display:none;">&nbsp;</span>

        <span class="subtitle-blue">
          &nbsp;Data Importer
        </span>		
		
		<div style="color: red; text-align: center; visibility: hidden" id="savingReportDiv">

		</div>
		<br />
		<div id="wizard_container" class="yui-navset">
    		<ul class="yui-nav">
    			<li id="tab_select_filed" class="selected"><a href="#type_step_div"><div>1. Field Selection</div></a> </li>
    			<li id="tab_additional_filed" class="enabled"><a href="#columns_step_div"><div>2. Additional Fields</div></a> </li>
    			<li id="hierachies_tab_label" class="enabled"><a href="#hierarchies_step_div"><div>3. Team Selection and Filters</div></a> </li>
    		</ul>
			<div class="yui-content" style="background-color: #EEEEEE">
				<div id="tab_select_filed" class="yui-tab-content" style="padding: 0px 0px 1px 0px;" >
                    <c:set var="stepNum" value="0" scope="request" />
                    <jsp:include page="toolbar.jsp" />
					<div style="height: 355px;">
    					<table cellpadding="5px" style="vertical-align: middle" width="100%">
    						<tr>
        						<td width="47%" align="center">
        							<span class="list_header">
        								<digi:trn key="rep:wizard:availableColumns">Available Fields</digi:trn>
        							</span>
        							<div id="source_col_div" class="draglist">
                  <div id="expandcontractdiv">
                    <a id="expand" href="#">Expand all</a>
                    <a id="collapse" href="#">Collapse all</a>
                  
                    <a id="check" href="#">Check all</a>
                    <a id="uncheck" href="#">Uncheck all</a>
                  </div>
                  <div id="dataExportTree"></div>
        
        							</div>
        						</td>
    						</tr>
    					</table>
					</div>
				</div>
				<div id="tab_additional_filed"  class="yui-tab-content" align="center" style="padding: 0px 0px 1px 0px; display: none;">
                    <c:set var="stepNum" value="1" scope="request" />
                    <jsp:include page="toolbar.jsp" />
                    Select additional fields
				</div>
				<div id="tab_filter"  class="yui-tab-content"  style="padding: 0px 0px 1px 0px; display: none;">
                    <c:set var="stepNum" value="2" scope="request" />
                    <jsp:include page="toolbar.jsp" />
                    <table cellpadding="15px" width="100%" align="center" border="0" >
                      <tr>
                        <td width="46%" style="vertical-align: top;">
	                       <span class="list_header">Donors</span>
                           <br/>
                             <span >Donor Type</span>
                             <br/>
                             <html:select name="deExportForm" property="donorTypeSelected"  style="width: 400px;" multiple="true" size="3">
                               <c:forEach var="fVar" items="${deExportForm.donorTypeList}" varStatus="lStatus">
                                 <option value="${fVar.ampOrgTypeId}">${fVar.orgType}</option>
                               </c:forEach>
                             </html:select>
                             <br/>
                             <span >Donor Group</span>
                             <br/>
                             <html:select name="deExportForm" property="donorGroupSelected" style="width: 400px;" multiple="true"  size="3">
                               <c:forEach var="fVar" items="${deExportForm.donorGroupList}" varStatus="lStatus">
                                 <option value="${fVar.ampOrgGrpId}">${fVar.orgGrpName}</option>
                               </c:forEach>
                             </html:select>
                             <br/>
                             <span >Donor Agency</span>
                             <br/>
                             <html:select name="deExportForm" property="donorAgencySelected"  style="width: 400px;" multiple="true"  size="3">
                               <c:forEach var="fVar" items="${deExportForm.donorAgencyList}" varStatus="lStatus">
                                 <option value="${fVar.ampOrgId}">${fVar.name}</option>
                               </c:forEach>
                             </html:select>
<%--                          
                           </div>
--%>
                           <br/>
                           <span class="list_header">Select Team</span>
                           <br/>
                           <html:select name="deExportForm" property="selectedTeamId" styleId="teamId" style="width: 400px;">
                             <option value="-1">Please select team</option>
                             <c:forEach var="fVar" items="${deExportForm.teamList}" varStatus="lStatus">
                               <option value="${fVar.ampTeamId}">${fVar.name}</option>
                             </c:forEach>
                           </html:select>
                        </td>
                        <td width="46%" style="vertical-align: top;">
                         <span class="list_header">Sectors</span>
                         <br/>
<%--                          
                         <div id="reportGroupDiv" style="padding: 15px 15px 15px 15px; border: 1px solid gray; background-color: white; position: relative;">
--%>
                             <span>Primary Sector</span>
                             <br/>
                             <html:select name="deExportForm" property="primarySectorsSelected"  style="width: 400px;" multiple="true"  size="3">
                               <c:forEach var="fVar" items="${deExportForm.primarySectorsList}" varStatus="lStatus">
                                 <option value="${fVar.ampSectorId}">${fVar.name}</option>
                               </c:forEach>
                             </html:select>
                             <span >Secondary Sector</span>
                             <br/>
                             <html:select name="deExportForm" property="secondarySectorsSelected" style="width: 400px;" multiple="true"  size="3">
                               <c:forEach var="fVar" items="${deExportForm.secondarySectorsList}" varStatus="lStatus">
                                 <option value="${fVar.ampSectorId}">${fVar.name}</option>
                               </c:forEach>
                             </html:select>
                         <br/>
<%--                          
                         <span class="list_header">Language</span>
                         <div id="reportGroupDiv" style="padding: 15px 15px 15px 15px; border: 1px solid gray; background-color: white; position: relative;">
                           <html:radio name="deExportForm" property="language" value="en" />English<br/>
                           <html:radio name="deExportForm" property="language" value="es" />Spanish<br/>
                           <html:radio name="deExportForm" property="language" value="fr" />French<br/>
                         </div>
--%>
                        </td>
                      </tr>
                    </table>
                    
                    
                  
                    <br/>
                  
                    
				</div>
			</div>
		</div>



		</digi:form>
	</td>
	</tr>
</table>

	