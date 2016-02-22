package de.ts.ticketsystem.client.jira.objects;

public class NewRestRequest {

	private Integer serviceDesk;
	private Integer requestType;
	
	private NewRestRequestFieldValue requestFieldValues;
	
	

	public NewRestRequest(Integer serviceDesk, Integer requestType, NewRestRequestFieldValue requestFieldValues) {
		super();
		this.serviceDesk = serviceDesk;
		this.requestType = requestType;
		this.requestFieldValues = requestFieldValues;
	}

	public Integer getServiceDesk() {
		return serviceDesk;
	}

	public void setServiceDesk(Integer serviceDesk) {
		this.serviceDesk = serviceDesk;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}

	public NewRestRequestFieldValue getRequestFieldValues() {
		return requestFieldValues;
	}

	public void setRequestFieldValues(NewRestRequestFieldValue requestFieldValues) {
		this.requestFieldValues = requestFieldValues;
	}



	
	
	
}
