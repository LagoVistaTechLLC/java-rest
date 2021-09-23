package com.lagovistatech.rest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class StringRequestTest {
	@Test
	void Post_Success() throws Exception {
		JsonPlaceHolderPost post = new JsonPlaceHolderPost();
		post.setUserId(128);
		post.setTitle("Test");
		post.setBody("Hello world!");
		
		ObjectMapper om = new ObjectMapper();
		String postJson = om.writeValueAsString(post);
		
		StringRequest req = new StringRequest();
		req.setUrl("https://jsonplaceholder.typicode.com/posts");
		req.setHeader(new ContentTypeHeader(JsonContentType.instance));
		req.setHeader(new AcceptHeader(JsonContentType.instance));
		req.setMethod(Method.POST);
		req.setBody(postJson);
		StringResponse resp = req.send();
		
		assertEquals(201, resp.getStatus());
		
		JsonPlaceHolderPost newPost = om.readValue(resp.getBody(), JsonPlaceHolderPost.class);

		assertEquals(post.getTitle(), newPost.getTitle());
		assertEquals(post.getBody(), newPost.getBody());
		assertEquals(post.getUserId(), newPost.getUserId());
	}
}
