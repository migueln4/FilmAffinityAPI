/**
 * 
 */
package com.jjimenez.filmaffinity.entity;

/**
 * Entity to reference the movie cast
 * 
 * @author Jesus Jimenez
 * @since 0.2.0
 */
public class Cast extends Person {
	
	private Cast(){}
	
	public static Cast getInstance(){
		return new Cast();
	}
	
	@Override
	public String toString() {
		return "Cast [Name=" + getName() + ", MovieList=" + getMovieList() + "]";
	}
}
