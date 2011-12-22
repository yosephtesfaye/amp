<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/taglib/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglib/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglib/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/taglib/struts-html" prefix="html" %>
<%@ taglib uri="/taglib/digijava" prefix="digi" %>
<%@ taglib uri="/taglib/jstl-core" prefix="c" %>

<link type="text/css" rel="stylesheet" href="/TEMPLATE/ampTemplate/js_2/yui/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="/TEMPLATE/ampTemplate/css_2/desktop_yui_tabs.css">
<link rel="stylesheet" type="text/css" href="/TEMPLATE/ampTemplate/css/yui/tabview.css">



<style>
    .yui-skin-sam .yui-dt thead th,.yui-skin-sam .yui-dt .yui-dt-data td {
        border-color: #CCC;
        border-style: none solid solid none;
        border-width: medium 1px 1px medium;
    }

  .yui-skin-sam .yui-dt  thead th {
	border-color: #FFF;
	background: #C7D4DB;
	color: #000;
	height: 30px;
	text-align: center;
}

    .yui-skin-sam .yui-dt th .yui-dt-liner {
        font-size: 12px;
        text-align: center;
        font-weight: bold;
        font-family: Arial, Verdana, Helvetica, sans-serif;
        border-color: #CCC;
    }

    .yui-skin-sam .yui-dt td .yui-dt-liner {
        font-size: 12px;
        font-family: Arial, Verdana, Helvetica, sans-serif;
    }

    .yui-skin-sam a.yui-pg-page {
        padding-right: 10px;
        font-size: 11px;
        border-right: 1px solid rgb(208, 208, 208);
    }

    .yui-skin-sam .yui-pg-pages {
        border: 0px;
        padding-left: 0px;
    }

    .yui-pg-current-page {
        background-color: #FFFFFF;
        color: rgb(208, 208, 208);
        padding: 0px;
    }

    .current-page {
        background-color: #FF6000;
        color: #FFFFFF;
        margin-right: 5px;
        font-weight: bold;
    }

    .yui-pg-last {
        border: 0px
    }

    .yui-skin-sam span.yui-pg-first,.yui-skin-sam span.yui-pg-previous,.yui-skin-sam span.yui-pg-next,.yui-skin-sam span.yui-pg-last
    {
        display: none;
    }

    .yui-skin-sam a.yui-pg-first {
        margin-left: 2px;
        padding-right: 7px;
        border-right: 1px solid rgb(208, 208, 208);
    }


</style>
<div id="popin" style="display: none">
    <div id="popinContent" class="content">
    </div>
</div>


<style type="text/css" media="print">
    .yui-skin-sam tr.yui-dt-selected td {
        border-style:solid;
        border-color:#000000;
    }
</style>

<style type="text/css">
    .mask {
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=80);
        background-color:#2f2f2f;
    }

    #popin .content { 
        overflow:auto; 
        height:455px; 
        background-color:fff; 
        padding:10px; 
    } 
    .bd a:hover {
        background-color:#ecf3fd;
        font-size: 10px; 
        color: #0e69b3; 
        text-decoration: none	  
    }
    .bd a {
        color:black;
        font-size:10px;
    }
    .toolbar{
        width: 50px;
        background: #addadd;
        background-color: #addadd;
        padding: 3px 3px 3px 3px;
        position: relative;
        top: 10px;
        left: 10px;
        bottom: 100px;
    }
    .toolbartable{
        border-color: #FFFFFF;
        border-width: 2px;
        border-bottom-width: 2px;
        border-right-width: 2px;
        border-left-width: 2px;
        border-style: solid;
    }
    .yui-tt {
        visibility: hidden;
        position: absolute;
        color: #000;
        background-color: #FFF;
        font-size: 11px;
        padding: 2px;
        border: 1px solid #CCC;
        width: auto;
    }
     .invisibleTable {
       display: none;
    }
     .visibleTable {
       display: block;
    }

</style>
<script language="JavaScript" type="text/javascript" src="<digi:file src="module/aim/scripts/asynchronous.js"/>"></script>
<!-- Individual YUI JS files -->
<script type="text/javascript" src="/TEMPLATE/ampTemplate/js_2/yui/element/element-min.js"></script>
<script type="text/javascript" src="/TEMPLATE/ampTemplate/js_2/yui/datasource/datasource-min.js"></script>
<script type="text/javascript" src="/TEMPLATE/ampTemplate/js_2/yui/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="/TEMPLATE/ampTemplate/js_2/yui/event/event-min.js"></script>
<script type="text/javascript" src="/TEMPLATE/ampTemplate/js_2/yui/json/json-min.js"></script>

<script type="text/javascript" src="/TEMPLATE/ampTemplate/js_2/yui/paginator/paginator-min.js"></script>
<script type="text/javascript" src="/TEMPLATE/ampTemplate/js_2/yui/datatable/datatable-min.js"></script>
<digi:instance property="aimWorkspaceForm" />
<script type="text/javascript">


    YAHOO.namespace("YAHOO.amp");

    var myPanel = new YAHOO.widget.Panel("newpopins", {
        width:"450px",
        height:"250px",
        fixedcenter: true,
        constraintoviewport: false,
        underlay:"none",
        close:true,
        visible:false,
        modal:true,
        draggable:true,
        context: ["showbtn", "tl", "bl"]
    });
    var panelStart;
    var checkAndClose=false;
    var lastFunction="";
   
	    
    function initWorkspaceManagerPopinScript() {
        var msg='\n<digi:trn>Select Indicator</digi:trn>';
        myPanel.setHeader(msg);
        myPanel.setBody("");
        myPanel.beforeHideEvent.subscribe(function() {
            myclose();
        }); 
        myPanel.render(document.body);
        panelStart = 0; 
		
    }
	
</script>
<script language="JavaScript">
    var tooltipPanel;
    var viewTeamDetails='';
    var myPaginator;
    var added='${param.added}';


    

    function initWorkspaceManagerScript(){
        initWorkspaceManagerPopinScript();
        initDynamicTable();
    }

    YAHOO.util.Event.addListener(window, "load", initWorkspaceManagerScript);
    function initDynamicTable() {
		
        YAHOO.example.XHR_JSON = new function() {
 	
       	    this.formatActions = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = 
                    "<a onclick='setViewTemDetails()' href=/aim/getWorkspace.do~dest=admin~event=edit~tId=" +oRecord.getData( 'ID' )+" title='<digi:trn>Click here to Edit Workspace</digi:trn>'>" + "<img vspace='2' border='0' src='/TEMPLATE/ampTemplate/imagesSource/common/application_edit.png'/>" + "</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "<a href='javascript:deleteWorkspace(" + oRecord.getData( 'ID' )+ ")' title='<digi:trn>Click here to Delete Workspace</digi:trn>'>" + "<img vspace='2' border='0' src='/TEMPLATE/ampTemplate/imagesSource/common/trash_16.gif'/>" + "</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "[<a href=\"JavaScript:openNpdSettingsWindow(" +oRecord.getData( 'ID' )+ ");\">"+"<digi:trn>Npd Settings</digi:trn>"+"</a>]"+"<input type='hidden' class='teamsOnpage' value='"+oRecord.getData( 'ID' )+"'/>"
            };
        
            this.formatActionsName = function(elCell, oRecord, oColumn, sData) {
                elCell.innerHTML = 
                    '<a href="JavaScript:showTeamDetails(' +oRecord.getData( 'ID' )+',  \''+oRecord.getData( 'name' )+'\');" title="<digi:trn>Click here to view Details</digi:trn>">' + oRecord.getData( 'name' ) + '</a>'
            };

            this.formatTeamName = function(elCell, oRecord, oColumn, sData) {
                var childrenWorkspaces=oRecord.getData( 'childrenWorkspaces' );
                var children="";
                if(childrenWorkspaces.length>0){
                    children+='<ul>';
                    for(var i=0;i<childrenWorkspaces.length;i++){
                        var childrenWorkspace=childrenWorkspaces[i];
                        children+='<li>'+childrenWorkspace.name+'</li>';
                    }
                    children+='</ul>';
                }
                var childrenOrganizations=oRecord.getData( 'childrenOrganizations' );
                var compOrgs='';
                if(childrenOrganizations.length>0){
                    compOrgs='<br/><digi:trn jsFriendly="true">Children Organization(s)</digi:trn>';
                    compOrgs+='<ul>';
                    for(var i=0;i<childrenOrganizations.length;i++){
                        var childOrg=childrenOrganizations[i];
                        compOrgs+='<li>'+childOrg.name+'</li>';
                    }
                    compOrgs+='</ul>';
                }
                var name=oRecord.getData( 'name' ).replace("\'", "\\'");
                elCell.innerHTML =
                    oRecord.getData( 'name' ) 
                    +'<div id="tooltip'+oRecord.getData( 'ID' )+'" style="z-index:1;display:none">'+
                    '<ul>'+
                    '<li><digi:trn>'+oRecord.getData( 'accessType' )+'</digi:trn></li>'+
                    '<li><digi:trn>Children (Workspaces)</digi:trn>:'+children+'</li>'+
                    '<li><digi:trn>Computation</digi:trn>:<digi:trn>'+oRecord.getData( 'computation' )+compOrgs+'</digi:trn></li>'+
                    '</ul>'+
                    '</div>';
            };
       
            this.myDataSource = new YAHOO.util.DataSource("/aim/searchWorkspaces.do?");
            this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
            this.myDataSource.connMethodPost = true;
            //this.myDataSource.connXhrMode = "queueRequests";
            this.myDataSource.responseSchema = {
                resultsList: "workspaces",
                fields: ["ID","name","accessType","computation","childrenOrganizations","childrenWorkspaces"],
                metaFields: {
                    totalRecords: "totalRecords" // Access to value in the server response
                }    
            };
        
        
            var myColumnDefs = [
                {key:"name", label:"<digi:trn>NAME</digi:trn>", sortable:true, width: 250,formatter:this.formatTeamName},
                {key:"actions", label:"<digi:trn>ACTIONS</digi:trn>", width: 160, formatter:this.formatActions,className:"ignore"}
            ];
  
            var div = document.getElementById('errors');

            var handleSuccess = function(o){
                if(o.responseText != undefined){
                    o.argument.oArgs.liner_element.innerHTML=o.responseText;
                }
            }

            var handleFailure = function(o){
                if(o.responseText !== undefined){
                    div.innerHTML = "<li>Transaction id: " + o.tId + "</li>";
                    div.innerHTML += "<li>HTTP status: " + o.status + "</li>";
                    div.innerHTML += "<li>Status code message: " + o.statusText + "</li>";
                }
            }
            // Create the Paginator 
            
            myPaginator = new YAHOO.widget.Paginator({ 
            	rowsPerPage:10,
	        	//totalRecords:document.getElementById("totalResults").value,
	        	containers : ["dt-pag-nav","dt-pag-nav2"], 
	        	template : "{CurrentPageReport}&nbsp;<span class='l_sm'><digi:trn>Results:</digi:trn></span>&nbsp;{RowsPerPageDropdown}<br/>{FirstPageLink}{PageLinks}{LastPageLink}", 
	        	pageReportTemplate		: "<span class='l_sm'><digi:trn>Showing items</digi:trn></span> <span class='txt_sm_b'>{startRecord} - {endRecord} <digi:trn>of</digi:trn> {totalRecords}</span>", 
	        	rowsPerPageOptions		: [10,25,50,100,{value:999999,text:'<digi:trn jsFriendly="true">All</digi:trn>'}],
	        	firstPageLinkLabel : 	"<digi:trn>first page</digi:trn>", 
	        	previousPageLinkLabel : "<digi:trn>prev</digi:trn>", 
	        	firstPageLinkClass : "yui-pg-first l_sm",
	        	lastPageLinkClass: "yui-pg-last l_sm",
	        	nextPageLinkClass: "yui-pg-next l_sm",
	        	previousPageLinkClass: "yui-pg-previous l_sm",
	        	rowsPerPageDropdownClass:"l_sm",
	        	nextPageLinkLabel		: '<digi:trn jsFriendly="true">next</digi:trn>',
	        	lastPageLinkLabel		: '<digi:trn jsFriendly="true">last page</digi:trn>',
	        	 // use custom page link labels
	            pageLabelBuilder: function (page,paginator) {
	                var curr = paginator.getCurrentPage();
	                if(curr==page){
	                	return "<span class='current-page'>&nbsp;&nbsp;"+page+"&nbsp;&nbsp;</span>|";
	                }
	                else{
	                	return page;
	                }
	                
	            }
            });   
            var myConfigs = {
                initialRequest: "sort=name&dir=asc&startIndex=0&results=10", // Initial request for first page of data
                dynamicData: true, // Enables dynamic server-driven data
                sortedBy : {key:"name", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
                //paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination
                paginator:myPaginator
            };
    	 
            this.myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, this.myDataSource, myConfigs);
            this.myDataTable.subscribe("rowMouseoverEvent", this.myDataTable.onEventHighlightRow); 
            this.myDataTable.subscribe("rowMouseoutEvent", this.myDataTable.onEventUnhighlightRow);
            this.myDataTable.subscribe("rowClickEvent", this.myDataTable.onEventSelectRow);
            this.myDataTable.subscribe("rowClickEvent", function (ev) {
                var target = YAHOO.util.Event.getTarget(ev);
                var record = this.getRecord(target);
                showTeamDetails(record.getData('ID'), record.getData('name'));
                hideToolTip();
            });
        
            //this.myDataTable.selectRow(this.myDataTable.getTrEl(0)); 
            // Programmatically bring focus to the instance so arrow selection works immediately 
            this.myDataTable.focus(); 
            var second=false;
            <c:if test="${not empty aimWorkspaceForm.currentPage}">
            this.myDataTable.subscribe('postRenderEvent',function(oArgs){
			if(second){
			    this.selectRow(this.getTrEl(${aimWorkspaceForm.currentRow}));
			    showTeamDetails(${aimWorkspaceForm.selectedWs},null);
			    second=false;
			}
            
            if(added=="true"){
            	if(${aimWorkspaceForm.currentPage}!=1){
					myPaginator.setPage(${aimWorkspaceForm.currentPage});
				    second=true;
				}
				else{
					this.selectRow(this.getTrEl(${aimWorkspaceForm.currentRow}));
				    second=false;
				} 
               added="false";
            }
            });
            </c:if>
          

            // Update totalRecords on the fly with value from server
            this.myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
                oPayload.totalRecords = oResponse.meta.totalRecords;
                return oPayload;
            }
            //further lines are for generating tooltips
            var showTimer,hideTimer;				
            var tt = new YAHOO.widget.Tooltip("myTooltip");
    	
            this.myDataTable.on('cellMouseoverEvent', function (oArgs) {
                if (showTimer) {
                    window.clearTimeout(showTimer);
                    showTimer = 0;
                }

                var target = oArgs.target;
                var column = this.getColumn(target);
                if (column.key == 'name') {
                    var record = this.getRecord(target);
    			
    	
                    var tooltipid="tooltip"+record.getData('ID');
                    var tooltipDiv=document.getElementById(tooltipid);
                    var tooltipText=tooltipDiv.innerHTML;
    		      

                    if(tooltipText!=null && tooltipText.length > 0){
                        var event=oArgs.event;
                        var x = 0;
                        var y = 0;

                        if (document.all) { //IE
                            x = event.clientX + document.body.scrollLeft;
                            y = event.clientY + document.body.scrollTop;
                        }
                        else {
                            x = event.pageX;
                            y = event.pageY;
                        }
                        var xy = [x,y];

                        showTimer = window.setTimeout(function() {
                            tt.setBody(tooltipText);
                            tt.cfg.setProperty('xyoffset',[10,10]);
                            tt.cfg.setProperty('xy',xy);
                            tt.show();
                            hideTimer = window.setTimeout(function() {
                                tt.hide();
                            },5000);
                        },500);
    					
                    }
    			
                }
            });
    	
            this.myDataTable.on('cellMouseoutEvent', function (oArgs) {
                if (showTimer) {
                    window.clearTimeout(showTimer);
                    showTimer = 0;
                }
                if (hideTimer) {
                    window.clearTimeout(hideTimer);
                    hideTimer = 0;
                }
                tt.hide();
            });
       
        };
  
	       
   
    };
    


    function setViewTemDetails() {
        viewTeamDetails='false';
    }

    function deleteWorkspace(id){
    	viewTeamDetails='false';
    	var msg = '<digi:trn jsFriendly="true">Delete this workspace?</digi:trn>';
    	if (confirm(msg)){
	        <digi:context name="deleteUrl" property="context/module/moduleinstance/deleteWorkspace.do"/>;
	        document.aimWorkspaceForm.action="${deleteUrl}?event=delete&tId="+id;
	        document.aimWorkspaceForm.target="_self";
	        document.aimWorkspaceForm.submit();	
    	}
    }
    
</script>



<script language="JavaScript">
    <!--
   
    //DO NOT REMOVE THIS FUNCTION --- AGAIN!!!!
    function mapCallBack(status, statusText, responseText, responseXML){
        window.location.reload();
    }
    
    
    var responseSuccess = function(o){
        var response = o.responseText; 
        myPanel.setBody(response);
        showContent();
    }
 
    var responseFailure = function(o){ 
        alert("Connection Failure!"); 
    }  
    var callback = 
        { 
        success:responseSuccess, 
        failure:responseFailure 
    };

    function showContent(){
        if (panelStart < 2){
            myPanel.show();
            panelStart = 2;
        }
        checkErrorAndClose();
    }
    function checkErrorAndClose(){
        if(checkAndClose==true){
            if(document.getElementsByName("someError")[0]==null || document.getElementsByName("someError")[0].value=="false"){
                myclose();
                refreshPage();
            }
            checkAndClose=false;			
        }
    }
    function refreshPage(){
        if(lastFunction==="showDetails"){
            showDetails();
        }
        lastFunction="";
    }

    function myclose(){
        myPanel.setBody("");
        myPanel.hide();	
        panelStart=1;
    	
    }
    

    function closeWindow() {
        myclose();
    }
    function showPanelLoading(msg){
        myPanel.setHeader(msg);	
        var bodymsg='<div style="text-align: center">' + 
        '<img src="/TEMPLATE/ampTemplate/js_2/yui/assets/skins/sam/loading.gif" border="0" height="17px"/>&nbsp;&nbsp;' + 
        '<digi:trn>Loading, please wait ...</digi:trn><br/><br/></div>'
        myPanel.setBody(bodymsg);
        showContent();
    }
    function addNewUser()	{
        var msg='\n<digi:trn>AMP View Settings</digi:trn>';
        showPanelLoading(msg);
            <digi:context name="commentUrl" property="context/module/moduleinstance/addUser.do"/>  
            var url = "<%=commentUrl %>";
        YAHOO.util.Connect.asyncRequest("POST",url, callback, '');
    }

    -->

</script>


<script language="JavaScript">
    var oldKeyword="", oldWorkspaceType="";
    function onDelete() {
        var flag = confirm('<digi:trn key="admin:workSpaceManager.deleteQuestion">Delete this workspace?</digi:trn>');
        return flag;
    }
    function openNpdSettingsWindow(ampTeamId){
        var msg='\n<digi:trn>AMP View Settings</digi:trn>';
        myPanel.cfg.setProperty("width","450px");
        myPanel.cfg.setProperty("height","250px");
        showPanelLoading(msg);
            <digi:context name="commentUrl" property="context/module/moduleinstance/npdSettingsAction.do"/>  
            var url = "<%=commentUrl %>";
        url+='?actionType=viewCurrentSettings&ampTeamId='+ampTeamId
        YAHOO.util.Connect.asyncRequest("POST",url, callback, '');
    }
    function addActionToURL(actionName){
        var fullURL=document.URL;
        var lastSlash=fullURL.lastIndexOf("/");
        var partialURL=fullURL.substring(0,lastSlash);
        return partialURL+"/"+actionName;
    }
    function getParams(){
        ret = "";
        ret += "keyword="+document.getElementsByName('keyword')[0].value+"&workspaceType="+document.getElementsByName('workspaceType')[0].value;
        return ret;
    }
	
    function resetPage(){
        if(oldKeyword != document.getElementsByName('keyword')[0].value  || oldWorkspaceType != document.getElementsByName('workspaceType')[0].value){
            oldKeyword=document.getElementsByName('keyword')[0].value;
            oldWorkspaceType=document.getElementsByName('workspaceType')[0].value;
                <digi:context name="commentUrl" property="context/module/moduleinstance/workspaceManager.do"/>  
                var url = "<%=commentUrl %>";
            url += "?"+getParams();
            var async=new Asynchronous();
            async.complete=initDynamicTable;
            async.call(url);
        }
    }
</script>
<script langauage="JavaScript" type="text/javascript">
	

    function validateValues(){
        var errmsg='';
        var ampTeamId=document.getElementById('ampTeamId').value;
        var width=document.getElementById('width').value;
        var height=document.getElementById('height').value;
        var angle=document.getElementById('angle').value;
        var pageSize=document.getElementById('pageSize').value;
        //*** Validate width
        if(parseInt(width)==(width-0)){		   	
            if(parseInt(width)<10 || parseInt(width)>1000){
                errmsg+='\n<digi:trn jsFriendly="true">Width must be in range from 10 to 1000</digi:trn>';
            }
        }else{
            errmsg+='\n<digi:trn jsFriendly="true">Please enter correct width</digi:trn>';
        }		 
        //***Validate height
        if(parseInt(height)==(height-0)) {
            if(parseInt(height)<10 || parseInt(height)>1000){
                errmsg+='\n<digi:trn jsFriendly="true">Height must be in range from 10 to 1000</digi:trn>';
            }
        }else{
            errmsg+='\n<digi:trn jsFriendly="true">Please enter correct height</digi:trn>';
        }		 
        //***Validate angle	

        if(angle!=''){
            if(parseInt(angle)==(angle-0)) {
                if(parseInt(angle)<0 || parseInt(angle)>90){
                    errmsg+='\n<digi:trn jsFriendly="true">Angle of inclination must be in range from 0 to 90</digi:trn>';
                }
            }else{
                errmsg+='\n<digi:trn jsFriendly="true">Please enter correct angle</digi:trn>';
            }
        }

        //***validate activities per page
        if(pageSize!=''){
            var validChars='^[0-9]*$';
            if(!pageSize.match(validChars)){
                errmsg+='\n<digi:trn>Please enter correct number of pages</digi:trn>';
            }
        }
		 
        //***Validate error messages
        if (errmsg==''){
            saveSettings(ampTeamId,width,height,angle,pageSize);
        } else{
            alert(errmsg);
            return false;
        }
    }

    function saveSettings(ampTeamId,width,height,angle,pageSize){
        lastTimeStamp = new Date().getTime();
            <digi:context name="changeSett" property="context/module/moduleinstance/npdSettingsAction.do?actionType=changeSettings"/>
            var params="&ampTeamId="+ampTeamId+"&width="+width+"&height="+height+"&angle="+angle+"&pageSize="+pageSize;
        var url = "${changeSett}"+params+'&timeStamp='+lastTimeStamp;
        checkAndClose=true;
        YAHOO.util.Connect.asyncRequest("POST",url+"?"+params, callback, '');
    }
</script>
<script language="JavaScript">

    function updateTableMembers(members){

        var demo       = YAHOO.util.Dom.get('demo'),
        tbl        = demo.getElementsByTagName('table')[0],
        tbody      = tbl.getElementsByTagName('tbody')[0],
        tmp        = document.createElement('div'),
        html       = ["<table id=\"dataTable\" class=\"inside\"><tbody>"],i,j = 1,l,item;

        if (members && members.length>0) {
            for (i = 0, l = members.length; i < l; ++i) {
                item = members[i];
                html[j++] = '<tr><td width="300" class="inside">';
                html[j++] = '<a href=\'javascript:showTeamMemberProfile("'+item.email+'")\' title=\'<digi:trn jsFriendly="true">Click to View Member Detais</digi:trn>\'>'+item.name+'</a>';
                html[j++] = '</td><td align=\'center\' width="100" class="inside">';
                html[j++] = '<a href=\'JavaScript:memberAction("edit",' +item.ID+')\' title=\'<digi:trn jsFriendly="true">Click here to Edit Team Member Details</digi:trn>\'>' + '<img vspace=\'2\' border=\'0\' src=\'/TEMPLATE/ampTemplate/imagesSource/common/application_edit.png\'/>' + '</a>'
                html[j++] = '&nbsp;&nbsp;&nbsp;&nbsp;<a href=\'JavaScript:memberAction("delete",' +item.ID+')\'  title=\'<digi:trn  jsFriendly="true">Click here to Delete Team Member</digi:trn>\'>' + '<img vspace=\'2\' border=\'0\' src=\'/TEMPLATE/ampTemplate/imagesSource/common/trash_16.gif\'/>' + '</a>'
                html[j++] = '</td></tr>';
            }
            document.getElementById('footerMessage').innerHTML='<em style="font-size:11px;"><digi:trn  jsFriendly="true">* Worskpace Manager</digi:trn></em>';
        } else {
            html[j++] = '<tr><td colspan="2"><em><digi:trn  jsFriendly="true">No Member data</digi:trn><em></td></tr>';
            document.getElementById('footerMessage').innerHTML='';
        }
        html[j] = "</tbody></table>";

        tmp.innerHTML = html.join('');

        tbl.replaceChild(tmp.getElementsByTagName('tbody')[0], tbody);
    
       
    }
    function memberAction(action, id){
        var msg='<digi:trn>Delete Member</digi:trn>';
        if(action==='edit'){
            msg='<digi:trn>Edit Member</digi:trn>'
        }
        myPanel.cfg.setProperty("width","400px");
        myPanel.cfg.setProperty("height","350px"); 
        showPanelLoading(msg);	
            <digi:context name="commentUrl" property="context/module/moduleinstance/getTeamMemberDetailsJSON.do"/>;  
        var url = "<%=commentUrl %>";
        url += "?action="+action+"&id="+id;
        YAHOO.util.Connect.asyncRequest("POST",url, callback, '');
    }
    function confirmActionMember(){
        if(validateAction()){
            checkAndClose=true;
            lastFunction="showDetails";
                <digi:context name="commentUrl" property="context/module/moduleinstance/updateTeamMemberJSON.do"/>  
                var url = "<%=commentUrl %>";
            url += "?teamId="+document.getElementsByName('teamId')[0].value+
                "&teamMemberId="+document.getElementsByName('teamMemberId')[0].value+
                "&action="+document.getElementsByName('action')[0].value+
                "&userId="+document.getElementsByName('userId')[0].value+
                "&name="+document.getElementsByName('name')[0].value+
                "&role="+document.getElementsByName('role')[0].value;
            YAHOO.util.Connect.asyncRequest("POST",url, callback, '');
        }	
    }

    function validateAction(){
        if(document.getElementsByName('action')[0].value==='edit' && document.getElementsByName('role')[0].selectedIndex==0){
            alert("<digi:trn>Please select the role</digi:trn>");
            return false;
        }
        return true;			
    }
    function updateTableActivities(members){

        var demo       = YAHOO.util.Dom.get('demo'),
        tbl        = demo.getElementsByTagName('table')[0],
        tbody      = tbl.getElementsByTagName('tbody')[0],
        tmp        = document.createElement('div'),
        html       = ["<table id=\"dataTable\" class=\"inside\"><tbody>"],i,j = 1,l,item;

        if (members && members.length>0) {
            for (i = 0, l = members.length; i < l; ++i) {
                item = members[i];
                html[j++] = '<tr><td width="300" class="inside">';
                html[j++] = item.name;
                html[j++] = '</td ><td align=\'center\' width="100" class="inside">';
                html[j++] = '<a href=\'JavaScript:removeActivity('+item.ID+')\' onClick=\'return confirmDelete()\' title=\'<digi:trn jsFriendly="true">Click here to Delete Activity</digi:trn>\'>' + '<img vspace=\'2\' border=\'0\' src=\'/TEMPLATE/ampTemplate/imagesSource/common/trash_16.gif\' />' + '</a>';
                html[j++] = '</td></tr>';
            }
        
        } else {
            html[j++] = '<tr><td colspan="2"><em><digi:trn jsFriendly="true">No Activities</digi:trn></em></td></tr>';
        }
        document.getElementById('footerMessage').innerHTML='';
        html[j] = "</tbody></table>";

        tmp.innerHTML = html.join('');

        tbl.replaceChild(tmp.getElementsByTagName('tbody')[0], tbody);
    
        
    }
    function removeActivity(id){
            <digi:context name="commentUrl" property="context/module/moduleinstance/removeTeamActivityJSON.do"/>  
            var url = "<%=commentUrl %>";
        url += "?selActivities="+id+"&teamId="+ document.getElementsByName('teamId')[0].value;
        YAHOO.util.Connect.asyncRequest('GET',url,{

            success : function (res) {
                document.getElementById('ws_go').disabled=true;
                var activities;
                try {
                    activities = YAHOO.lang.JSON.parse(res.responseText);
                    updateTableActivities(activities);
                }
                catch(e) {
                    alert("<digi:trn>Error getting activity data</digi:trn>");
                }
                finally {
                    document.getElementById('ws_go').disabled=false;
                }
            },
            failure : function () {
                alert("<digi:trn>Error getting activity data</digi:trn>");
            }
        
        });
    }


    function showTeamMembers(id, description){
        var timestamp=new Date().getTime();
        YAHOO.util.Dom.replaceClass('loadedDetails', 'visibleTable','invisibleTable');
        YAHOO.util.Dom.replaceClass('loadingDetailsIcon', 'invisibleTable','visibleTable');
        YAHOO.util.Dom.replaceClass('addNew','visibleTable','invisibleTable');

        YAHOO.util.Connect.asyncRequest('GET','/aim/teamMembersJSON.do?teamId='+id+"&timestamp="+timestamp,{

            success : function (res) {
    		document.getElementById('ws_go').disabled=true;
                var members;
                try {
                    members = YAHOO.lang.JSON.parse(res.responseText);
                    updateTableMembers(members);
                    document.getElementById('teamTitle').innerHTML=document.getElementsByName('teamName')[0].value;
                    document.getElementById('addNew').innerHTML='<a title="Click here to Add Workspace Member" style="font-size:12px; padding-left:5px;" href="JavaScript:assignNewMember()"><digi:trn jsFriendly="true">Add Workspace Member</digi:trn></a>'
                }
                catch(e) {
                    alert("<digi:trn>Error getting members data</digi:trn>");
                }
                finally {
                   document.getElementById('ws_go').disabled=false;
                   YAHOO.util.Dom.replaceClass('loadingDetailsIcon','visibleTable', 'invisibleTable');
                   YAHOO.util.Dom.replaceClass('loadedDetails', 'invisibleTable','visibleTable');
                   YAHOO.util.Dom.replaceClass('addNew','invisibleTable', 'visibleTable');
                }
            },
            failure : function () {
                alert("<digi:trn>Error getting members data</digi:trn>");
            }
        
        });
    }
    function showActivities(id, description){
        YAHOO.util.Dom.replaceClass('loadedDetails', 'visibleTable','invisibleTable');
        YAHOO.util.Dom.replaceClass('loadingDetailsIcon', 'invisibleTable','visibleTable');
        YAHOO.util.Dom.replaceClass('addNew','visibleTable','invisibleTable');
        var timestamp=new Date().getTime();
        YAHOO.util.Connect.asyncRequest('GET','/aim/teamActivitiesJSON.do?id='+id+"&timestamp="+timestamp,{

            success : function (res) {
                document.getElementById('ws_go').disabled=true;
                var members;
                try {
                    members = YAHOO.lang.JSON.parse(res.responseText);
                    updateTableActivities(members);
                    document.getElementById('teamTitle').innerHTML=document.getElementsByName('teamName')[0].value;
                    document.getElementById('addNew').innerHTML='<a title="Click here to Assign Activity" style="font-size:12px; padding-left:5px;" href=\'JavaScript:addActivities('+id+')\'><digi:trn jsFriendly="true">Assign an activity</digi:trn></a>';

                }
                catch(e) {
                    alert("Error getting members data");
                }
                finally {
                    document.getElementById('ws_go').disabled=false;
                    YAHOO.util.Dom.replaceClass('loadingDetailsIcon','visibleTable', 'invisibleTable');
                    YAHOO.util.Dom.replaceClass('loadedDetails','invisibleTable', 'visibleTable');
                    YAHOO.util.Dom.replaceClass('addNew','invisibleTable', 'visibleTable');
                }
            },
            failure : function () {
                alert("Error getting members data");
            }
        
        });
    }

    var responseSuccessJson = function(o){
        //initDynamicTable1();
        var response = o.responseText; 
        var content = document.getElementById("popinContent");
        content.innerHTML = response;
        showContent();
    }

    var responseFailureJson = function(o){ 
        alert("Connection Failure!"); 
    }  
    var jsonCallback = 
        { 
        success:responseSuccessJson, 
        failure:responseFailureJson 
    };

    function assignNewMember(){
        <digi:context name="exportUrl" property="context/module/moduleinstance/showAddTeamMemberJSON.do"/>;
        var fromPage=myPaginator.getCurrentPage();
        var recordID = YAHOO.example.XHR_JSON.myDataTable.getSelectedRows()[0];
        var selectedRow= YAHOO.example.XHR_JSON.myDataTable.getTrIndex(recordID);
        document.aimWorkspaceForm.action="${exportUrl}?fromPage="+fromPage+"&teamId="+document.getElementsByName('teamId')[0].value+"&selectedRow="+selectedRow;
        document.aimWorkspaceForm.target="_self";
        document.aimWorkspaceForm.submit();	
    }
    function saveAddedMember(){
        if(validateAddedMember()){
            checkAndClose=true;
            lastFunction="showDetails";
            var msg='<digi:trn>Assign Member</digi:trn>';
                <digi:context name="commentUrl" property="context/module/moduleinstance/addTeamMemberJSON.do"/>;
            var url = "<%=commentUrl %>";
            url += "?fromPage=1&teamId="+document.getElementsByName('teamId')[0].value+"&email="+document.getElementsByName('email')[0].value+"&role="+document.getElementsByName('role')[0].value;
            YAHOO.util.Connect.asyncRequest("POST",url, callback, '');
        }	
    }
    function validateAddedMember(){
        if(document.getElementsByName('role')[0].selectedIndex==0){
            alert("<digi:trn>Role not entered</digi:trn>");
            return false;
        }
        return true;
    }
    function addActivities(id){
        var msg='<digi:trn>Assign Activities</digi:trn>';
        myPanel.cfg.setProperty("width","500px");
        myPanel.cfg.setProperty("height","400px"); 
        showPanelLoading(msg);

            <digi:context name="commentUrl" property="context/module/moduleinstance/assignActivityJSON.do"/>  
            var url = "<%=commentUrl %>";
        url += "~id="+id;
	

        YAHOO.util.Connect.asyncRequest("POST",url, callback, '');

    }

    function showDetails(){
        id = document.getElementsByName('teamId')[0].value;
        desc = document.getElementsByName('teamName')[0].value;
        if(id===""||desc===""){
            alert("<digi:trn>Select a Team First</digi:trn>");
            return;
        }
	
        value = document.getElementById('showdataWs').options[document.getElementById('showdataWs').selectedIndex].value;
        if(value==0){
            showTeamMembers(document.getElementsByName('teamId')[0].value, document.getElementById('teamTitle').value);			
        }
        else{
            showActivities(document.getElementsByName('teamId')[0].value, document.getElementById('teamTitle').value);		
        }
    }
    function showTeamDetails(id, description){
    	value = document.getElementById('showdataWs').options[document.getElementById('showdataWs').selectedIndex].value;
    	if(value==0){
        	if (description==null){
            	description='<digi:trn jsFriendly="true">Members</digi:trn>';
        	}
    	}else{
    		 if (description==null){
             	description='<digi:trn jsFriendly="true">Activities</digi:trn>';
             }
        }
    	document.getElementsByName('teamId')[0].value=id;
        document.getElementsByName('teamName')[0].value=description;
    	
        if(viewTeamDetails!='false'){
            if(value==0){
                showTeamMembers(id, description);			
            }
            else{
                showActivities(id, description);		
            }
        }else{
            viewTeamDetails='true';
        }
	
    }

</script>
<script language=javascript>
    function showTeamMemberProfile(email){
        var param = "~edit=true~email="+email;
        previewWorkspaceframe('/aim/default/userProfile.do',param);
	
    }
    function confirmDelete() {
        var flag = confirm("<digi:trn>Are you sure you want to remove the selected activity</digi:trn>");
        if(flag == false)
            return false;
        else 
            return true;
    }
</script>
<script language="javascript">
    function validate(){
        var result=false
        if(document.getElementsByName("selectedActivities")!=null){
            var sectors = document.getElementsByName("selectedActivities").length;
            for(var i=0; i< sectors; i++){
                if(document.getElementsByName("selectedActivities")[i].checked){
                    result=true;
                    break;
                }
            }
        } 
        if(!result){
            alert('<digi:trn>Please, Select an Activity First</digi:trn>')
        }
        return result;
    }

    function checkall() {
        var selectbox = document.aimAssignActivityForm.checkAll;
        if (document.aimAssignActivityForm.selectedActivities.type=="checkbox"){
            document.aimAssignActivityForm.selectedActivities.checked=selectbox.checked;
        }else{
            var items = document.aimAssignActivityForm.selectedActivities;
            for(i=0;i<items.length;i++){
                document.aimAssignActivityForm.selectedActivities[i].checked = selectbox.checked;
            }
        }
    }
    function assignActivityList(){
        ret="";
        if(document.getElementsByName("selectedActivities")!=null){
            var sectors = document.getElementsByName("selectedActivities").length;
            for(var i=0; i< sectors; i++){
                if(document.getElementsByName("selectedActivities")[i].checked){
                    ret+=document.getElementsByName("selectedActivities")[i].name+"="+document.getElementsByName("selectedActivities")[i].value+"&";
                }
            }
        } 
        if(validate()){
            checkAndClose=true;
            lastFunction="showDetails";
                <digi:context name="commentUrl" property="context/module/moduleinstance/assignActivityJSON.do"/>;  
            var url = "<%=commentUrl %>";
            url+="?"+ret+"&teamId="+document.getElementsByName('teamId')[0].value;
            var bodymsg='<div style="text-align: center">' + 
            '<img src="/TEMPLATE/ampTemplate/js_2/yui/assets/skins/sam/loading.gif" border="0" height="17px"/>&nbsp;&nbsp;' + 
            '<digi:trn>Saving, please wait ...</digi:trn><br/><br/></div>'
            myPanel.setBody(bodymsg);
            YAHOO.util.Connect.asyncRequest("POST",url, callback, '');
        }
        else{
            alert('<digi:trn>Validation Error</digi:trn>')
        }
	
    }
</script>
<script language="javascript">
    function exportXSL(){
            <digi:context name="exportUrl" property="context/module/moduleinstance/exportWorkspaceManager2XSL.do"/>;
        document.aimWorkspaceForm.action="${exportUrl}";
        document.aimWorkspaceForm.target="_blank";
        document.aimWorkspaceForm.submit();
    }

</script> 

<DIV id="TipLayer"	style="absolute;z-index:1000;"></DIV>


<digi:context name="digiContext" property="context" />


<table bgColor=#ffffff cellPadding=0 cellSpacing=0 width=1000 align=center>

    <tr>
        <td align=left vAlign=top width=850>
            <table cellPadding=5 cellSpacing=0 width="100%" border=0>
                 
				<!--	
				<tr>
			
                    <td height=33><span class=crumb>
                            <c:set var="translation">
                                <digi:trn>Click here to goto Admin Home</digi:trn>
                            </c:set>
                            <digi:link href="/admin.do" styleClass="comment" title="${translation}" >
                                <digi:trn>Admin Home</digi:trn>
                            </digi:link>&nbsp;&gt;&nbsp;
                            <digi:trn>Workspace Manager</digi:trn>
                            <div class="adminicon"><img src="/TEMPLATE/ampTemplate/img_2/adminicons/workspacemanager.jpg"/></div>
                    </td>
                </tr> 
				-->
                <!--<tr>
					<td height="16" vAlign="center" width="571">
                    	<span class=subtitle-blue><digi:trn>Workspace Manager</digi:trn></span>
					</td>
				</tr>-->
                <tr>
                    <td height="16" vAlign="center" width="571">
                <digi:errors />
        </td>
    </tr>
    <tr><td align="left">              

            <table border="0" align="center" bgcolor="#f2f2f2" width=100%>
                <tr>


                    <td noWrap align=center valign="middle">
                        <a target="_blank" onclick="exportXSL(); return false;">
                            <digi:img hspace="2" vspace="2" src="/TEMPLATE/ampTemplate/imagesSource/common/ico_exc.gif" border="0" alt="Export to Excel" />
                        </a>

                <digi:link styleId="printWin" href="#" onclick="window.print(); return false;">
                    <digi:img hspace="2" vspace="2" src="/TEMPLATE/ampTemplate/imagesSource/common/ico_print.gif" border="0" alt="Printer Friendly"/>
                </digi:link>
        </td>
    </tr>
</table>

</td></tr>
<tr>
    <td noWrap width="100%" vAlign="top">
        <table width="100%" cellspacing="1" cellpadding=""="1" border="0">
               <tr><td noWrap width="50%" vAlign="top">
                    <table bgColor="#cccccc" cellPadding="1" cellSpacing="1" width="100%" valign="top">
                        <tr bgColor="#ffffff">
                            <td vAlign="top" width="100%">
                                <table width="100%" cellspacing="1" cellpadding="1" valign="top" align="left" style="font-size:12px;">
                                    <tr><td bgColor=#c7d4db class=box-title height="25" align="center">
                                            <!-- Table title -->
                                    <digi:trn><b>Teams</b></digi:trn>
                                    <!-- end table title -->
                            </td>
                        </tr>
                        <tr><td>&nbsp;</td></tr>

                        <digi:form action="/workspaceManager.do?page=1" method="post">
                            <tr><td class="box-title" align="left">
                                    <!-- Table title -->
                                    <table width="100%" class="filter" style="font-size:12px;">
                                        <tr>

                                            <td>
                                        <digi:trn>keyword</digi:trn>:&nbsp;
                                        <html:text property="keyword" style="font-family:verdana;font-size:11px;"/>
                                </td>

                                <td align="center">
                            <digi:trn>Type</digi:trn>:&nbsp;
                            <html:select property="workspaceType" styleClass="inp-text">
                                <html:option value="all"><digi:trn>All</digi:trn></html:option>
                                <html:option value="team"><digi:trn>Team</digi:trn></html:option>
                                <html:option value="management"><digi:trn>Management</digi:trn></html:option>
                                <html:option value="computed"><digi:trn>Computed</digi:trn></html:option>
                            </html:select>
                            </td>
                            <td align="left">
                            <c:set var="translation">
                                <digi:trn>Show</digi:trn>
                            </c:set>
                            <input type="button" value="${translation}"  class="buttonx" style="font-family:verdana;font-size:11px;" onclick="return resetPage()"/>
                            </td>
                            </tr>
                    </table>
                    <!-- end table title -->
                </td></tr>
        </digi:form>
        <tr><td style="padding-left:5px;">
        <c:set var="translation">
            <digi:trn>Click here to Add Teams</digi:trn>
        </c:set>
        <digi:link href="/createWorkspace.do?dest=admin" title="${translation}" >
            <digi:trn>Add Team</digi:trn>
        </digi:link>
</td></tr>
<tr><td>
        <table width="100%" cellspacing="1" cellpadding="0" valign="top" align="left" style="font-size:12px;">
            <logic:empty name="aimWorkspaceForm" property="workspaces">
                <tr bgcolor="#ffffff">
                    <td colspan="5" align="center"><b>
                            <digi:trn>No teams present</digi:trn>
                        </b></td>
                </tr>
            </logic:empty>
            <logic:notEmpty name="aimWorkspaceForm" property="workspaces">
                <tr>
                    <td width="70%" style="padding-top:15px;" align=center>
                        <div class='yui-skin-sam'>
                            <div id="dynamicdata" class="report"></div>
                            <div id="dt-pag-nav" style="text-align: left"></div>
                            <div id="errors"></div>
                            <div id="tooltipsCtx"></div>
                        </div>
                    </td>
                </tr>
            </logic:notEmpty>
            <!-- end page logic -->
        </table>
    </td>
</tr>
</table>

</td>
</tr>
</table>
</td>

<!--details-->
<td  width="50%" vAlign="top">
    <table bgColor="#cccccc" cellPadding="1" cellSpacing="1" width="100%" valign="top">
        <tr bgColor="#ffffff">
            <td vAlign="top" width="100%">
                <table width="100%" cellspacing="1" cellpadding="1" valign="top" align="left" style="font-size:12px;">
                    <tr><td bgColor=#c7d4db class=box-title height="25" align="center" id="teamTitle" style="font-weight:bold;">
                    <digi:trn><b>Team Name</b></digi:trn>
            </td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr><td class="box-title" align="left">
                <table width="100%"  class="filter" style="font-size:12px;">
                    <tr>
                        <td>&nbsp;</td>
                        <td align="right">
                    <digi:trn>Select</digi:trn>:&nbsp;
                    <select id="showdataWs" class="inp-text"> 
                        <option value="0"><digi:trn>Members</digi:trn></option> 
                        <option value="1"><digi:trn>Activities</digi:trn></option> 
                    </select> &nbsp;&nbsp;&nbsp;
											            <input type="button" id="ws_go" class="buttonx" value='<digi:trn>Show</digi:trn>' onclick="showDetails()">
                    </td>
                    </tr>
                </table>
            </td></tr>

        <tr>
            <td>
                <table width="100%" cellspacing="0" cellpadding="0" valign="top" align="left" border="0">
                    <tr>
                        <td id="addNew" align="left" style="padding-bottom:13px;">&nbsp;
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr><td>
                <div>
                    <table width="100%" class="visibleTable" id="loadedDetails" cellspacing="0" cellpadding="0" valign="top" align="left" border="0" >
                        <tr>
                            <td align=center>
                                <input type="hidden" name="teamId" value=""/>
                                <input type="hidden" name="teamName" value=""/>
                                <table  cellspacing="1" cellpadding="2" align="left" width="100%" style="font-size:12px;">
                                    <tr><td>
				<div class="reportHead" style="width: 100%; height: 22px; max-height: 22px; ">
                                                <table width="100%" class="inside">																				
                                                    <tr class="headTableTr">
                                                        <td align="center" width="300" class="inside" bgcolor=#F2F2f2><digi:trn><b>Name</b></digi:trn></td>
                                                    <td align="center" width="100" class="inside" bgcolor=#F2F2f2><digi:trn><b>Actions</b></digi:trn></td>
                                                    </tr>
                                                </table>
                                            </div>
						<div id="demo" class="report box-border-nopadding scrollable" >
                                                <table class="inside" width="100%" id="dataTable" cellspacing="0" cellpadding="4" valign="top"  align="left">
                                                    <tbody>
                                                        <tr><td colspan="2"><em><digi:trn>Select Team to Get Data</digi:trn></em></td></tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <table cellspacing="1" cellpadding="2" align="left" width="100%">
                                                <tbody>
                                                    <tr><td colspan="2" id="footerMessage">&nbsp;
                                                        </td></tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                     <img src="/TEMPLATE/ampTemplate/img_2/ajax-loader.gif" alt="loading" id="loadingDetailsIcon" class="invisibleTable"/>
                </div>
            </td>
        </tr>
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
</td>
</tr>
</table>
