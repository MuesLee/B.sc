package de.ts.rfr.main;

import java.net.URI;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.rfr.domain.RfrParser;
import de.ts.ticketsystem.client.jira.platformapi.JiraPlatform;
import de.ts.ticketsystem.client.jira.platformapi.objects.Issue;
import de.ts.ticketsystem.client.jira.servicedeskapi.JiraServicedesk;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequest;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequestFieldValue;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Request;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Servicedesk;

public class Main {

	private static final int _REQUESTTYPE_ID_TECHNISCHERSERVICE = 1;
	private static final int _SERVICEDESK_ID_HEIZUNG = 1;
	private static URI jiraServerUri = UriBuilder.fromUri(Properties.getString("Main.JIRA_SERVICE_DESK_URL")).build(); //$NON-NLS-1$
	private static JiraServicedesk jiraServicedesk;
	private static JiraPlatform jiraPlatformDAO;
	private static HttpAuthenticationFeature basicAuthenticationFeature;

	public static void main(String[] args) {

		initJiraApi();

		jiraTestCase1();

		jiraTestCase2();
	}

	private static void initJiraApi() {

		String userName = Properties.getString("Main.JIRA_ACCOUNTNAME");
		String password = Properties.getString("Main.JIRA_PASSWORD");
		HttpAuthenticationFeature basicAuthenticationFeature = HttpAuthenticationFeature.basic(userName, password);
		
		jiraServicedesk = new JiraServicedesk(jiraServerUri, basicAuthenticationFeature);
		jiraPlatformDAO = new JiraPlatform(jiraServerUri, basicAuthenticationFeature);
	}

	private static void jiraTestCase2() {

		String line = "1;Schulung XYZ;B 02;11224;Sun Mar 13 16:36:07 CET 2016;Sun Mar 13 18:36:07 CET 2016;"
				+ " Status;Max Mustermann;Maria Musterfrau;";

		Issue issue = RfrParser.parseLineToIssue(line);

		try {

			Issue returnedIssue = jiraPlatformDAO.createIssue(issue);
			System.out.println(returnedIssue);
		} catch (WebApplicationException ex) {
			System.out.println("Fehler bei Erstellung des Issues: " + ex.getLocalizedMessage());
		}

	}

	private static void jiraTestCase1() {

		NewRestRequest givenRequest = createTestRequest();

		try {

			Request returnedRequest = jiraServicedesk.createRequest(givenRequest);

			System.out.println(returnedRequest);

		} catch (WebApplicationException ex) {
			System.out.println("Fehler bei Erstellung des Issues: " + ex.getLocalizedMessage());
		}
	}

	//
	// // GET Request
	// Request jiraRequest = jiraServicedesk.getRequestById("HEIZ-4");
	// //$NON-NLS-1$
	// System.out.println(jiraRequest);

	// // GET My Requests
	// List<Request> myRequests = jiraServicedesk.getMyRequests();
	// System.out.println(myRequests);
	// //
	// // GET ServiceDesk
	// Servicedesk serviceDeskById = jiraServicedesk.getServiceDeskById("1");
	// System.out.println(serviceDeskById);
	//
	// Issue issue = jiraPlatformDAO.getIssue("HEIZ-2");
	// System.out.println(issue);

	private static NewRestRequest createTestRequest() {

		int requestTypeId = _REQUESTTYPE_ID_TECHNISCHERSERVICE;
		int serviceDeskId = _SERVICEDESK_ID_HEIZUNG;
		String summary = "Request JSD help via REST";
		String description = "I need a new mouse for my Mac";

		NewRestRequestFieldValue requestFieldValues = new NewRestRequestFieldValue(summary, description);
		NewRestRequest request = new NewRestRequest(serviceDeskId, requestTypeId, requestFieldValues);
		request.setRequestParticipants(new String[] { "admin" });

		return request;
	}

}
