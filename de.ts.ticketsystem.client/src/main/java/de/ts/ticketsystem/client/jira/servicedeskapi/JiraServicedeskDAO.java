package de.ts.ticketsystem.client.jira.servicedeskapi;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequest;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Request;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.ResultPage;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.ServiceDesk;

public class JiraServicedeskDAO {

	private WebTarget target;
	private Gson gson;

	/**
	 * Constructs a new instance, which is connecting and communicating with the
	 * given WebTarget.
	 * 
	 * This class expects the authentication to be taken care of.
	 * 
	 * @param target
	 *            Weblocation of the Jira Servicedesk
	 */
	public JiraServicedeskDAO(WebTarget target) {
		this.target = target;
		gson = new Gson();
	}

	/**
	 * Returns the Request for the given id
	 * 
	 * @param issueIdOrKey
	 *            Id or Key of the searched Request
	 * @return
	 */
	public Request getRequestById(String issueIdOrKey) {

		// GET /rest/servicedeskapi/request/{issueIdOrKey}
		Builder builder = target.path("rest").path("servicedeskapi").path("request").path(issueIdOrKey)
				.request(MediaType.APPLICATION_JSON);

		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");

		Response response = builder.get();
		String jsonString = response.readEntity(String.class);
		Request jiraRequest = gson.fromJson(jsonString, Request.class);
		return jiraRequest;
	}
	
	/**
	 * Returns a list of all requests created or participated by the authenticated user
	 * 
	 * @return
	 */
	public List<Request> getMyRequests() {
		
		// GET /rest/servicedeskapi/request/
		Builder builder = target.path("rest").path("servicedeskapi").path("request")
				.request(MediaType.APPLICATION_JSON);
		
		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");
		
		Response response = builder.get();
		String jsonString = response.readEntity(String.class);
		ResultPage fromJson = gson.fromJson(jsonString, ResultPage.class);
		List<Request> jiraRequests = fromJson.getValues();
		return jiraRequests;
	}

	public Request postNewRequest(NewRestRequest newRestRequest) {
		
		String jsonString = gson.toJson(newRestRequest);

		// POST /rest/servicedeskapi/request
		Builder builder = target.path("rest").path("servicedeskapi").path("request")
				.request(MediaType.APPLICATION_JSON);

		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");

		Response response = builder.post(Entity.entity(jsonString, MediaType.APPLICATION_JSON), Response.class);

		System.out.println(response.getStatus());
		jsonString = response.readEntity(String.class);

		Request returnedjiraRequest = gson.fromJson(jsonString, Request.class);
		return returnedjiraRequest;

	}
	
	/**
	 * Returns the ServiceDesk for the given id
	 * 
	 * @param serviceDeskId
	 *            Id of the searched service desk
	 * @return
	 */
	public ServiceDesk getServiceDeskById(String serviceDeskId) {

		// GET /rest/servicedeskapi/servicedesk/{serviceDeskId}
		Builder builder = target.path("rest").path("servicedeskapi").path("servicedesk").path(serviceDeskId)
				.request(MediaType.APPLICATION_JSON);

		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");

		Response response = builder.get();
		String jsonString = response.readEntity(String.class);
		ServiceDesk serviceDesk = gson.fromJson(jsonString, ServiceDesk.class);
		return serviceDesk;
	}

}
