package de.ts.ticketsystem.client.jira.servicedeskapi.objects;

import java.net.URI;

public class Links {

	private URI self;
	private URI jiraRest;
	public URI getSelf() {
		return self;
	}
	public void setSelf(URI self) {
		this.self = self;
	}
	public URI getJiraRest() {
		return jiraRest;
	}
	public void setJiraRest(URI jiraRest) {
		this.jiraRest = jiraRest;
	}
	@Override
	public String toString() {
		return "Links [self=" + self + ", jiraRest=" + jiraRest + "]";
	}
	
	
	
	
}
