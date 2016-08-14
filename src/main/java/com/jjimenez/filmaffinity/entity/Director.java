/**
 * 
 */
package com.jjimenez.filmaffinity.entity;

/**
 * Entity to reference the movie director
 * 
 * @author Jesus Jimenez
 * @since 0.2.0
 */
public class Director extends Person {
	
	private Director(){}
	
	public static Director getInstance(){
		return new Director();
	}
	
	@Override
	public String toString() {
		return "Director [Name=" + getName() + ", MovieList=" + getMovieList() + "]";
	}
}
