package de.ts.ticketsystem.client.jira.platformapi;

import java.net.URI;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.ticketsystem.client.jira.AbstractJiraInstance;
import de.ts.ticketsystem.client.jira.GenericJiraRestDAO;
import de.ts.ticketsystem.client.jira.platformapi.objects.Issue;

public class JiraPlatform extends AbstractJiraInstance {

	private final GenericJiraRestDAO<Issue> issueDAO;

	public JiraPlatform(final URI jiraUri, final HttpAuthenticationFeature authenticationFeature) {
		super(jiraUri, authenticationFeature);

		issueDAO = new GenericJiraRestDAO<Issue>(Issue.class);

	}

	public Issue getIssue(final String issueIdOrKey) throws ClientErrorException {
		// GET /rest/api/2/issue/key

		final Builder builder = getTarget().path("rest").path("api").path("2").path("issue").path(issueIdOrKey)
				.request(MediaType.APPLICATION_JSON);

		final Issue returnedJiraIssue = issueDAO.getOne(builder);

		return returnedJiraIssue;
	}

	public Issue postIssue(final Issue issue) throws ClientErrorException {
		
		//POST /rest/api/2/issue
		final Builder builder = getTarget().path("rest").path("api").path("2").path("issue").request(MediaType.APPLICATION_JSON);

		final Issue returnedJiraIssue = issueDAO.postOne(builder, issue);

		return returnedJiraIssue;
	}

}
