package de.ts.ticketsystem.client.jira.platformapi;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public abstract class AbstractJiraInstance {

	private final WebTarget target;
	private final Client client;

	/**
	 * Constructs a new instance, which is connecting and communicating with the
	 * given WebTarget.
	 * 
	 * 
	 * @param target
	 *            Weblocation of the Jira Servicedesk
	 * @param authenticationFeature
	 * 			  Authentification information for the Jira Server
	 */
	public AbstractJiraInstance(final URI jiraUri, final HttpAuthenticationFeature authenticationFeature) {

		this.client = ClientBuilder.newClient();
		this.client.register(authenticationFeature);

		this.target = client.target(jiraUri);
	}

	@Override
	protected void finalize() throws Throwable {
		client.close();
	}

	public WebTarget getTarget() {
		return target;
	}

}
