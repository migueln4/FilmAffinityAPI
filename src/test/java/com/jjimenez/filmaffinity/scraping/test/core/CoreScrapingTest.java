package com.jjimenez.filmaffinity.scraping.test.core;

import java.io.IOException;

import org.apache.commons.lang3.Validate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.jjimenez.filmaffinity.core.search.Search;
import com.jjimenez.filmaffinity.entity.Movie;
import com.jjimenez.filmaffinity.exception.NotEstablishedConnectionException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class,loader=AnnotationConfigContextLoader.class)
public class CoreScrapingTest {

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
//		System.out.println(movie.toString());
	}

}
