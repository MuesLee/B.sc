package de.ts.ticketsystem.client.main;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.ticketsystem.client.jira.servicedeskapi.JiraServicedeskDAO;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequest;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequestFieldValue;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Request;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.ServiceDesk;

public class Main {

	private static final int _REQUESTTYPE_ID_TECHNISCHERSERVICE = 1;
	private static final int _SERVICEDESK_ID_HEIZUNG = 1;
	private static URI jiraServerUri = UriBuilder.fromUri(Properties.getString("Main.JIRA_SERVICE_DESK_URL")).build(); //$NON-NLS-1$

	public static void main(String[] args) {
		
		HttpAuthenticationFeature basicAuthenticationFeature = HttpAuthenticationFeature
				.basic(Properties.getString("Main.JIRA_ACCOUNTNAME"), Properties.getString("Main.JIRA_PASSWORD")); //$NON-NLS-1$ //$NON-NLS-2$
		
		Client client = createHttpClient(basicAuthenticationFeature);
		WebTarget target = client.target(jiraServerUri);
		
		JiraServicedeskDAO jiraServicedeskDAO = new JiraServicedeskDAO(target);
		
		//GET Request
		Request jiraRequest = jiraServicedeskDAO.getRequestById("HEIZ-4"); //$NON-NLS-1$
		System.out.println(jiraRequest);

		//POST Request
//		NewRestRequest givenRequest = createTestRequest();
//		Request returnedRequest = jiraServicedeskDAO.postNewRequest(givenRequest); // $NON-NLS-1$
//		System.out.println(returnedRequest);
		
		//GET My Requests
		List<Request> myRequests = jiraServicedeskDAO.getMyRequests();
		System.out.println(myRequests);
		
		//GET ServiceDesk
		ServiceDesk serviceDeskById = jiraServicedeskDAO.getServiceDeskById("1");
		System.out.println(serviceDeskById);
		
		client.close();
	}


	private static NewRestRequest createTestRequest() {

		int requestTypeId = _REQUESTTYPE_ID_TECHNISCHERSERVICE;
		int serviceDeskId = _SERVICEDESK_ID_HEIZUNG;
		NewRestRequestFieldValue requestFieldValues = new NewRestRequestFieldValue("REST JAVA TEST", "Submitted via java Rest API call");
		NewRestRequest request = new NewRestRequest(serviceDeskId, requestTypeId, requestFieldValues);

		return request;
	}

	private static Client createHttpClient(HttpAuthenticationFeature authenticationFeature) {
		Client client = ClientBuilder.newClient();
		client.register(authenticationFeature);

		return client;
	}
}
