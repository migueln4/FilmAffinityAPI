package com.jjimenez.filmaffinity.test.core;

import java.io.IOException;

import org.apache.commons.lang3.Validate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.jjimenez.filmaffinity.core.search.Search;
import com.jjimenez.filmaffinity.entity.Cast;
import com.jjimenez.filmaffinity.entity.Director;
import com.jjimenez.filmaffinity.entity.Movie;
import com.jjimenez.filmaffinity.exception.NotEstablishedConnectionException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class,loader=AnnotationConfigContextLoader.class)
public class CoreTest {

	@Test
	public void testGetMovieByName() throws IOException, NotEstablishedConnectionException{
		Movie movie = Search.getMovieByName("A todo gas");
		Validate.notNull(movie);
		System.out.println(movie.toString());
	}
	
	@Test
	public void testGetMovieById() throws IOException, NotEstablishedConnectionException{
		Movie movie = Search.getMovieById(827906L);
		Validate.notNull(movie);
		System.out.println(movie.toString());
	}
	
	@Test
	public void testGetDirectorByName() throws IOException, NotEstablishedConnectionException{
		Director director = Search.getMovieListByDirector("Steven Spielberg");
		Validate.notNull(director);
		System.out.println(director.toString());
	}
	
	@Test
	public void testGetCastByName() throws IOException, NotEstablishedConnectionException{
		Cast cast = Search.getMovieListByCast("Will Smith");
		Validate.notNull(cast);
		System.out.println(cast.toString());
	}

}
