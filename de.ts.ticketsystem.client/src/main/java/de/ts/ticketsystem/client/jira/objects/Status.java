package de.ts.ticketsystem.client.jira.objects;

public class Status {

	public Status() {
	}

	private String status;
	private JiraDate statusDate;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Status [status=" + status + ", statusDate=" + getStatusDate() + "]";
	}

	public JiraDate getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(JiraDate statusDate) {
		this.statusDate = statusDate;
	}

}
