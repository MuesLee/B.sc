package de.ts.ticketsystem.client.jira.platformapi;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import de.ts.ticketsystem.client.jira.platformapi.objects.ResultPage;

public class GenericJiraRestDAO<T> {

	
	private Gson gson;
	private Class<T> typeParameterClass;
	
	public GenericJiraRestDAO(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
		this.gson = ClientUtils.getGson();
	}
	
	public T postOne(Builder builder, T t)
	{
		String jsonString = gson.toJson(t);
		Response response = builder.post(Entity.entity(jsonString, MediaType.APPLICATION_JSON), Response.class);

		System.out.println(response.getStatus());
		jsonString = response.readEntity(String.class);

		T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass );
		return returnedjiraObject;
	}
	public T postOne(Builder builder, Entity<String> entity)
	{
		Response response = builder.post(entity, Response.class);
		
		System.out.println(response.getStatus());
		String jsonString = response.readEntity(String.class);
		
		T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass );
		return returnedjiraObject;
	}
	
	public List<T> getMany(Builder builder) {
		
		Response response = builder.get();
		String jsonString = response.readEntity(String.class);
		
		@SuppressWarnings("unchecked")
		ResultPage<T> fromJson = gson.fromJson(jsonString, ResultPage.class);
		List<T> returnedjiraObjects = fromJson.getValues();
		return returnedjiraObjects;
	}
	
	public T getOne(Builder builder) {
		
		Response response = builder.get();
		String jsonString = response.readEntity(String.class);
		
		T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}
	
}
