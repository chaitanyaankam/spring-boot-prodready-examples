package com.spring.edm.controller;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;
import com.spring.edm.entity.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void saveBookTest() {
		HttpHeaders httpHeaders = new HttpHeaders();
		Book testBook = Book.builder().name("Test Book").cost(2).build();
		HttpEntity<Book> entity = new HttpEntity<Book>(testBook, httpHeaders);
		ResponseEntity<String> response = this.testRestTemplate.exchange(
				createURLWithPort("/book"),
				HttpMethod.POST, entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertThat(response.getBody(), equalTo("success"));
	}

	@Test
	public void getBookTest() {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
		ResponseEntity<String> response = this.testRestTemplate.exchange(
				createURLWithPort("/book/Head First Java"),
				HttpMethod.GET, entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		String json = response.getBody();
		assertThat(json, hasJsonPath("$.id", equalTo(1)));
	}
	
	@Test
	public void getAllBooksTest() {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, httpHeaders);
		ResponseEntity<String> response = this.testRestTemplate.exchange(
				createURLWithPort("/books"),
				HttpMethod.GET, entity, String.class);
		String json = response.getBody();
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(json, isJson());
		assertThat(json, hasJsonPath("$[*]", hasSize(greaterThan(0))));
		List<String> names = JsonPath.read(json, "$[*].name");
		assertThat(names, Matchers.hasItem("Head First Java"));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
}
