package de.ts.ticketsystem.client.jira;

import java.util.List;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
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

	/**
	 * POST the via builder specified path. Returns the retrieved Object or
	 * throws a subclass of {@link RuntimeException} if the received statuscode
	 * is not 200, 201.
	 * 
	 * @param builder
	 *            fully specified Path
	 * @param t
	 *            Object to Post
	 * @return Object returned, nullable
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 401 or 403, 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200, 201, 400, 401, 404 or 403
	 */
	public T postOne(final Builder builder, final T t) throws ClientErrorException, WebApplicationException {
		String jsonString = gson.toJson(t);
		final Entity<String> entity = Entity.entity(jsonString, MediaType.APPLICATION_JSON);
		final Response response = post(builder, entity);

		jsonString = response.readEntity(String.class);

		final T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}

	/**
	 * PUT the via builder specified path. Returns the retrieved Object or
	 * throws a subclass of {@link RuntimeException} if the received statuscode
	 * is not 201.
	 * 
	 * @param builder
	 *            fully specified Path
	 * @param entity
	 *            changed Object
	 * @return Object returned, nullable
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 401 or 403
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200,204, 400, 401 or 403
	 */

	public T putOne(final Builder builder, final Entity<String> entity)
			throws ClientErrorException, WebApplicationException {
		final Response response = put(builder, entity);

		if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
			return null;
		}

		final String jsonString = response.readEntity(String.class);

		final T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}

	/**
	 * POST the via builder specified path. Returns the retrieved Object or
	 * throws a subclass of {@link RuntimeException} if the received statuscode
	 * is not 200, 201.
	 * 
	 * @param builder
	 *            fully specified Path
	 * @param entity
	 *            Entity to Post
	 * @return Object returned, nullable
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 401 or 403, 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200, 201, 400, 401 or 403, 404
	 */
	public T postOne(final Builder builder, final Entity<String> entity)
			throws ClientErrorException, WebApplicationException {
		final Response response = post(builder, entity);

		System.out.println(response.getStatus());
		final String jsonString = response.readEntity(String.class);

		final T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}

	/**
	 * GET the via builder specified path. Returns the retrieved Objects or
	 * throws a subclass of {@link RuntimeException} if the received statuscode
	 * is not 200.
	 * 
	 * @param builder
	 *            fully specified Path
	 * @param entity
	 *            Entity to Post
	 * @return List of Objects returned
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 403 or 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200, 400, 403 or 404
	 */
	public List<T> getMany(final Builder builder) throws ClientErrorException, WebApplicationException {

		final Response response = get(builder);
		final String jsonString = response.readEntity(String.class);

		@SuppressWarnings("unchecked")
		final ResultPage<T> fromJson = gson.fromJson(jsonString, ResultPage.class);
		final List<T> returnedjiraObjects = fromJson.getValues();
		return returnedjiraObjects;
	}

	/**
	 * GET the via builder specified path. Returns the retrieved Object or
	 * throws a subclass of {@link RuntimeException} if the received statuscode
	 * is not 200.
	 * 
	 * @param builder
	 *            fully specified Path
	 * @param entity
	 *            Entity to Post
	 * @return Object returned
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 403 or 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200, 400, 403 or 404
	 */
	public T getOne(final Builder builder) throws ClientErrorException, WebApplicationException {

		final Response response = get(builder);
		final String jsonString = response.readEntity(String.class);

		final T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}

	/**
	 * DELETE the via builder specified path. Returns the retrieved Object or
	 * throws a subclass of {@link RuntimeException} if the received statuscode
	 * is not 200.
	 * 
	 * @param builder
	 *            fully specified Path
	 * @return Object returned, nullable
	 * @throws ClientErrorException
	 *             if the HTTP-Statuscode is 400, 403 or 404
	 * @throws WebApplicationException
	 *             if the Statuscode is not 200, 400, 403 or 404
	 */
	public T deleteOne(final Builder builder) {
		final Response response = delete(builder);

		if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
			return null;
		}

		final String jsonString = response.readEntity(String.class);
		final T returnedjiraObject = gson.fromJson(jsonString, typeParameterClass);
		return returnedjiraObject;
	}

	private Response post(final Builder builder, final Entity<String> entity)
			throws ClientErrorException, WebApplicationException {

		Response response = builder.post(entity, Response.class);

		int status = response.getStatus();

		switch (status) {
		case 200:
		case 201:
			// completed succesfully
			break;
		case 400:
		case 401:
		case 403:
		case 404:
			throw new ClientErrorException(response);
		default:
			throw new WebApplicationException(response);
		}
		return response;

	}

	private Response delete(final Builder builder) throws ClientErrorException, WebApplicationException {

		Response response = builder.delete(Response.class);

		int status = response.getStatus();

		switch (status) {
		case 200:
		case 204:
			// completed succesfully
			break;
		case 400:
		case 401:
		case 403:
		case 404:
			throw new ClientErrorException(response);
		default:
			throw new WebApplicationException(response);
		}
		return response;

	}

	private Response put(final Builder builder, final Entity<String> entity)
			throws ClientErrorException, WebApplicationException {

		Response response = builder.put(entity, Response.class);

		int status = response.getStatus();

		switch (status) {
		case 200:
		case 204:
			// completed succesfully
			break;
		case 400:
		case 401:
		case 403:
		case 404:
		case 412:
			throw new ClientErrorException(response);
		default:
			throw new WebApplicationException(response);
		}
		return response;

	}

	private Response get(final Builder builder) throws ClientErrorException, WebApplicationException {
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
			throw new WebApplicationException(response);
		}

		return response;
	}

}
