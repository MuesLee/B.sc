package de.ts.ticketsystem.client.jira.platformapi;

import javax.ws.rs.client.WebTarget;

import com.google.gson.Gson;

public abstract class JiraDAO {

	protected WebTarget target;
	protected Gson gson;

	/**
	 * Constructs a new instance, which is connecting and communicating with the
	 * given WebTarget.
	 * 
	 * This class expects the authentication to be taken care of.
	 * 
	 * @param target
	 *            Weblocation of the Jira Servicedesk
	 */
	public JiraDAO(WebTarget target) {
		this.target = target;
		gson = new Gson();
	}

}
