package de.ts.ticketsystem.client.jira.platformapi;

import java.net.URI;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.ticketsystem.client.jira.ClientUtils;
import de.ts.ticketsystem.client.jira.platformapi.objects.Assignee;
import de.ts.ticketsystem.client.jira.platformapi.objects.Issue;

/**
 *	Represents an instance of a Jira Platform. Encapsulates the API calls. 
 */
public class JiraPlatform extends AbstractJiraInstance {

	private final GenericJiraRestDAO<Issue> issueDAO;

	public JiraPlatform(final URI jiraUri, final HttpAuthenticationFeature authenticationFeature) {
		super(jiraUri, authenticationFeature);

		issueDAO = new GenericJiraRestDAO<Issue>(Issue.class);

	}
	
	/**
	 * Returns the Issue for the given id or throws an Exception
	 * 
	 * @param issueIdOrKey
	 *            Id or Key of the searched Request
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 403 or 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200, 400, 403 or 404
	 * @return found Issue
	 */
	public Issue getIssue(final String issueIdOrKey) throws ClientErrorException, WebApplicationException {
		
		// GET /rest/api/2/issue/key
		final Builder builder = getTarget().path("rest").path("api").path("2").path("issue").path(issueIdOrKey)
				.request(MediaType.APPLICATION_JSON);

		final Issue returnedJiraIssue = issueDAO.getOne(builder);

		return returnedJiraIssue;
	}
	
	
	/**
	 * POST a new {@link Issue}. Returns the newly created Issue or throws an Exception
	 * 
	 * @param issue issue to create
	 * @return newly created Issue
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 401 or 403
	 * @throws WebApplicationException
	 *             if the Statuscode is not 201, 400, 401 or 403
	 */
	public Issue createIssue(final Issue issue) throws ClientErrorException, WebApplicationException {
		
		//POST /rest/api/2/issue
		final Builder builder = getTarget().path("rest").path("api").path("2").path("issue").request(MediaType.APPLICATION_JSON);

		final Issue returnedJiraIssue = issueDAO.postOne(builder, issue);

		return returnedJiraIssue;
	}
	
	/**
	 * PUT an assignee to an issue 
	 * 
	 * @param issueIdOrKey Id of the Issue to assign
	 * @param assigneeName username of the assignee
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 401 or 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 204, 400, 401 or 404
	 */
	public void assignIssue(final String issueIdOrKey, final Assignee assignee)  throws ClientErrorException, WebApplicationException
	{

		//PUT /rest/api/2/issue/{issueIdOrKey}/assignee
		final Builder builder = getTarget().path("rest").path("api").path("2").path("issue").path(issueIdOrKey).request(MediaType.APPLICATION_JSON);
		final String jsonString = ClientUtils.getGson().toJson(assignee);
		final Entity<String> entity = Entity.entity(jsonString, MediaType.APPLICATION_JSON);

		issueDAO.putOne(builder, entity);
	}

}
