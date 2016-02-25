package de.ts.ticketsystem.client.jira.platformapi.objects;

public class Issue {

	private IssueFields fields;
	
	public Issue(IssueFields fields) {
		super();
		this.fields = fields;
	}

	public IssueFields getFields() {
		return fields;
	}

	public void setFields(IssueFields fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "Issue [fields=" + fields + "]";
	}
	
	
	
	
	
}
