package com.jjimenez.filmaffinity.test.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.jjimenez.filmaffinity.PeliculaResumen;
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

	/*
	El mejor ejemplo es: https://www.filmaffinity.com/es/film486640.html
	 */
	/*
	https://medium.com/el-acordeon-del-programador/convertir-objetos-java-a-json-y-de-regreso-1077500d78f7
	https://jarroba.com/gson-json-java-ejemplos/
	 */

	@Test
	public void testGetMovieByName() throws IOException, NotEstablishedConnectionException{
		Movie movie = Search.getMovieByName("gas");
		Validate.notNull(movie);
		System.out.println(movie.toString());
	}

	@Test
	public void writeJson() throws Exception {
		Movie movie = Search.getMovieById(486640L);
		Validate.notNull(movie);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = new FileWriter("C:/Aprendizaje TFM/prueba.json");
		gson.toJson(movie,writer);
		writer.close();
	}

	@Test
	public void writeJsonList() throws Exception {
		Movie movie = Search.getMovieById(486640L);
		Validate.notNull(movie);
		Movie movie2 = Search.getMovieById(515024L);
		Validate.notNull(movie2);
		Movie movie3 = Search.getMovieById(714710L);
		Validate.notNull(movie3);
		List<Movie> peliculas = new ArrayList<Movie>();
		peliculas.add(movie);
		peliculas.add(movie2);
		peliculas.add(movie3);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = new FileWriter("C:/Aprendizaje TFM/prueba.json");
		gson.toJson(peliculas,writer);
		writer.close();
	}

	@Test
	public void readJson() throws Exception {
		String path = "C:/Aprendizaje TFM/jsonchulo.json";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Gson gson = new Gson();
		List<PeliculaResumen> lista;
		lista = gson.fromJson(bufferedReader,new TypeToken<List<PeliculaResumen>>(){}.getType());
		System.out.println(Arrays.toString(lista.toArray()));
		System.out.println(lista.get(0)+" es un "+lista.get(0).getClass());
		System.out.println(lista.get(0).getId()+" es un "+lista.get(0).getId().getClass());
		System.out.println(lista.get(0).getNota()+" es un "+lista.get(0).getNota().getClass());

		////=====Hasta aquí ya tengo leído el JSON

		List<Movie> listapeliculas = new ArrayList<Movie>();
		for(PeliculaResumen peli : lista) {
			Movie movie = Search.getMovieById(peli.getId());
			listapeliculas.add(movie);
		}
		Gson gsonW = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = new FileWriter("C:/Aprendizaje TFM/pruebaOK.json");
		gsonW.toJson(listapeliculas,writer);
		writer.close();

	}
	
	@Test
	public void testGetMovieById() throws IOException, NotEstablishedConnectionException{
		Movie movie = Search.getMovieById(486640L);
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
