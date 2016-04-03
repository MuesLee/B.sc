package de.ts.ticketsystem.client.jira;

import java.util.List;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import de.ts.ticketsystem.client.jira.platformapi.objects.ResultPage;

public class GenericJiraRestDAO<T> {

	private final Gson gson;
	private final Class<T> typeParameterClass;

	public GenericJiraRestDAO(final Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
		this.gson = ClientUtils.getGson();
	}

	public T postOne(final Builder builder, final T t) {
		String jsonString = gson.toJson(t);
		final Entity<String> entity = Entity.entity(jsonString, MediaType.APPLICATION_JSON);
		final Response response = post(builder, entity);

		jsonString = response.readEntity(String.class);

		final T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}

	public T postOne(final Builder builder, final Entity<String> entity) {
		final Response response = post(builder, entity);

		System.out.println(response.getStatus());
		final String jsonString = response.readEntity(String.class);

		final T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}

	public List<T> getMany(final Builder builder) throws ClientErrorException {

		final Response response = get(builder);
		final String jsonString = response.readEntity(String.class);

		@SuppressWarnings("unchecked")
		final ResultPage<T> fromJson = gson.fromJson(jsonString, ResultPage.class);
		final List<T> returnedjiraObjects = fromJson.getValues();
		return returnedjiraObjects;
	}

	public T getOne(final Builder builder) throws ClientErrorException {

		final Response response = get(builder);
		final String jsonString = response.readEntity(String.class);

		final T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}

	private Response post(final Builder builder, final Entity<String> entity) throws ClientErrorException {

		Response response = builder.post(entity, Response.class);

		int status = response.getStatus();

		switch (status) {
		case 200:
			// completed succesfully
			break;
		case 400:
		case 401:
		case 403:
			throw new ClientErrorException(response);
		default:
			break;
		}
		return response;
		
	}

	private Response get(final Builder builder) throws ClientErrorException {
		Response response = builder.get();

		int status = response.getStatus();

		switch (status) {
		case 200:
			// completed succesfully
			break;
		case 401:
		case 403:
		case 404:
			throw new ClientErrorException(response);
		default:
			break;
		}

		return response;
	}

}
