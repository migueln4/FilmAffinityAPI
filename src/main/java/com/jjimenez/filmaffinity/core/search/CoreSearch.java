package com.jjimenez.filmaffinity.core.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jjimenez.filmaffinity.core.AbstractCore;
import com.jjimenez.filmaffinity.core.ResponseEntity;
import com.jjimenez.filmaffinity.entity.Cast;
import com.jjimenez.filmaffinity.entity.Director;
import com.jjimenez.filmaffinity.entity.Movie;
import com.jjimenez.filmaffinity.entity.ProxyHTTP;
import com.jjimenez.filmaffinity.exception.CastNotFoundException;
import com.jjimenez.filmaffinity.exception.DirectorNotFoundException;
import com.jjimenez.filmaffinity.exception.MovieNotFoundException;
import com.jjimenez.filmaffinity.exception.NotEstablishedConnectionException;

/**
 * Implements the main methods to access to information of the search page
 * 
 * <p>
 * This class is only used internally.
 * </p>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
public class CoreSearch extends AbstractCore {

	private CoreSearch() {
	}

	/**
	 * Create an instance of CoreSearch
	 * 
	 * @return <em>CoreSearch</em> instance
	 */
	protected static CoreSearch getInstance() {
		return new CoreSearch();
	}

	/**
	 * Search a film by means of a name
	 * 
	 * @param name
	 *            to search
	 * @param proxy
	 *            <em>null</em> if not use proxy, either, {@link ProxyHTTP}
	 * @return {@link Movie} founded
	 * @throws MovieNotFoundException
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	protected Movie obtainMovie(final String name, final ProxyHTTP proxy)
			throws MovieNotFoundException, IOException, NotEstablishedConnectionException {

		Validate.notEmpty(name, String.format(ConstantsSearch.MESSAGE_NOT_EMPTY, "Name"));
		AbstractCore.proxy = proxy;

		final Long id = obtaindID(name);
		if (id == null)
			throw new MovieNotFoundException(String.format(ConstantsSearch.MESSAGE_MOVIE_NOT_FOUND_EXCEPTION, name));

		Movie movie = obtainMetadata(id);
		movie = obtainImages(movie);
		movie = obtainTrailers(movie);

		return movie;
	}

	/**
	 * Search a film by means of an id
	 * 
	 * @param id
	 *            to search
	 * @param proxy
	 *            <em>null</em> if not use proxy, either, {@link ProxyHTTP}
	 * @return {@link Movie} founded
	 * @throws MovieNotFoundException
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	protected Movie obtainMovie(final Long id, final ProxyHTTP proxy)
			throws IOException, NotEstablishedConnectionException {

		AbstractCore.proxy = proxy;
		Movie movie = obtainMetadata(id);
		movie = obtainImages(movie);
		movie = obtainTrailers(movie);

		return movie;
	}

	/**
	 * Search a director by means of a name and its {@link Movie}
	 * 
	 * @param name
	 *            of director
	 * @param proxy
	 *            <em>null</em> if not use proxy, either, {@link ProxyHTTP}
	 * @return {@link Director} founded
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	protected Director obtainDirector(final String name, final ProxyHTTP proxy)
			throws IOException, NotEstablishedConnectionException {
		AbstractCore.proxy = proxy;
		Validate.notEmpty(name, String.format(ConstantsSearch.MESSAGE_NOT_EMPTY, "Name"));

		List<Movie> movieList = obtainMovieListByName(name, TypeSearch.DIRECTOR.getTypeSearch());
		Director director = Director.getInstance();
		director.setName(name);
		director.setMovieList(movieList);

		return director;
	}

	/**
	 * Search an actor/actress by means of a name and its {@link Movie}
	 * 
	 * @param name
	 *            of an actor/actress
	 * @param proxy
	 *            <em>null</em> if not use proxy, either, {@link ProxyHTTP}
	 * @return {@link Cast} founded
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	protected Cast obtainCast(final String name, final ProxyHTTP proxy)
			throws IOException, NotEstablishedConnectionException {
		AbstractCore.proxy = proxy;
		Validate.notEmpty(name, String.format(ConstantsSearch.MESSAGE_NOT_EMPTY, "Name"));

		List<Movie> movieList = obtainMovieListByName(name, TypeSearch.CAST.getTypeSearch());
		Cast cast = Cast.getInstance();
		cast.setName(name);
		cast.setMovieList(movieList);

		return cast;
	}

	/**
	 * Through a name, obtain its ID
	 * 
	 * @param name
	 *            to obtain its ID
	 * @return ID
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	private Long obtaindID(final String name) throws IOException, NotEstablishedConnectionException {

		final String URL = ConstantsSearch.URL_BASE
				.concat(String.format(ConstantsSearch.URL_SEARCH, name, TypeSearch.TITLE.getTypeSearch()));

		ResponseEntity response = search(URL, name, TypeSearch.TITLE.getTypeSearch());

		cookies.putAll(response.getCookies());
		Document document = response.getResponse().parse();

		List<BasicMovieEntity> listBasicObject = new ArrayList<BasicMovieEntity>();
		String id, title;
		BasicMovieEntity basicMovie = null;
		for (Element element : document.select(".movie-card.movie-card-1 > div.mc-poster > a[title]")) {
			id = element.parent().parent().attr("data-movie-id");
			title = element.attr("title");

			basicMovie = BasicMovieEntity.getInstance();
			basicMovie.setId(id);
			basicMovie.setTitle(title);

			listBasicObject.add(basicMovie);
		}

		Long idFinal = null;

		if (listBasicObject.isEmpty()) {
			Elements _idElement = document.select("div.rate-movie-box[data-movie-id]");
			String idElement = !_idElement.isEmpty() ? _idElement.first().attr("data-movie-id") : StringUtils.EMPTY;
			idFinal = !idElement.equals(StringUtils.EMPTY) ? Long.parseLong(idElement) : null;
		} else {
			idFinal = UtilsSearch.getId(listBasicObject, name);
		}

		return idFinal;
	}

	/**
	 * Through ID, obtain all metadata from the first tab of the web
	 * 
	 * @param id
	 *            of the film
	 * @return {@link Movie} with the metadata
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	private Movie obtainMetadata(final Long id) throws IOException, NotEstablishedConnectionException {
		final String URL = ConstantsSearch.URL_BASE.concat(ConstantsSearch.URL_FILM).concat(id.toString())
				.concat(".html");

		ResponseEntity response = accessToTabMovie(URL);

		cookies.putAll(response.getCookies());
		Document document = response.getResponse().parse();

		Movie movie = Movie.getInstance();
		// ID
		movie.setId(id);
		// ORIGINAL TITLE
		Elements _title = document.select("#mt-content-cell > div > h1");
		String title = !_title.isEmpty() ? _title.first().text() : StringUtils.EMPTY;
		movie.setOriginalTitle(title);
		// DEFAULT IMAGE
		Elements _image = document.select("#movie-main-image-container > a");
		String imagePath = !_image.isEmpty() ? _image.first().attr("href") : StringUtils.EMPTY;
		movie.setImageDefault(imagePath);
		// RATIO
		Elements _ratio = document.select("#movie-rat-avg");
		String ratio = !_ratio.isEmpty() ? _ratio.first().attr("content") : StringUtils.EMPTY;
		movie.setRated(StringUtils.isEmpty(ratio) ? null : Double.valueOf(ratio));
		// ORIGINAL TITLE
		Elements _originalTitle = document.select("dd");
		String originalTitle = !_originalTitle.isEmpty()
				? _originalTitle.first().text().replaceAll("aka", StringUtils.EMPTY) : StringUtils.EMPTY;
		movie.setOriginalTitle(originalTitle);
		// YEAR
		Elements _year = document.select("dd[itemprop=datePublished]");
		String year = !_year.isEmpty() ? _year.first().text() : StringUtils.EMPTY;
		movie.setYear(year);
		// DURATION
		Elements _duration = document.select("dd[itemprop=duration]");
		String duration = !_duration.isEmpty() ? _duration.first().text().split(" ")[0] : StringUtils.EMPTY;
		movie.setDuration(StringUtils.isEmpty(duration) ? null : Long.parseLong(duration));
		// COUNTRY
		Elements _country = document.select("dd > span[id=country-img] > img");
		String country = !_country.isEmpty() ? _country.first().attr("title") : StringUtils.EMPTY;
		movie.setCountry(country);
		// COUNTRY FLAG
		String countryFlag = country != StringUtils.EMPTY
				? ConstantsSearch.URL_BASE_EN.concat(_country.first().attr("src")) : StringUtils.EMPTY;
		movie.setFlagCountry(countryFlag);
		// DIRECTORS
		Elements _directors = document.select("dd[class=directors] > span");
		int i = 0;
		String[] directors = new String[_directors.size()];
		for (Element element : _directors) {
			directors[i] = element.text().replaceAll(",", StringUtils.EMPTY);
			i++;
		}
		movie.setDirector(directors);
		// SCRIPTS
		Elements _scripts = document.select("dt:contains(Guión) + dd > div > span");
		i = 0;
		String[] scripts = new String[_scripts.size()];
		for (Element element : _scripts) {
			scripts[i] = element.text().replaceAll(",", StringUtils.EMPTY);
			i++;
		}
		movie.setScript(scripts);
		// MUSIC
		Elements _music = document.select("dt:contains(Música) + dd > div > span");
		i = 0;
		String[] music = new String[_music.size()];
		for (Element element : _music) {
			music[i] = element.text().replaceAll(",", StringUtils.EMPTY);
			i++;
		}
		movie.setMusic(music);
		// PHOTOGRAPHY
		Elements _photography = document.select("dt:contains(Fotografía) + dd > div > span");
		i = 0;
		String[] photography = new String[_photography.size()];
		for (Element element : _photography) {
			photography[i] = element.text().replaceAll(",", StringUtils.EMPTY);
			i++;
		}
		movie.setPhotography(photography);
		// CAST
		Elements _cast = document.select("dt:contains(Reparto) + dd > span");
		i = 0;
		String[] cast = new String[_cast.size()];
		for (Element element : _cast) {
			cast[i] = element.text().replaceAll(",", StringUtils.EMPTY);
			i++;
		}
		movie.setCast(cast);
		// PRODUCERS
		Elements _producer = document.select("dt:contains(Productora) + dd > div > span");
		i = 0;
		String[] producer = new String[_producer.size()];
		for (Element element : _producer) {
			producer[i] = element.text().replaceAll(",", StringUtils.EMPTY);
			i++;
		}
		movie.setProducer(producer);
		// GENRES
		Elements _genre = document.select("dt:contains(Género) + dd > span");
		i = 0;
		String[] genre = new String[_genre.size()];
		for (Element element : _genre) {
			genre[i] = element.text().replaceAll(",", StringUtils.EMPTY);
			i++;
		}
		movie.setGenre(genre);
		// GROUPS
		Elements _groups = document.select("dt:contains(Grupos) + dd > span");
		i = 0;
		String[] groups = new String[_groups.size()];
		for (Element element : _groups) {
			groups[i] = element.text().replaceAll(",", StringUtils.EMPTY);
			i++;
		}
		movie.setGroups(groups);
		// SINOPSIS
		Elements _sinopsis = document.select("dd[itemprop=description]");
		String sinopsis = !_sinopsis.isEmpty()
				? _sinopsis.first().text().replace("(FILMAFFINITY)", StringUtils.EMPTY).trim() : StringUtils.EMPTY;
		movie.setSynopsis(sinopsis);

		return movie;
	}

	/**
	 * Through {@link Movie}, obtain all trailers from the trailers tab
	 * 
	 * @param {@link
	 * 			Movie}
	 * @return {@link Movie} with the trailers setted
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	private Movie obtainTrailers(Movie movie) throws IOException, NotEstablishedConnectionException {
		Validate.notNull(movie, String.format(ConstantsSearch.MESSAGE_NOT_NULL, "Movie"));

		final String URL = ConstantsSearch.URL_BASE.concat(ConstantsSearch.URL_TRAILERS)
				.concat(movie.getId().toString());

		ResponseEntity response = accessToTabMovie(URL);

		cookies.putAll(response.getCookies());
		Document document = response.getResponse().parse();

		// TRAILERS
		Elements _trailers = document.select("iframe");
		int i = 0;
		String[] trailers = new String[_trailers.size()];
		for (Element element : _trailers) {
			trailers[i] = element.attr("src");
			i++;
		}
		movie.setTrailers(trailers);

		return movie;
	}

	/**
	 * Through {@link Movie}, obtain all images from the images tab
	 * 
	 * @param {@link
	 * 			Movie}
	 * @return {@link Movie} with the images setted
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	private Movie obtainImages(Movie movie) throws IOException, NotEstablishedConnectionException {
		Validate.notNull(movie, String.format(ConstantsSearch.MESSAGE_NOT_NULL, "Movie"));

		final String URL = ConstantsSearch.URL_BASE.concat(ConstantsSearch.URL_IMAGES).concat(movie.getId().toString());

		ResponseEntity response = accessToTabMovie(URL);

		cookies.putAll(response.getCookies());
		Document document = response.getResponse().parse();

		// IMAGES
		Elements _images = document.select(".colorbox-image > a");
		int i = 0;
		String[] images = new String[_images.size()];
		for (Element element : _images) {
			images[i] = element.attr("href");
			i++;
		}
		movie.setImages(images);

		return movie;
	}

	/**
	 * By means of a name and type of search (director or cast)
	 * 
	 * @param name of the person
	 * @param type {@link TypeSearch} of search
	 * @return List<{@link Movie} with all Movies
	 * @throws DirectorNotFoundException
	 * @throws IOException
	 * @throws NotEstablishedConnectionException
	 */
	private List<Movie> obtainMovieListByName(final String name, final String type)
			throws DirectorNotFoundException, IOException, NotEstablishedConnectionException {

		final String URL = ConstantsSearch.URL_BASE.concat(String.format(ConstantsSearch.URL_SEARCH, name, type));

		ResponseEntity response = search(URL, name, type);

		cookies.putAll(response.getCookies());
		Document document = response.getResponse().parse();

		Elements _movies = document.select("div[data-movie-id]");
		List<Movie> movieList = null;
		if (_movies.size() > 0)
			movieList = new ArrayList<Movie>();
		else{
			if("director".equals(type)){
				throw new DirectorNotFoundException(String.format(ConstantsSearch.MESSAGE_DIRECTOR_NOT_FOUND_EXCEPTION, name));
			}else{
				throw new CastNotFoundException(String.format(ConstantsSearch.MESSAGE_CAST_NOT_FOUND_EXCEPTION, name));
			}
		}
			
			

		for (Element element : _movies) {
			String _id = element.attr("data-movie-id");
			Long id = _id != null ? Long.valueOf(_id) : null;
			Movie movie = obtainMovie(id, proxy);
			movieList.add(movie);
		}

		return movieList;
	}

}
