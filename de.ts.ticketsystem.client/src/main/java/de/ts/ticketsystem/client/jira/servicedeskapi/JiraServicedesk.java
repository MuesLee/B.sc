package de.ts.ticketsystem.client.jira.servicedeskapi;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.ticketsystem.client.jira.AbstractJiraInstance;
import de.ts.ticketsystem.client.jira.GenericJiraRestDAO;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequest;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Request;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.ServiceDesk;

public class JiraServicedesk extends AbstractJiraInstance{

	private GenericJiraRestDAO<Request> requestDAO;
	private GenericJiraRestDAO<ServiceDesk> serviceDeskDAO;

	public JiraServicedesk(URI jiraUri, HttpAuthenticationFeature authenticationFeature) {
		super(jiraUri, authenticationFeature);
		this.requestDAO = new GenericJiraRestDAO<>(Request.class);
		this.serviceDeskDAO = new GenericJiraRestDAO<>(ServiceDesk.class);
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

		Request jiraRequest = requestDAO.getOne(builder);
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
		
		List<Request> jiraRequests = requestDAO.getMany(builder);
		return jiraRequests;
	}

	public Request postNewRequest(NewRestRequest newRestRequest) {
		
		String jsonString = gson.toJson(newRestRequest);

		// POST /rest/servicedeskapi/request
		Builder builder = target.path("rest").path("servicedeskapi").path("request")
				.request(MediaType.APPLICATION_JSON);

		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");

		Entity<String> entity = Entity.entity(jsonString, MediaType.APPLICATION_JSON);

		Request returnedjiraRequest = requestDAO.postOne(builder, entity);
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
		
		ServiceDesk serviceDesk = serviceDeskDAO.getOne(builder);
		return serviceDesk;
	}

}
