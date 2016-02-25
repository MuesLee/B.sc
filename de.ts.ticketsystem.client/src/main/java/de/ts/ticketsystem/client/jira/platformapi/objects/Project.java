package de.ts.ticketsystem.client.jira.platformapi.objects;

public class Project {
	
	private String id;

	
	public Project(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
