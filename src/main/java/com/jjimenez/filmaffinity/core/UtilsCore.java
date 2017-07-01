package com.jjimenez.filmaffinity.core;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.apache.commons.lang3.StringUtils;

import com.jjimenez.filmaffinity.entity.ProxyHTTP;

/**
 * Set of methods helps to {@link AbstractCore}
 * 
 * <p>
 * This class is only used internally.
 * </p>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
class UtilsCore {

	/**
	 * If proxy <em>not null </em>, established into the system the properties
	 * to connect through proxy. In another case, it will be removed the
	 * properties on the system.
	 * 
	 * @param proxy
	 *            {@link ProxyHTTP} to configure the system
	 */
	static void setProxy(final ProxyHTTP proxy) {

		if (proxy != null) {
			System.setProperty(Constants.HTTP_PROXY_HOST, proxy.getHost());
			System.setProperty(Constants.HTTP_PROXY_PORT, proxy.getPort());
			System.setProperty(Constants.HTTPS_PROXY_HOST, proxy.getHost());
			System.setProperty(Constants.HTTPS_PROXY_PORT, proxy.getPort());
			if (!StringUtils.isEmpty(proxy.getPassword()) && !StringUtils.isEmpty(proxy.getUser())) {
				Authenticator.setDefault(new Authenticator() {
					@Override
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(proxy.getUser(), proxy.getPassword().toCharArray());
					}
				});
				System.setProperty(Constants.HTTP_PROXY_USER, proxy.getUser());
				System.setProperty(Constants.HTTP_PROXY_PASSWORD, proxy.getPassword());
			}
		} else {
			if (existsKey(Constants.HTTP_PROXY_HOST))
				System.getProperties().remove(Constants.HTTP_PROXY_HOST);
			if (existsKey(Constants.HTTP_PROXY_PORT))
				System.getProperties().remove(Constants.HTTP_PROXY_PORT);
			if (existsKey(Constants.HTTP_PROXY_USER))
				System.getProperties().remove(Constants.HTTP_PROXY_USER);
			if (existsKey(Constants.HTTP_PROXY_PASSWORD))
				System.getProperties().remove(Constants.HTTP_PROXY_PASSWORD);
			if (existsKey(Constants.HTTPS_PROXY_HOST))
				System.getProperties().remove(Constants.HTTPS_PROXY_HOST);
			if (existsKey(Constants.HTTPS_PROXY_PORT))
				System.getProperties().remove(Constants.HTTPS_PROXY_PORT);
		}

	}

	/**
	 * Check if the given key exists on the system
	 * 
	 * @param key to search
	 * @return <em>TRUE</em> if exists, <em>FALSE</em> in another case
	 */
	private static boolean existsKey(String key) {
		return key != null && System.getProperties().containsKey(key);
	}

}
