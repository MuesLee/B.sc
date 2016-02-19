package de.ts.ticketsystem.client.main;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.google.gson.Gson;

import de.ts.ticketsystem.client.jira.objects.Request;

public class Main {

	private static URI jiraServerUri = UriBuilder.fromUri("https://callmats.atlassian.net").build();

	public static void main(String[] args) {
		test1();


	}

	private static void test1() {
		
		HttpAuthenticationFeature basicAuthenticationFeature = HttpAuthenticationFeature.basic("admin", "Ah1005desc");
		
		Client client = createHttpClient(jiraServerUri, basicAuthenticationFeature);

		WebTarget target = client.target(jiraServerUri);
		
		Request jiraRequest = getRequestById(target);
		
		System.out.println(jiraRequest);
		client.close();
	}

	private static Request getRequestById(WebTarget target) {
		//GET /rest/servicedeskapi/request/{issueIdOrKey}
		
		Builder request = target.path("rest").path("servicedeskapi").path("request").path("HEIZ-4")
				.request(MediaType.APPLICATION_JSON);
		request.header("X-ExperimentalApi", "opt-in");
		Response response = request.get();
		String jsonString = response.readEntity(String.class);
		Gson gson = new Gson();
		Request jiraRequest = gson.fromJson(jsonString, Request.class);
		return jiraRequest;
	}
	
	private static Client createHttpClient(URI uri, HttpAuthenticationFeature authenticationFeature)
	{
		Client client = ClientBuilder.newClient();
		client.register(authenticationFeature);
		
		return client;
	}
}
