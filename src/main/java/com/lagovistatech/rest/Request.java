package com.lagovistatech.rest;

public interface Request<I, O> {
	void setHeader(Header value);
	void setUrl(String value);
	void setMethod(Method value);
	void setBody(I value) throws Exception;

	Response<O> send() throws Exception;
}