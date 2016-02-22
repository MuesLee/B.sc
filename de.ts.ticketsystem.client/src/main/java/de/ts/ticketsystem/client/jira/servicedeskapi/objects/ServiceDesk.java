package de.ts.ticketsystem.client.jira.servicedeskapi.objects;

import com.google.gson.annotations.SerializedName;

public class ServiceDesk {

	private Integer id;

	@SerializedName("_links")
	private Links links;

	private Integer projectId;
	private String projectName;
	private String projectKey;

	public ServiceDesk() {
	}

	@Override
	public String toString() {
		return "ServiceDesk [id=" + id + ", links=" + links + ", projectId=" + projectId + ", projectName="
				+ projectName + ", projectKey=" + projectKey + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

}
