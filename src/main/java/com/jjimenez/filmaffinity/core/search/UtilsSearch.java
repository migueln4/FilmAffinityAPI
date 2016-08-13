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
			int distanceComputed = computeLevenshteinDistance(StringUtils.deleteWhitespace(name.toLowerCase()),
					StringUtils.deleteWhitespace(basic.getTitle().toLowerCase()));
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

	/**
	 * Obtain the minimum number between three numbers
	 * 
	 * @param a
	 *            first number
	 * @param b
	 *            second number
	 * @param c
	 *            third number
	 * @return the minimum number
	 */
	private static int minimum(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	/**
	 * Through algorithm Levenshtein Distance, calculate how many changes are
	 * necessary to obtain the same string
	 * 
	 * @param lhs
	 *            first string
	 * @param rhs
	 *            second string
	 * @return number of changes
	 */
	private static int computeLevenshteinDistance(CharSequence lhs, CharSequence rhs) {
		if (lhs.equals(rhs))
			return 0;

		int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];

		for (int i = 0; i <= lhs.length(); i++)
			distance[i][0] = i;
		for (int j = 1; j <= rhs.length(); j++)
			distance[0][j] = j;

		for (int i = 1; i <= lhs.length(); i++)
			for (int j = 1; j <= rhs.length(); j++)
				distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1,
						distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));

		return distance[lhs.length()][rhs.length()];
	}

}
