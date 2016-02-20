package de.ts.ticketsystem.client.main;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.ticketsystem.client.jira.objects.Request;
import de.ts.ticketsystem.client.jira.servicedeskapi.JiraServicedeskDAO;

public class Main {

	private static URI jiraServerUri = UriBuilder.fromUri(Properties.getString("Main.JIRA_SERVICE_DESK_URL")).build(); //$NON-NLS-1$

	public static void main(String[] args) {
		test1();


	}
	
	

	private static void test1() {
		
		HttpAuthenticationFeature basicAuthenticationFeature = HttpAuthenticationFeature.basic(Properties.getString("Main.JIRA_ACCOUNTNAME"), Properties.getString("Main.JIRA_PASSWORD")); //$NON-NLS-1$ //$NON-NLS-2$
		
		Client client = createHttpClient(basicAuthenticationFeature);
		WebTarget target = client.target(jiraServerUri);
		
		JiraServicedeskDAO jiraServicedeskDAO = new JiraServicedeskDAO(target);

		Request jiraRequest = jiraServicedeskDAO .getRequestById("HEIZ-4"); //$NON-NLS-1$
		
		System.out.println(jiraRequest);
		client.close();
	}

	private static Client createHttpClient(HttpAuthenticationFeature authenticationFeature)
	{
		Client client = ClientBuilder.newClient();
		client.register(authenticationFeature);
		
		return client;
	}
}
