package com.lagovistatech.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class HttpClientTest {
	@Test
	void Post_Success() throws Exception {
		JsonPlaceHolderPost post = new JsonPlaceHolderPost();
		post.setUserId(128);
		post.setTitle("Test");
		post.setBody("Hello world!");
		
		ObjectMapper om = new ObjectMapper();
		String postJson = om.writeValueAsString(post);
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
		requestBuilder.uri(new URI("https://jsonplaceholder.typicode.com/posts"));
		requestBuilder.header("Content-Type", "application/json");
		
		HttpRequest.BodyPublisher bodyPublisher = BodyPublishers.ofString(postJson);
		
		requestBuilder.method("POST", bodyPublisher);
		HttpRequest request = requestBuilder.build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		assertEquals(201, response.statusCode());
		
		JsonPlaceHolderPost newPost = om.readValue(response.body(), JsonPlaceHolderPost.class);

		assertEquals(post.getTitle(), newPost.getTitle());
		assertEquals(post.getBody(), newPost.getBody());
		assertEquals(post.getUserId(), newPost.getUserId());
	}
}