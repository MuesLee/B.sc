package de.ts.rfr.main;

import java.net.URI;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

		initJiraPlatformApi();

		jiraTestCase1(basicAuthenticationFeature);
		//jiraTestCase2(basicAuthenticationFeature);
	}

	private static void initJiraPlatformApi() {
		basicAuthenticationFeature = HttpAuthenticationFeature.basic(Properties.getString("Main.JIRA_ACCOUNTNAME"),
				Properties.getString("Main.JIRA_PASSWORD"));
		jiraServicedesk = new JiraServicedesk(jiraServerUri, basicAuthenticationFeature);
		jiraPlatformDAO = new JiraPlatform(jiraServerUri, basicAuthenticationFeature);
	}

	private static void jiraTestCase2(HttpAuthenticationFeature basicAuthenticationFeature) {
		GregorianCalendar calendar = new GregorianCalendar();
		Date startDate = calendar.getTime();
		calendar.add(GregorianCalendar.HOUR, 2);
		Date endDate = calendar.getTime();

		String line = "1;Schulung XYZ;B 02;11224;" + startDate + ";" + endDate
				+ "; Status;Max Mustermann;Maria Musterfrau";
		Issue issue = RfrParser.parseLineToIssue(line);

		Issue returnedIssue = jiraPlatformDAO.postIssue(issue);
		System.out.println(returnedIssue);

	}

	private static void jiraTestCase1(HttpAuthenticationFeature basicAuthenticationFeature) {
//
//		// GET Request
//		Request jiraRequest = jiraServicedesk.getRequestById("HEIZ-4"); //$NON-NLS-1$
//		System.out.println(jiraRequest);

		// POST Request
		NewRestRequest givenRequest = createTestRequest();
		Request returnedRequest = jiraServicedesk.postRequest(givenRequest); // $NON-NLS-1$
		System.out.println(returnedRequest);

//		// GET My Requests
//		List<Request> myRequests = jiraServicedesk.getMyRequests();
//		System.out.println(myRequests);
//		//
//		// GET ServiceDesk
//		Servicedesk serviceDeskById = jiraServicedesk.getServiceDeskById("1");
//		System.out.println(serviceDeskById);
//
//		Issue issue = jiraPlatformDAO.getIssue("HEIZ-2");
//		System.out.println(issue);
	}

	private static NewRestRequest createTestRequest() {

		int requestTypeId = _REQUESTTYPE_ID_TECHNISCHERSERVICE;
		int serviceDeskId = _SERVICEDESK_ID_HEIZUNG;
		NewRestRequestFieldValue requestFieldValues = new NewRestRequestFieldValue("Request JSD help via REST",
				"I need a new mouse for my Mac");
		NewRestRequest request = new NewRestRequest(serviceDeskId, requestTypeId, requestFieldValues);
		request.setRequestParticipants(new String[]{"admin"});

		return request;
	}

}
