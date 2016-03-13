package de.ts.ticketsystem.client.jira;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.google.gson.Gson;

public abstract class AbstractJiraInstance {

	protected WebTarget target;
	protected Gson gson;
	private Client client;

	/**
	 * Constructs a new instance, which is connecting and communicating with the
	 * given WebTarget.
	 * 
	 * This class expects the authentication to be taken care of.
	 * 
	 * @param target
	 *            Weblocation of the Jira Servicedesk
	 */
	public AbstractJiraInstance(URI jiraUri, HttpAuthenticationFeature authenticationFeature) {

		Client client = createHttpClient(authenticationFeature);
		this.target = client.target(jiraUri);
		gson = new Gson();
	}

	private Client createHttpClient(HttpAuthenticationFeature authenticationFeature) {
		client = ClientBuilder.newClient();
		client.register(authenticationFeature);

		return client;
	}

	@Override
	protected void finalize() throws Throwable {
		client.close();
	}

}
