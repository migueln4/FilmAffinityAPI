package com.jjimenez.filmaffinity.core;

import java.util.Map;

import org.jsoup.Connection.Response;

/**
 * Entity to create a customize response from the server
 * 
 * <p>
 * This class is only used internally.
 * </p>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
public class ResponseEntity {

	private String body;
	private String contentype;
	private Map<String, String> cookies;
	private Map<String, String> headers;
	private Response response;

	private ResponseEntity() {
	}

	protected static ResponseEntity getInstance() {
		return new ResponseEntity();
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getContentype() {
		return contentype;
	}

	public void setContentype(String contentype) {
		this.contentype = contentype;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}
