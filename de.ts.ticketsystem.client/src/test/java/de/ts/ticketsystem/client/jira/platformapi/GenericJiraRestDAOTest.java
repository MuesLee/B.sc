package de.ts.ticketsystem.client.jira.platformapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.ts.ticketsystem.client.jira.ClientUtils;
import de.ts.ticketsystem.client.jira.platformapi.GenericJiraRestDAO;
import de.ts.ticketsystem.client.jira.platformapi.objects.Issue;
import de.ts.ticketsystem.client.jira.platformapi.objects.IssueType;
import de.ts.ticketsystem.client.jira.platformapi.objects.Project;

@RunWith(MockitoJUnitRunner.class)
public class GenericJiraRestDAOTest {

	private GenericJiraRestDAO<Issue> classUnderTest;

	@Mock
	private Builder builder;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private Response response;

	private Issue expected;

	private String jsonstring;

	private Entity<String> entity;

	@Before
	public void setUp() {
		classUnderTest = new GenericJiraRestDAO<>(Issue.class);
		expected = new Issue(new Project("1"), "sum", "desc", new IssueType("1", "A"));
		jsonstring = ClientUtils.getGson().toJson(expected);
		entity = Entity.entity(jsonstring, MediaType.APPLICATION_JSON);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testGetReturns200() throws Exception {
		// Arrange
		Issue expected = new Issue(new Project("1"), "sum", "desc", new IssueType("1", "A"));
		Mockito.when(response.getStatus()).thenReturn(200);
		 
		String jsonstring = ClientUtils.getGson().toJson(expected);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(jsonstring);
		Mockito.when(builder.get()).thenReturn(response);
		// Act
		Issue actual = classUnderTest.getOne(builder);
		// Assert
		assertEquals(expected, actual);
	}

	@Test(expected = ClientErrorException.class)
	public void testGetReturns401() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(401);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.get()).thenReturn(response);
		// Act
		classUnderTest.getOne(builder);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testGetReturns404() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(404);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.get()).thenReturn(response);
		// Act
		classUnderTest.getOne(builder);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testGetReturns403() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(403);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.get()).thenReturn(response);
		// Act
		classUnderTest.getOne(builder);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testPostReturns201() throws Exception {

		Mockito.when(response.getStatus()).thenReturn(201);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.SUCCESSFUL);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(jsonstring);
		Mockito.when(builder.post(entity, Response.class)).thenReturn(response);
		// Act
		Issue actual = classUnderTest.postOne(builder, expected);
		// Assert
		assertEquals(expected, actual);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testPostReturns200() throws Exception {

		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.SUCCESSFUL);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(jsonstring);
		Mockito.when(builder.post(entity, Response.class)).thenReturn(response);
		// Act
		Issue actual = classUnderTest.postOne(builder, expected);
		// Assert
		assertEquals(expected, actual);
	}

	@Test(expected = ClientErrorException.class)
	public void testPostReturns401() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(401);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.post(entity, Response.class)).thenReturn(response);
		// Act
		classUnderTest.postOne(builder, expected);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testPostReturns404() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(404);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.post(entity, Response.class)).thenReturn(response);
		// Act
		classUnderTest.postOne(builder, expected);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testPostReturns403() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(403);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.post(entity, Response.class)).thenReturn(response);
		// Act
		classUnderTest.postOne(builder, expected);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testPostReturns400() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(400);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.post(entity, Response.class)).thenReturn(response);
		// Act
		classUnderTest.postOne(builder, expected);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testDeleteReturns400() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(400);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.delete(Response.class)).thenReturn(response);
		// Act
		classUnderTest.deleteOne(builder);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testDeleteReturns401() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(401);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.delete(Response.class)).thenReturn(response);
		// Act
		classUnderTest.deleteOne(builder);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testDeleteReturns403() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(400);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.delete(Response.class)).thenReturn(response);
		// Act
		classUnderTest.deleteOne(builder);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testDeleteReturns404() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(400);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.delete(Response.class)).thenReturn(response);
		// Act
		classUnderTest.deleteOne(builder);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testDeleteReturns200() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(null);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.SUCCESSFUL);
		Mockito.when(builder.delete(Response.class)).thenReturn(response);
		// Act
		Issue deleteOne = classUnderTest.deleteOne(builder);
		// Assert
		assertEquals(null, deleteOne);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testPutReturns200() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(jsonstring);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.SUCCESSFUL);
		Mockito.when(builder.put(entity, Response.class)).thenReturn(response);
		// Act
		Issue putOne = classUnderTest.putOne(builder, entity);
		// Assert
		assertEquals(expected, putOne);
	}
	@Test
	@SuppressWarnings("unchecked")
	public void testPutReturns204() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(204);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(entity);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.SUCCESSFUL);
		Mockito.when(builder.put(entity, Response.class)).thenReturn(response);
		// Act
		Issue putOne = classUnderTest.putOne(builder, entity);
		// Assert
		assertEquals(null, putOne);
	}

	@Test(expected = ClientErrorException.class)
	public void testPutReturns400() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(400);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.put(entity, Response.class)).thenReturn(response);
		// Act
		classUnderTest.putOne(builder, entity);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testPutReturns401() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(401);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.put(entity, Response.class)).thenReturn(response);
		// Act
		classUnderTest.putOne(builder, entity);
		// Assert
		fail("Should have thrown an Exception");
	}

	@Test(expected = ClientErrorException.class)
	public void testPutReturns403() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(403);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.put(entity, Response.class)).thenReturn(response);
		// Act
		classUnderTest.putOne(builder, entity);
		// Assert
		fail("Should have thrown an Exception");
	}
	@Test(expected = ClientErrorException.class)
	public void testPutReturns412() throws Exception {
		// Arrange
		Mockito.when(response.getStatus()).thenReturn(412);
		Mockito.when(response.getStatusInfo().getFamily()).thenReturn(Response.Status.Family.CLIENT_ERROR);
		Mockito.when(builder.put(entity, Response.class)).thenReturn(response);
		// Act
		classUnderTest.putOne(builder, entity);
		// Assert
		fail("Should have thrown an Exception");
	}

}
