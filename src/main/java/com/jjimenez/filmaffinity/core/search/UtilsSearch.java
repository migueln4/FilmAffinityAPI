package com.jjimenez.filmaffinity.core.search;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.Validate;

/**
 * Set of methods helps to {@link CoreSearch}
 * 
 * <p>
 * This class is only used internally.
 * </p>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
class UtilsSearch {
	
	/**
	 * Through a list of BasicMovieEntity and one name, search the ID of the
	 * most similar movie (In case there more than one similar movies)
	 * 
	 * @param listBasicObject
	 *            List of {@link BasicMovieEntity}
	 * @param name
	 *            to search
	 * @return ID
	 */
	static Long getId(List<BasicMovieEntity> listBasicObject, String name) {
		Long id = -1L;
		BasicMovieEntity objectMoreEquals = null;
		int distance = Integer.MAX_VALUE;
		Validate.notEmpty(name, String.format(ConstantsSearch.MESSAGE_NOT_EMPTY, "Name"));

		for (BasicMovieEntity basic : listBasicObject) {
			Validate.notEmpty(basic.getTitle(), String.format(ConstantsSearch.MESSAGE_NOT_EMPTY, "Title"));
			int distanceComputed = StringUtils.getLevenshteinDistance(StringUtils.deleteWhitespace(name.toLowerCase()),StringUtils.deleteWhitespace(basic.getTitle().toLowerCase()));
			if (distanceComputed <= distance) {
				objectMoreEquals = basic;
				if (distanceComputed == 0)
					break;
			}
			distance = distanceComputed;
		}

		id = Long.parseLong(objectMoreEquals.getId());

		return id;
	}

}
