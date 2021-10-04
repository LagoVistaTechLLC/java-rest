package com.lagovistatech.rest;

import java.util.Base64;

public class AuthorizationHeader implements Header {
	private String token;
	public AuthorizationHeader(String user, String password) {
		token = user + ":" + password;
		token = "Basic " + Base64.getEncoder().encodeToString(token.getBytes());
	}
	
	public String getName() { return "Authorization"; }
	public String getValue() { return token; }
}
