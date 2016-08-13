package com.jjimenez.filmaffinity.core;

/**
 * Constants to <em>com.jjimenez.filmaffinity.core</em> package
 * 
 * <p>
 * This class is only used internally.
 * </p>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
public final class Constants {

	// PROXY KEYS
	protected final static String HTTP_PROXY_HOST = "http.proxyHost";
	protected final static String HTTP_PROXY_PORT = "http.proxyPort";
	protected final static String HTTP_PROXY_USER = "http.proxyUser";
	protected final static String HTTP_PROXY_PASSWORD = "http.proxyPassword";
	protected final static String HTTPS_PROXY_HOST = "https.proxyHost";
	protected final static String HTTPS_PROXY_PORT = "https.proxyPort";

	// PARAMETERS TO SEARCH
	protected final static String PARAMETER_STEXT = "sfile";
	protected final static String PARAMETER_STYPE = "stype";

	// MESSAGES
	protected final static String MESSAGE_NOT_NULL = "%s must not be null";
	protected final static String MESSAGE_NOT_EMPTY = "%s must not be empty";
	protected final static String MESSAGE_NOT_ESTABLISHED_CONNECTION_EXCEPTION = "The server did not return any response";

}
