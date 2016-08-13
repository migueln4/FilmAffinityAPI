package com.jjimenez.filmaffinity.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;

import com.jjimenez.filmaffinity.entity.ProxyHTTP;
import com.jjimenez.filmaffinity.exception.NotEstablishedConnectionException;

/**
 * Abstract implementation of scraping methods (using {@link ProxyHTTP} or not)
 * 
 * <p>
 * This class is only used internally.
 * </p>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 * 
 */
public abstract class AbstractCore {

	private static Connection connection;
	private static Response response;
	private static Map<String, String> headers = new HashMap<String, String>();
	private static Map<String, String> parameters = new HashMap<String, String>();
	protected static Map<String, String> cookies = new HashMap<String, String>();
	protected static ProxyHTTP proxy;

	/**
	 * Established connection through an {@code URL}, through proxy or not,
	 * including {@code headers}, {@code cookies}, {@code parameters} and
	 * {@code method}.
	 * 
	 * @param URL
	 *            the web address where do you want to connect
	 * @param cookies
	 *            list of pairs <em>key-value</em> with the cookies
	 * @param headers
	 *            list of pairs <em>key-value</em> with the headers
	 * @param parameters
	 *            list of pairs <em>key-value</em> with the parameters
	 * @param method
	 *            {@link Method} to realized the request
	 * @return {@link ResponseEntity} as a result to the request
	 * @throws IOException
	 * @throws NotEstablishedConnectionException 
	 */
	private static ResponseEntity execute(final String URL, final Map<String, String> cookies,
			final Map<String, String> headers, Map<String, String> parameters, Method method) throws IOException, NotEstablishedConnectionException {

		UtilsCore.setProxy(proxy);

		Validate.notEmpty(URL, String.format(Constants.MESSAGE_NOT_EMPTY, "URL"));
		Validate.notNull(cookies, String.format(Constants.MESSAGE_NOT_NULL, "Cookies"));
		Validate.notNull(headers, String.format(Constants.MESSAGE_NOT_NULL, "Headers"));
		Validate.notNull(parameters, String.format(Constants.MESSAGE_NOT_NULL, "Parameters"));
		Validate.notNull(method, String.format(Constants.MESSAGE_NOT_NULL, "Method"));

		connection = Jsoup.connect(URL).method(method).data(parameters).cookies(cookies);
		for (String header : headers.keySet()) {
			connection.header(header, headers.get(header));
		}

		response = connection.execute();
		if (response == null)
			throw new NotEstablishedConnectionException(Constants.MESSAGE_NOT_ESTABLISHED_CONNECTION_EXCEPTION);

		ResponseEntity responseEntity = ResponseEntity.getInstance();
		responseEntity.setBody(response.body());
		responseEntity.setContentype(response.contentType());
		responseEntity.setCookies(response.cookies());
		responseEntity.setHeaders(response.headers());
		responseEntity.setResponse(response);

		return responseEntity;
	}

	/**
	 * Established connection with the search page through an {@code URL},
	 * {@code name} and {@code type} of the search.
	 * 
	 * @param URL
	 *            to go to the search page
	 * @param name
	 *            of the searching
	 * @param type
	 *            {@link TypeSearch} of search
	 * @return {@link ResponseEntity} as a result to the request
	 * @throws IOException
	 * @throws NotEstablishedConnectionException 
	 */
	protected static ResponseEntity search(final String URL, final String name, final String type) throws IOException, NotEstablishedConnectionException {

		parameters.put(Constants.PARAMETER_STEXT, name);
		parameters.put(Constants.PARAMETER_STYPE, type);

		return execute(URL, cookies, headers, parameters, Method.GET);
	}

	/**
	 * Established connection through an {@code URL} with the different tabs of
	 * the film.
	 * 
	 * @param URL
	 *            the tab where you want to inspect
	 * @return {@link ResponseEntity} as a result to the request
	 * @throws IOException
	 * @throws NotEstablishedConnectionException 
	 */
	protected static ResponseEntity accessToTabMovie(final String URL) throws IOException, NotEstablishedConnectionException {
		return execute(URL, cookies, headers, parameters, Method.GET);
	}

}
