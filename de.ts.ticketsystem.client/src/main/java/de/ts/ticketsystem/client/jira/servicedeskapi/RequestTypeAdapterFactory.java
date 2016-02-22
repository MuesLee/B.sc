package de.ts.ticketsystem.client.jira.servicedeskapi;

import com.google.gson.JsonElement;

import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Request;
import de.ts.ticketsystem.client.main.CustomizedTypeAdapterFactory;

public class RequestTypeAdapterFactory extends CustomizedTypeAdapterFactory<Request>{

	public RequestTypeAdapterFactory(Class<Request> customizedClass) {
		super(customizedClass);
	}
	
	@Override
	protected void beforeWrite(Request source, JsonElement toSerialize) {

	
	}

}
