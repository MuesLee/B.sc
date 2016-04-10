package de.ts.ticketsystem.client.jira.platformapi.objects;

public class Assignee {
	
	private String name;

	public Assignee(final String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	

}
