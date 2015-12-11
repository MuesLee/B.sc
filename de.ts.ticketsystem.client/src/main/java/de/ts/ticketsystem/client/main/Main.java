package de.ts.ticketsystem.client.main;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

public class Main {

	private static URI jiraServerUri = UriBuilder.fromUri("https://callmats.atlassian.net").build();
	private static URI googleServerUri = UriBuilder.fromUri("https://google.de").build();

	private static URI proxyServerUri = UriBuilder.fromUri("URI").build();
	private static final String proxyUserName = "dont even";
	private static final String proxyPW = "trololol";

	public static void main(String[] args) {
		//test1();
		
		test2();
		
	}
	
	
	private static void test1()
	{
		ClientConfig config = new ClientConfig();
		
		Client client = ClientBuilder.newClient(config);
		client.register(JacksonFeature.class);
		
		WebTarget target = client.target(jiraServerUri);

		Builder request = target.path("rest").path("api").path("latest").path("issue").path("MAT-1").request(MediaType.APPLICATION_JSON);
		String response = request.get(Response.class).toString();

		System.out.println(response);
		client.close();
	}
	
	private static void test2()
	{
		ClientConfig config = new ClientConfig();
		
		
		config.property(ClientProperties.CONNECT_TIMEOUT, 1000);
		config.property(ClientProperties.PROXY_URI, proxyServerUri.toString());
		config.property(ClientProperties.PROXY_PASSWORD, proxyPW);
		config.property(ClientProperties.PROXY_USERNAME, proxyUserName);
		
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(googleServerUri);
		
		Builder request = target.request(MediaType.TEXT_HTML);
		String response = request.get(Response.class).toString();
		
		System.out.println(response);
		client.close();
	}
}
