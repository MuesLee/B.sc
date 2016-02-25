package de.ts.ticketsystem.client.jira.platformapi;

import com.google.gson.Gson;

public class ClientUtils {

	private static Gson gson;
	
	public static Gson getGson() {
		
		if(gson == null)
			gson = new Gson();
		
		return gson;
	}

}
