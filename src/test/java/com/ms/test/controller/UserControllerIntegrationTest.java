package com.ms.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ms.test.TestBackendApplication;
import com.ms.test.beans.User;

/**
 * Web layer integration-test for UserController
 * 
 * @author Manish
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestBackendApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerIntegrationTest {

	private final Logger log = LoggerFactory.getLogger(UserControllerIntegrationTest.class);
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testGetUserById401() {
		// No Auth or wrong Auth
		restTemplate = new TestRestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<User> response = restTemplate.exchange(createURLWithPort("/users/getuserbyid/1"), HttpMethod.GET,
				entity, User.class);

		log.info("testGetUserById401 response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void testGetUserById404() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<User> response = restTemplate.exchange(createURLWithPort("/fake/users/getuserbyid/1"),
				HttpMethod.GET, entity, User.class);

		log.info("testGetUserById404 response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void testGetUserById200() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);
		String testUser = "user1";

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<User> response = restTemplate.exchange(createURLWithPort("/users/getuserbyid/1"), HttpMethod.GET,
				entity, User.class);

		log.info("testGetUserById200 response Code: " + response.getStatusCode());
		assertThat(response.getBody().getUsername()).isEqualTo(testUser);
	}

	@Test
	public void testGetAllUsers() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/users/allusers"), HttpMethod.GET,
				entity, String.class);

		log.info("testGetAllUsers response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void testAddUpdateUser() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		User user = new User("testuser", "test123"); 

		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		ResponseEntity<User> response = restTemplate.exchange(createURLWithPort("/users/saveupdateuser"), HttpMethod.POST, entity, User.class);
		
		log.info("testAddUser response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		//and if response is 200 do update
		if (response.getStatusCode().equals(HttpStatus.OK))
		{
			user.setUserid(response.getBody().getUserid());
			entity = new HttpEntity<User>(user, headers);
			response = restTemplate.exchange(createURLWithPort("/users/saveupdateuser"), HttpMethod.POST, entity, User.class);
			
			log.info("testUpdateUser response Code: " + response.getStatusCode());
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		}
	}
	
	@Test
	public void testDeleteUser() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		//delete same user was last created
		User user = new User(100, "testuser", "test123"); 

		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/users/deleteuser"), HttpMethod.DELETE, entity, String.class);
		
		log.info("testDeleteUser response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}