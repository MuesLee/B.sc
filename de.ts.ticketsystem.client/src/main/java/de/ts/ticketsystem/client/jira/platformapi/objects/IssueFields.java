package de.ts.ticketsystem.client.jira.platformapi.objects;

public class IssueFields {

	private Project project;
	
	private String summary;
	private String description;
	
	private IssueType issueType;
	
	
	public IssueFields(Project project, String summary, String description, IssueType issueType) {
		super();
		this.project = project;
		this.summary = summary;
		this.description = description;
		this.issueType = issueType;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	@Override
	public String toString() {
		return "IssueFields [project=" + project + ", summary=" + summary + ", description=" + description
				+ ", issueType=" + issueType + "]";
	}
	
	
	
	
}
