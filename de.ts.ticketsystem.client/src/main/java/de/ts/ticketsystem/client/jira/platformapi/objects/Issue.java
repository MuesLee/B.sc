package de.ts.ticketsystem.client.jira.platformapi.objects;

public class Issue {

	private IssueFields fields;
	
	private String id;
	
	private String key;
	
	public Issue() {
	}
	
	public Issue(Project project, String summary, String description, IssueType issueType) {
		super();
		this.fields = new IssueFields(project, summary, description, issueType);
	}

	public IssueFields getFields() {
		return fields;
	}

	public void setFields(IssueFields fields) {
		this.fields = fields;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", key=" + key + ",fields=" + fields + "]";
	}
	
	
	
}
