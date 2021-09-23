package com.lagovistatech.rest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JsonRequestTest {
	@Test
	void Post_Success() throws Exception {
		JsonPlaceHolderPost post = new JsonPlaceHolderPost();
		post.setUserId(128);
		post.setTitle("Test");
		post.setBody("Hello world!");
		
		JsonRequest<JsonPlaceHolderPost, JsonPlaceHolderPost> req = new JsonRequest<JsonPlaceHolderPost, JsonPlaceHolderPost>(JsonPlaceHolderPost.class);
		req.setUrl("https://jsonplaceholder.typicode.com/posts");
		req.setHeader(new ContentTypeHeader(JsonContentType.instance));
		req.setHeader(new AcceptHeader(JsonContentType.instance));
		req.setMethod(Method.POST);
		req.setBody(post);
		JsonResponse<JsonPlaceHolderPost> resp = req.send();
		
		assertEquals(201, resp.getStatus());
		
		JsonPlaceHolderPost newPost = resp.getBody();

		assertEquals(post.getTitle(), newPost.getTitle());
		assertEquals(post.getBody(), newPost.getBody());
		assertEquals(post.getUserId(), newPost.getUserId());
	}
}
