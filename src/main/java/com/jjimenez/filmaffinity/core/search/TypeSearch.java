package com.jjimenez.filmaffinity.core.search;

/**
 * Different types of search
*  <li>{@link #TITLE}</li>
 *  <li>{@link #CAST}</li>
 *  <li>{@link #DIRECTOR}</li>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
enum TypeSearch {
	/**
	 * TITLE (search movies)
	 */
	TITLE("title"),
	/**
	 * CAST (search actors)
	 */
	CAST("cast"),
	/**
	 * DIRECTOR (search directors)
	 */
	DIRECTOR("director");
	
	private final String typeSearch;
	
	TypeSearch(String typeSearch){
		this.typeSearch = typeSearch;
	}

	public String getTypeSearch() {
		return typeSearch;
	}
	
}
