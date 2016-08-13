package com.jjimenez.filmaffinity.core.search;

import java.io.IOException;

import com.jjimenez.filmaffinity.entity.Movie;
import com.jjimenez.filmaffinity.entity.ProxyHTTP;
import com.jjimenez.filmaffinity.exception.NotEstablishedConnectionException;

/**
 * Class used to search films, cast or directors into <em>www.filmaffinity.com/es</em>
 * 
 * @author Jesus Jimenez
 * @since 1.0.1
 */
public abstract class Search{
	
	/**
	 * Search a film by means of a name without using a proxy
	 * 
	 * @param name to search
	 * @return {@link Movie} founded
	 * @throws IOException
	 * @throws NotEstablishedConnectionException 
	 */
	public static Movie getMovieByName(final String name) throws IOException, NotEstablishedConnectionException{
		return CoreSearch.getInstance().obtainMovie(name,null);
	}
	
	/**
	 * Search a film by means of a name using a proxy
	 * 
	 * @param name to search
	 * @param proxy {@link ProxyHTTP}
	 * @return {@link Movie} founded
	 * @throws IOException
	 * @throws NotEstablishedConnectionException 
	 */
	public static Movie getMovieByName(final String name, final ProxyHTTP proxy) throws IOException, NotEstablishedConnectionException{
		return CoreSearch.getInstance().obtainMovie(name,proxy);
	}
	
	/**
	 * Search a film by means of a id without using a proxy
	 * 
	 * @param id to search
	 * @return {@link Movie} founded
	 * @throws IOException
	 * @throws NotEstablishedConnectionException 
	 */
	public static Movie getMovieById(final Long id) throws IOException, NotEstablishedConnectionException{
		return CoreSearch.getInstance().obtainMovie(id, null);
	}
	
	/**
	 * Search a film by means of a id using a proxy
	 * 
	 * @param id to search
	 * @param proxy {@link ProxyHTTP}
	 * @return {@link Movie} founded
	 * @throws IOException
	 * @throws NotEstablishedConnectionException 
	 */
	public static Movie getMovieById(final Long id,final ProxyHTTP proxy) throws IOException, NotEstablishedConnectionException{
		return CoreSearch.getInstance().obtainMovie(id, proxy);
	}
}
