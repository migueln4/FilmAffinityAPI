package com.jjimenez.filmaffinity.core.search;

/**
 * Constants to <em>com.jjimenez.filmaffinity.core.search</em> package
 * 
 * <p>
 * This class is only used internally.
 * </p>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
public final class ConstantsSearch {

	// URLS
	protected final static String URL_BASE = "https://www.filmaffinity.com/es/";
	protected final static String URL_BASE_EN = "https://www.filmaffinity.com/";
	protected final static String URL_FILM = "film";
	protected final static String URL_IMAGES = "filmimages.php?movie_id=";
	protected final static String URL_TRAILERS = "evideos.php?movie_id=";
	protected final static String URL_SEARCH = "search.php?stext=%s&stype=%s";

	// MESSAGES
	protected static final String MESSAGE_MOVIE_NOT_FOUND_EXCEPTION = "Any movie has founded with this name: %s";
	protected static final String MESSAGE_DIRECTOR_NOT_FOUND_EXCEPTION = "Any director has founded with this name: %s";
	protected static final String MESSAGE_CAST_NOT_FOUND_EXCEPTION = "Any cast has founded with this name: %s";
	protected final static String MESSAGE_NOT_EMPTY = "%s must not be empty";
	protected final static String MESSAGE_NOT_NULL = "%s must not be null";
}
