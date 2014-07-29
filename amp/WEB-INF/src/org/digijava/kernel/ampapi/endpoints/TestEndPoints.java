package org.digijava.kernel.ampapi.endpoints;
//TODO: Add documentation to this class;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.digijava.kernel.ampapi.endpoints.util.FiltersParams;
import org.digijava.kernel.ampapi.mondrian.queries.TestQueries;


/**
 * 
 * @author Diego Sample class to test end points are working properly Root
 *         resource (exposed at "test" path)
 */

@Path("test")
public class TestEndPoints {

	private ServletContext context;

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
		return "Hey, This is AMP API Test Root Path";
	}

	@GET
	@Path("/testjson")
	@Produces(MediaType.APPLICATION_JSON)
	public final testObj simplejson() {
		testObj jsonobj = new testObj(
				"AMP API Test End Point - Test JSON Format");
		return jsonobj;
	}
	
	@GET
	@Path ("/testquery")
	@Produces(MediaType.TEXT_PLAIN)
	public final String queryresult() {
		TestQueries query = new TestQueries();
		try {
			return query.getQuery(context.getRealPath("/WEB-INF/")).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Can't execute query .. :(";
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FiltersParams createTrackInJSON(FiltersParams fp) {

		return fp;
		
	}
	
	@Context
	public void setServletContext(ServletContext context) {
		System.out.println("servlet context set here");
		this.context = context;
	}

	/***
	 * 
	 * Auxiliary object that will be converted to JSON
	 * 
	 */

	private class testObj {
		private Integer Id;
		private String Message;

		public testObj(String message) {
			super();
			this.setMessage(message);
			this.Id = this.getId();
		}

		public String getMessage() {
			return Message;
		}

		public void setMessage(String message) {
			Message = message;
		}

		public Integer getId() {
			Random random = new Random();
			return random.nextInt((100000 - 0) + 1);
		}
	}

}