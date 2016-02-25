package de.ts.ticketsystem.client.jira.platformapi;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.WebTarget;

import de.ts.ticketsystem.client.jira.platformapi.objects.Issue;

public class JiraPlatformDAO extends JiraDAO {

	
	private GenericJiraRestDAO<Issue> issueDAO;
	
	public JiraPlatformDAO(WebTarget target) {
		super(target);
		
		issueDAO = new GenericJiraRestDAO<Issue>(Issue.class);
		
	}

	
	public Issue getIssue(String issueIdOrKey)
	{
		//GET /rest/api/2/issue
		
		Builder builder = target.path("rest").path("api").path("2").path("issue").path(issueIdOrKey).request(MediaType.APPLICATION_JSON);
		
		Issue returnedJiraIssue = issueDAO.getOne(builder);
		
		return returnedJiraIssue ;
	}

}
