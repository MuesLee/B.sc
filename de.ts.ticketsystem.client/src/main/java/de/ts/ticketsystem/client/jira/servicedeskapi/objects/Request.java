package de.ts.ticketsystem.client.jira.servicedeskapi.objects;

import java.net.URI;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a full JIRA ServiceDesk Request. This class should not be created
 * manually. It should be deserialized from a JSON Response of your JIRA instance.
 * Use {@link NewRestRequest} to create a new Request, which can be posted to
 * your JIRA instance.
 *
 */
public class Request {

	private URI self;
	private String expand;

	private Integer issueId;
	private Integer issueTypeId;
	private String issueKey;

	private Integer serviceDeskId;
	private Integer requestTypeId;

	private Integer projectId;

	private Status currentStatus;

	private JiraDate createdDate;

	private Reporter reporter;

	@SerializedName("_links")
	private Links links;

	private List<FieldValue> requestFieldValues;

	public Request() {

	}

	public Request(int requestTypeId, int serviceDeskId, List<FieldValue> requestFieldValues) {
		super();
		this.setRequestTypeId(requestTypeId);
		this.serviceDeskId = serviceDeskId;
		this.requestFieldValues = requestFieldValues;
	}

	@Override
	public String toString() {
		return "Request [self=" + self + ", expand=" + expand + ", issueId=" + issueId + ", issueTypeId=" + issueTypeId
				+ ", issueKey=" + issueKey + ", serviceDeskId=" + serviceDeskId + ", requestTypeId=" + requestTypeId
				+ ", currentStatus=" + currentStatus + ", createdDate=" + createdDate + ", reporter=" + reporter
				+ ", projectId=" + projectId + ", links=" + links + ", requestFieldValues=" + requestFieldValues + "]";
	}

	public int getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(int issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getIssueKey() {
		return issueKey;
	}

	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}

	public int getServiceDeskId() {
		return serviceDeskId;
	}

	public void setServiceDeskId(int serviceDeskId) {
		this.serviceDeskId = serviceDeskId;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public JiraDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(JiraDate createdDate) {
		this.createdDate = createdDate;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public Reporter getReporter() {
		return reporter;
	}

	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}

	public List<FieldValue> getRequestFieldValues() {
		return requestFieldValues;
	}

	public void setRequestFieldValues(List<FieldValue> requestFieldValues) {
		this.requestFieldValues = requestFieldValues;
	}

	public URI getSelf() {
		return self;
	}

	public void setSelf(URI self) {
		this.self = self;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public int getRequestTypeId() {
		return requestTypeId;
	}

	public void setRequestTypeId(int requestTypeId) {
		this.requestTypeId = requestTypeId;
	}

}
