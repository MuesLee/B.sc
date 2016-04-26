package de.ts.ticketsystem.client.jira.servicedeskapi;

import java.net.URI;
import java.util.List;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.ticketsystem.client.jira.AbstractJiraInstance;
import de.ts.ticketsystem.client.jira.ClientUtils;
import de.ts.ticketsystem.client.jira.GenericJiraRestDAO;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequest;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Request;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Servicedesk;

/**
 *	Represents an instance of a Jira Servicedesk. Encapsulates the API calls. 
 */
public class JiraServicedesk extends AbstractJiraInstance {

	private final GenericJiraRestDAO<Request> requestDAO;
	private final GenericJiraRestDAO<Servicedesk> serviceDeskDAO;
	
	
	public JiraServicedesk(final URI jiraUri, final HttpAuthenticationFeature authenticationFeature) {
		super(jiraUri, authenticationFeature);
		this.requestDAO = new GenericJiraRestDAO<>(Request.class);
		this.serviceDeskDAO = new GenericJiraRestDAO<>(Servicedesk.class);
	}

	/**
	 * Returns the Request for the given id or throws an Exception
	 * 
	 * @param issueIdOrKey
	 *            Id or Key of the searched Request
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 403 or 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200, 400, 403 or 404
	 * @return found request
	 */
	public Request getRequestById(final String issueIdOrKey) throws ClientErrorException, WebApplicationException {

		// GET /rest/servicedeskapi/request/{issueIdOrKey}
		final Builder builder = getTarget().path("rest").path("servicedeskapi").path("request").path(issueIdOrKey)
				.request(MediaType.APPLICATION_JSON);

		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");

		final Request jiraRequest = requestDAO.getOne(builder);
		return jiraRequest;
	}

	/**
	 * Returns a list of all requests created or participated by the
	 * authenticated user
	 * 
	 * @return
	 */
	public List<Request> getMyRequests() throws ClientErrorException, WebApplicationException {

		// GET /rest/servicedeskapi/request/
		final Builder builder = getTarget().path("rest").path("servicedeskapi").path("request")
				.request(MediaType.APPLICATION_JSON);

		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");

		final List<Request> jiraRequests = requestDAO.getMany(builder);
		return jiraRequests;
	}

	/**
	 * POST a new Request. Please consider the different input and output type
	 * {@link NewRestRequest} and {@link Request}
	 * 
	 * @param newRestRequest
	 * @return newly created request
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 401 or 403
	 * @throws WebApplicationException
	 *             if the Statuscode is not 201, 400, 401 or 403
	 */
	public Request createRequest(final NewRestRequest newRestRequest)
			throws ClientErrorException, WebApplicationException {


		// POST /rest/servicedeskapi/request
		final Builder builder = getTarget().path("rest").path("servicedeskapi").path("request")
				.request(MediaType.APPLICATION_JSON);

		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");

		final String jsonString = ClientUtils.getGson().toJson(newRestRequest);
		final Entity<String> entity = Entity.entity(jsonString, MediaType.APPLICATION_JSON);

		final Request returnedjiraRequest = requestDAO.postOne(builder, entity);
		return returnedjiraRequest;

	}

	/**
	 * Returns the ServiceDesk for the given id or throws an Exception
	 * 
	 * @param serviceDeskId
	 *            Id of the searched service desk
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 403 or 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200, 400, 403 or 404
	 * @return found {@link Servicedesk}
	 */
	public Servicedesk getServiceDeskById(final String serviceDeskId)
			throws ClientErrorException, WebApplicationException {

		// GET /rest/servicedeskapi/servicedesk/{serviceDeskId}
		final Builder builder = getTarget().path("rest").path("servicedeskapi").path("servicedesk").path(serviceDeskId)
				.request(MediaType.APPLICATION_JSON);

		// Mandatory to use the Experimental Jira Service Desk API
		builder.header("X-ExperimentalApi", "opt-in");

		final Servicedesk serviceDesk = serviceDeskDAO.getOne(builder);
		return serviceDesk;
	}

}
