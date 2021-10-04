package com.lagovistatech.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonRequest<I, O> implements Request<I, O> {
	private StringRequest request;
	private Class<O> responseBodyType;
	private static final ObjectMapper om = new ObjectMapper();
	
	public JsonRequest(Class<O> responseBodyType) {
		this.request = new StringRequest();
		
		request.setHeader(new AcceptHeader(JsonContentType.instance));
		request.setHeader(new ContentTypeHeader(JsonContentType.instance));

		this.responseBodyType = responseBodyType;
	}
	
	public void setHeader(Header value) { request.setHeader(value); }
	public void setUrl(String value) { request.setUrl(value); }
	public void setMethod(Method value) { request.setMethod(value); }
	public void setBody(I value) throws Exception { request.setBody(om.writeValueAsString(value)); }

	public JsonResponse<O> send() throws Exception {
		StringResponse resp = request.send();
		return new JsonResponse<>(responseBodyType, resp);
	}
}
