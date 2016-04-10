package de.ts.ticketsystem.client.jira.platformapi.objects;

import com.google.gson.annotations.SerializedName;

public class IssueFields {

	private Project project;
	
	private String summary;
	private String description;
	
	@SerializedName("issuetype")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((issueType == null) ? 0 : issueType.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssueFields other = (IssueFields) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (issueType == null) {
			if (other.issueType != null)
				return false;
		} else if (!issueType.equals(other.issueType))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		return true;
	}
}
