package com.jjimenez.filmaffinity.core.search;

/**
 * Basic entity create as a map with id and title of a movie
 * 
 * <p>
 * This class is only used internally.
 * </p>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
public class BasicMovieEntity {

	private String id;
	private String title;

	private BasicMovieEntity() {
	}

	protected static BasicMovieEntity getInstance() {
		return new BasicMovieEntity();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
