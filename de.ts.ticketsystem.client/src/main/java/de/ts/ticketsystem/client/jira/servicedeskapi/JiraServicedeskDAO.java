package de.ts.ticketsystem.client.jira.servicedeskapi;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import de.ts.ticketsystem.client.jira.objects.Request;

public class JiraServicedeskDAO {

	private WebTarget target;

	/**
	 * Constructs a new instance, which is connecting and communicating with the given WebTarget.
	 * 
	 * This class expects the authentication to be taken care of.
	 * 
	 * @param target Weblocation of the Jira Servicedesk
	 */
	public JiraServicedeskDAO(WebTarget target) {
		this.target = target;
	}




	/**
	 * Returns the Request for the given id
	 * 
	 * @param issueIdOrKey Id or Key of the searched Request
	 * @return
	 */
	public Request getRequestById(String issueIdOrKey) {
		//GET /rest/servicedeskapi/request/{issueIdOrKey}
		
	
		Builder request = target.path("rest").path("servicedeskapi").path("request").path(issueIdOrKey)
				.request(MediaType.APPLICATION_JSON);
		
		//Mandatory to use the Experimental Jira Service Desk API
		request.header("X-ExperimentalApi", "opt-in");
		Response response = request.get();
		String jsonString = response.readEntity(String.class);
		Gson gson = new Gson();
		Request jiraRequest = gson.fromJson(jsonString, Request.class);
		return jiraRequest;
	}

}
