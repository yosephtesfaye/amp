package org.digijava.kernel.ampapi.endpoints.activity;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.dgfoundation.amp.algo.AmpCollections;
import org.digijava.kernel.ampapi.endpoints.activity.utils.AmpMediaType;
import org.digijava.kernel.ampapi.endpoints.activity.utils.ApiCompat;
import org.digijava.kernel.ampapi.endpoints.common.EndpointUtils;
import org.digijava.kernel.ampapi.endpoints.errors.ErrorReportingEndpoint;
import org.digijava.kernel.ampapi.endpoints.security.AuthRule;
import org.digijava.kernel.ampapi.endpoints.util.ApiMethod;
import org.digijava.kernel.ampapi.endpoints.util.JsonBean;
import org.digijava.kernel.request.TLSUtils;
import org.digijava.module.aim.dbentity.AmpActivityFields;
import org.digijava.module.aim.helper.Constants;
import org.digijava.module.aim.helper.TeamMember;


/**
 * AMP Activity Endpoints for Activity Import / Export
 *
 * @implicitParam X-Auth-Token|string|header
 * @author acartaleanu
 */
@Path("activity")
public class InterchangeEndpoints implements ErrorReportingEndpoint {

    @Context
    private UriInfo uri;

	/**
	 * Returns a list of JSON objects, each describing a possible value that might be specified in an activity field
	 * If Accept: application/vnd.possible-values-v2+json is used then possible values will be represented in a tree
	 * structure.
	 *
	 * <h3>Sample response (flat):</h3><pre>
	 * [
	 *   {
	 *     "id": 539,
	 *     "value": "Cote d'Ivoire",
	 *     "extra_info": {
	 *       "parent_location_id": null,
	 *       "parent_location_name": null,
	 *       "implementation_level_id": 76,
	 *       "implementation_location_name": "Country"
	 *     }
	 *   },
	 *   {
	 *     "id": 796,
	 *     "value": "BAGOUE",
	 *     "extra_info": {
	 *       "parent_location_id": 539,
	 *       "parent_location_name": "Cote d'Ivoire",
	 *       "implementation_level_id": 77,
	 *       "implementation_location_name": "Region"
	 *     }
	 *   }
	 * ]
	 * </pre>
	 *
	 * <h3>Sample response (tree):</h3><pre>
	 * [
	 *   {
	 *     "id": 539,
	 *     "value": "Cote d'Ivoire",
	 *     "children": [
	 *       {
	 *         "id": 796,
	 *         "value": "BAGOUE",
	 *         "extra_info": {
	 *           "implementation_level_id": 77,
	 *           "implementation_location_name": "Region"
	 *         }
	 *       }
	 *     ],
	 *     "extra_info": {
	 *       "implementation_level_id": 76,
	 *       "implementation_location_name": "Country"
	 *     }
	 *   }
	 * ]
	 * </pre>
	 *
	 * @implicitParam Accept|string|header
	 * @param fieldName, the Activity field title, underscorified (see <InterchangeUtils.underscorify for details>
	 * @return list of JsonBean objects, each representing a possible value
	 */
	@GET
	@Path("fields/{fieldName}")
	@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8", AmpMediaType.POSSIBLE_VALUES_V2_JSON})
	@ApiMethod(authTypes = AuthRule.IN_WORKSPACE, id = "getValues", ui = false)
	public Response getPossibleValuesFlat(@PathParam("fieldName") String fieldName) {
		List<PossibleValue> possibleValues = possibleValuesFor(fieldName);
		MediaType responseType = MediaType.APPLICATION_JSON_TYPE;
		if (AmpMediaType.POSSIBLE_VALUES_V2_JSON.equals(ApiCompat.getRequestedMediaType())) {
			responseType = AmpMediaType.POSSIBLE_VALUES_V2_JSON_TYPE;
		} else {
			possibleValues = PossibleValue.flattenPossibleValues(possibleValues);
		}
		return Response.ok(possibleValues, responseType).build();
	}

	/**
	 * Returns a list of possible values for each requested field.
	 * If Accept: application/vnd.possible-values-v2+json is used then possible values will be represented in a tree
	 * structure.
	 * <h3>Sample request:</h3><pre>
	 * ["fundings~donor_organization_id", "approval_status"]
	 * </pre>
	 * <h3>Sample response (flat):</h3><pre>
	 * {
	 *   "fundings~donor_organization_id": [
	 *     {
	 *       "id": 1,
	 *       "value": "Donor 1"
	 *     },
	 *     {
	 *       "id": 2,
	 *       "value": "Donor 2"
	 *     }
	 *   ],
	 *   "approval_status": [
	 *     {
	 *       "id": "1",
	 *       "value": "approved"
	 *     },
	 *     {
	 *       "id": "2",
	 *       "value": "edited"
	 *     }
	 *   ]
	 * }
	 * </pre>
	 * <h3>Sample response (tree):</h3><pre>
	 * {
	 *   "fundings~donor_organization_id": [
	 *     {
	 *       "id": 1,
	 *       "value": "Donor 1"
	 *     },
	 *     {
	 *       "id": 2,
	 *       "value": "Donor 2"
	 *     }
	 *   ],
	 *   "locations~locations": [
	 *     {
	 *       "id": "1",
	 *       "value": "Cote d'Ivoire",
	 *       "children": [
	 *         {
	 *       	"id": 796,
	 *       	"value": "BAGOUE"
	 *         }
	 *       ]
	 *     }
	 *   ]
	 * }
	 * </pre>
	 * @implicitParam Accept|string|header
	 * @param fields list of activity fields
	 * @return list of possible values grouped by field
	 */
	@POST
	@Path("field/values")
	@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8", AmpMediaType.POSSIBLE_VALUES_V2_JSON})
	@ApiMethod(authTypes = AuthRule.AUTHENTICATED, id = "getMultiValues", ui = false)
	public Response getValues(List<String> fields) {
		Map<String, List<PossibleValue>> response;
		if (fields == null) {
			response = Collections.emptyMap();
		} else {
			response = fields.stream()
					.filter(Objects::nonNull)
					.distinct()
					.collect(toMap(identity(), this::possibleValuesFor));
		}
		MediaType responseType = MediaType.APPLICATION_JSON_TYPE;
		if (AmpMediaType.POSSIBLE_VALUES_V2_JSON.equals(ApiCompat.getRequestedMediaType())) {
			responseType = AmpMediaType.POSSIBLE_VALUES_V2_JSON_TYPE;
		} else {
			response = AmpCollections.remap(response, PossibleValue::flattenPossibleValues);
		}
		return Response.ok(response, responseType).build();
	}

	private List<PossibleValue> possibleValuesFor(String fieldName) {
		return PossibleValuesEnumerator.INSTANCE.getPossibleValuesForField(fieldName, AmpActivityFields.class, null);
	}
	
	/**
	 * Provides full set of available fields and their settings/rules in a hierarchical structure.
	 * @return JSON with fields information
	 * @see <a href="https://wiki.dgfoundation.org/display/AMPDOC/Fields+enumeration">Fields Enumeration Wiki<a/>
	 */
	@GET
	@Path("fields")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiMethod(authTypes = AuthRule.IN_WORKSPACE, id = "getFields", ui = false)
	public List<APIField> getAvailableFields() {
		return AmpFieldsEnumerator.PUBLIC_ENUMERATOR.getAllAvailableFields();
	}
	
	// TODO remove it as part of AMP-25568
	@GET
	@Path("fields-no-workspace")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiMethod(authTypes = AuthRule.AUTHENTICATED, id = "getDefaultFields", ui = false)
	public List<APIField> getAvailableFieldsBasedOnDefaultFM() {
		return getAvailableFields();
	}
	
	/**
	 * Returns a JSON object with the list of all projects on the system, including its view and edit status for the current logged user.
	 * If the user can view the project, the 'view' property of the project is set to true. False otherwise.
	 * If the user can edit the project, the 'edit' property of the project on the JSON is set to true. False otherwise.
	 * Pagination can be used if the parameters are sent on the request. If not parameters are sent, the full list
	 * of projects is returned.
	 * 
	 * @param pid  current pagination request reference (random id). It acts as a key for a LRU caching mechanism that holds the 
	 * full list of projects for the current user. If it is not provided no caching is used
	 * @param offset, Integer used for pagination. It represents which is the first project to return. It helps to skip the unnecessary 
	 * records.
	 * @param size, Integer used for pagination. It tells how many projects to return
	 * @return list of JsonBean with all the projects on the system
	 */
	@GET
	@Path("/projects")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiMethod(authTypes = AuthRule.IN_WORKSPACE, id = "getProjectList", ui = false)
	public Collection<JsonBean> getProjects(@QueryParam ("pid") String pid,@QueryParam("offset") Integer offset, @QueryParam("count") Integer count) {
		TeamMember tm = (TeamMember) TLSUtils.getRequest().getSession().getAttribute(Constants.CURRENT_MEMBER);
		Collection<JsonBean> activityCollection = ProjectList.getActivityList(pid, tm);
		int start = 0;
		int end = activityCollection.size() - 1;
		if (offset != null && count != null && offset < activityCollection.size()) {
			start = offset.intValue();
			if (activityCollection.size() > (offset + count)) {
				end = offset + count;
			}
		}
		return new ArrayList(activityCollection).subList(start, end);
	}

	/**
	 * Provides full project information 
	 * @param projectId project id
	 * @return project with full set of configured fields and their values 
	 */
	@GET
	@Path("/projects/{projectId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiMethod(authTypes = AuthRule.VIEW_ACTIVITY, id = "getProject", ui = false)
	public JsonBean getProject(@PathParam("projectId") Long projectId) {
		return InterchangeUtils.getActivity(projectId);
	}
	
	/**
	 * Provides full project information
	 * @param projectId project id
	 * @param filter jsonBean with a list of fields that will be displayed
	 * @return project with full set of configured fields and their values
	 */
	@POST
	@Path("/projects/{projectId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiMethod(authTypes = AuthRule.VIEW_ACTIVITY, id = "getProjectsFilter", ui = false)
	public JsonBean getProject(@PathParam("projectId") Long projectId, JsonBean filter) {
		return InterchangeUtils.getActivity(projectId, filter);
	}

	/**
	 * Retrieve project by AMP Id.
	 *
	 * <h3>Sample Output:</h3><pre>
	 * {
	 *   "project_impact": null,
	 *   "project_management": null,
	 *   "internal_id": 10827,
	 *   "amp_id": "112007154460",
	 *   "project_title": "Activity title",
	 *   "description": "Activity description",
	 *   "lessons_learned": null,
	 *   ...
	 * }
	 * </pre>
	 *
	 * @param ampId AMP Id
	 * @return Project with full set of configured fields and their values.
	 */
	@GET
	@Path("/project")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiMethod(authTypes = AuthRule.AUTHENTICATED, id = "getProjectByAmpId", ui = false)
	public JsonBean getProjectByAmpId(@QueryParam("amp-id") String ampId) {
		return InterchangeUtils.getActivityByAmpId(ampId);
	}

	/**
	 * Imports an activity
	 * 
	 * @param newJson activity configuration
	 * @return latest project overview or an error if invalid configuration is received
	 */
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiMethod(authTypes = AuthRule.AUTHENTICATED, id = "addProject", ui = false)
	public JsonBean addProject(JsonBean newJson) {
        return InterchangeUtils.importActivity(newJson, false, uri.getBaseUri() + "activity");
	}
	
	/**
	 * Updates an activity
	 * 
	 * @param projectId the id of the activity which should be updated
	 * @param newJson activity configuration
	 * @return latest project overview or an error if invalid configuration is received
	 */
	@POST
	@Path("/{projectId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@ApiMethod(authTypes = AuthRule.AUTHENTICATED, id = "updateProject", ui = false)
	public JsonBean updateProject(@PathParam("projectId") Long projectId, JsonBean newJson) {
		/*
		 * Originally it was defined as PUT to avoid these type of issues checked here.
		 * But it is more common to use it as POST, so let's then validate
		 */ 
		Object internalId = newJson.get(ActivityEPConstants.AMP_ACTIVITY_ID_FIELD_NAME);
		if (!projectId.toString().equals(String.valueOf(internalId))) {
			// invalidating
			String details = "url project_id = " + projectId + ", json " + ActivityEPConstants.AMP_ACTIVITY_ID_FIELD_NAME +
					" = " + internalId;
			EndpointUtils.addGeneralError(newJson, ActivityErrors.UPDATE_ID_MISMATCH.withDetails(details));
		}

		return InterchangeUtils.importActivity(newJson, true, uri.getBaseUri() + "activity");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class getErrorsClass() {
		return ActivityErrors.class;
	}
}
