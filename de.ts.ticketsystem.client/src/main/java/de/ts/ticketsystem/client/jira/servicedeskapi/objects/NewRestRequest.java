package de.ts.ticketsystem.client.jira.servicedeskapi.objects;

public class NewRestRequest {

	private Integer serviceDeskId;
	private Integer requestTypeId;
	
	private NewRestRequestFieldValue requestFieldValues;
	private String[] requestParticipants;
	
	

	public NewRestRequest(final Integer serviceDesk, final Integer requestType, final NewRestRequestFieldValue requestFieldValues) {
		super();
		this.serviceDeskId = serviceDesk;
		this.requestTypeId = requestType;
		this.requestFieldValues = requestFieldValues;
	}

	public Integer getServiceDesk() {
		return serviceDeskId;
	}

	public void setServiceDesk(final Integer serviceDesk) {
		this.serviceDeskId = serviceDesk;
	}

	public Integer getRequestType() {
		return requestTypeId;
	}

	public void setRequestType(final Integer requestType) {
		this.requestTypeId = requestType;
	}

	public NewRestRequestFieldValue getRequestFieldValues() {
		return requestFieldValues;
	}

	public void setRequestFieldValues(final NewRestRequestFieldValue requestFieldValues) {
		this.requestFieldValues = requestFieldValues;
	}

	public String[] getRequestParticipants() {
		return requestParticipants;
	}

	public void setRequestParticipants(final String[] requestParticipants) {
		this.requestParticipants = requestParticipants;
	}



	
	
	
}
