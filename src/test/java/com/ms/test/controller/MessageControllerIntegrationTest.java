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
import com.ms.test.beans.Message;

/**
 * Web layer integration-test for UserController
 * 
 * @author Manish
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestBackendApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MessageControllerIntegrationTest {

	private final Logger log = LoggerFactory.getLogger(MessageControllerIntegrationTest.class);

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testGetMessageById401() {
		// No Auth or wrong Auth
		restTemplate = new TestRestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Message> response = restTemplate.exchange(createURLWithPort("/messages/getmessagebyid/1"),
				HttpMethod.GET, entity, Message.class);

		log.info("testGetMessageById401 response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void testGetMessageById404() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Message> response = restTemplate.exchange(createURLWithPort("/fake/messages/getmessagebyid/1"),
				HttpMethod.GET, entity, Message.class);

		log.info("testGetMessageById404 response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void testGetMessageById200() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Message> response = restTemplate.exchange(
				createURLWithPort("/messages/getmessagebyid/1"), HttpMethod.GET, entity, Message.class);

		log.info("testGetMessageById200 response Code: " + response.getStatusCode());
		assertThat(response.getBody().getMessageid()).isEqualTo(1);
	}

	@Test
	public void testGetAllMessages() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/messages/allmessages"),
				HttpMethod.GET, entity, String.class);

		log.info("testGetAllMessages response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void testAddUpdateMessage() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		Message message = new Message("test message..123");

		//First do insertion
		HttpEntity<Message> entity = new HttpEntity<Message>(message, headers);
		ResponseEntity<Message> response = restTemplate.exchange(createURLWithPort("/messages/saveupdatemessage"),
				HttpMethod.POST, entity, Message.class);

		log.info("testAdd response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		//and if response is 200 do update
		if (response.getStatusCode().equals(HttpStatus.OK))
		{
			 message.setMessageid(response.getBody().getMessageid());
			 entity = new HttpEntity<Message>(message, headers);
			 response = restTemplate.exchange(createURLWithPort("/messages/saveupdatemessage"), HttpMethod.POST, entity, Message.class);

			log.info("testUpdate response Code: " + response.getStatusCode());
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		}
	}

	@Test
	public void testDeleteMessage() {
		// Inject Auth
		restTemplate = new TestRestTemplate("user1", "test123");
		headers.setContentType(MediaType.APPLICATION_JSON);

		Message message = new Message(100, "test message..123");

		HttpEntity<Message> entity = new HttpEntity<Message>(message, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/messages/deletemessage"),
				HttpMethod.DELETE, entity, String.class);

		log.info("testDeleteMessage response Code: " + response.getStatusCode());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}