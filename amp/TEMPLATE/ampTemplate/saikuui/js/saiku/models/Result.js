/*  
 *   Copyright 2012 OSBI Ltd
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
 
/**
 * Holds the resultset for a query, and notifies plugins when resultset updated
 */
var Result = Backbone.Model.extend({

    result: null,
    firstRun: false,
    
    initialize: function(args, options) {
        // Keep reference to query
        this.query = options.query;
    },
    
    parse: function(response) {
        // Show the UI if hidden
        this.query.workspace.unblock();
        this.query.workspace.processing.hide();
        this.result = response;
        if (!response.error) {
            this.query.model = _.extend({}, response.query);
        }
        this.firstRun = true;
		//Start Custom Code for Pagination
        this.query.set({'total_rows': response.height});
        //End Custom Code for Pagination

        this.query.workspace.trigger('query:result', {
            workspace: this.query.workspace,
            data: response
        });

    },

    hasRun: function() {
        return this.firstRun;
    },
    
    lastresult: function() {
            return this.result;
    },
    
    url: function() {
    	if(this.query.get('report_id')){
	    	//Start Custom Code for Pagination
	    	var result_type = "result";
	    	if(Settings.PAGINATION) {
	    		result_type = "paginatedresult";
	        	var start = this.query.get('page')*Settings.RESULTS_PER_PAGE;
	        	var end = start  + Settings.RESULTS_PER_PAGE;
	        	this.set({'start': start});
	        	this.set({'end': end});
	    	}
    		
    		return encodeURI("../../../rest/data/saikureport/" + this.query.get('report_id'));
    	}
        return "api/query/execute";
    }
});
