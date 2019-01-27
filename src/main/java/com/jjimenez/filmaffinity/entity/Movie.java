package com.jjimenez.filmaffinity.entity;

import java.util.Arrays;

/**
 * Entity of movie from <em>www.filmaffinity.com/es</em>
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 *
 */
public class Movie {

	//private Long id;
	private String title;
	private String originalTitle;
	private Integer year;
	private Long duration;
	//private String flagCountry;
	private String country;
	private String[] director;
	private String[] script;
	private String[] music;
	private String[] photography;
	private String[] cast;
	//private String[] producer;
	private String[] genre;
	//private String[] groups;
	private String synopsis;
	//private Double rated;
	//private String[] images;
	private String imageDefault;
	//private String[] trailers;
	private String[] subgenre;

	private Movie() {}
	
	public static Movie getInstance(){
		return new Movie();
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

/*	public String getFlagCountry() {
		return flagCountry;
	}

	public void setFlagCountry(String flagCountry) {
		this.flagCountry = flagCountry;
	}*/

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String[] getDirector() {
		return director;
	}

	public void setDirector(String[] director) {
		this.director = director;
	}

	public String[] getScript() {
		return script;
	}

	public void setScript(String[] script) {
		this.script = script;
	}

	public String[] getMusic() {
		return music;
	}

	public void setMusic(String[] music) {
		this.music = music;
	}

	public String[] getPhotography() {
		return photography;
	}

	public void setPhotography(String[] photography) {
		this.photography = photography;
	}

	public String[] getCast() {
		return cast;
	}

	public void setCast(String[] cast) {
		this.cast = cast;
	}

/*	public String[] getProducer() {
		return producer;
	}

	public void setProducer(String[] producer) {
		this.producer = producer;
	}*/

	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public String[] getSubgenre() {return subgenre;}

	public void setSubgenre(String[] subgenre) {this.subgenre = subgenre;}

/*	public String[] getGroups() {
		return groups;
	}

	public void setGroups(String[] groups) {
		this.groups = groups;
	}*/

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

/*	public Double getRated() {
		return rated;
	}

	public void setRated(Double rated) {
		this.rated = rated;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String[] getTrailers() {
		return trailers;
	}

	public void setTrailers(String[] trailers) {
		this.trailers = trailers;
	}*/

	public String getImageDefault() {
		return imageDefault;
	}

	public void setImageDefault(String imageDefault) {
		this.imageDefault = imageDefault;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cast);
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + Arrays.hashCode(director);
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
/*		result = prime * result + ((flagCountry == null) ? 0 : flagCountry.hashCode());*/
		result = prime * result + Arrays.hashCode(genre);
		result = prime * result + Arrays.hashCode(subgenre);
/*		result = prime * result + Arrays.hashCode(groups);
		result = prime * result + ((id == null) ? 0 : id.hashCode());*/
		result = prime * result + ((imageDefault == null) ? 0 : imageDefault.hashCode());
/*		result = prime * result + Arrays.hashCode(images);*/
		result = prime * result + Arrays.hashCode(music);
		result = prime * result + ((originalTitle == null) ? 0 : originalTitle.hashCode());
		result = prime * result + Arrays.hashCode(photography);
/*		result = prime * result + Arrays.hashCode(producer);
		result = prime * result + ((rated == null) ? 0 : rated.hashCode());*/
		result = prime * result + Arrays.hashCode(script);
		result = prime * result + ((synopsis == null) ? 0 : synopsis.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
/*		result = prime * result + Arrays.hashCode(trailers);*/
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (!Arrays.equals(cast, other.cast))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (!Arrays.equals(director, other.director))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
/*		if (flagCountry == null) {
			if (other.flagCountry != null)
				return false;
		} else if (!flagCountry.equals(other.flagCountry))
			return false;*/
		if (!Arrays.equals(genre, other.genre))
			return false;
		if(!Arrays.equals(subgenre,other.subgenre))
			return false;
/*		if (!Arrays.equals(groups, other.groups))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;*/
		if (imageDefault == null) {
			if (other.imageDefault != null)
				return false;
		} else if (!imageDefault.equals(other.imageDefault))
			return false;
/*		if (!Arrays.equals(images, other.images))
			return false;*/
		if (!Arrays.equals(music, other.music))
			return false;
		if (originalTitle == null) {
			if (other.originalTitle != null)
				return false;
		} else if (!originalTitle.equals(other.originalTitle))
			return false;
		if (!Arrays.equals(photography, other.photography))
			return false;
/*		if (!Arrays.equals(producer, other.producer))
			return false;
		if (rated == null) {
			if (other.rated != null)
				return false;
		} else if (!rated.equals(other.rated))
			return false;*/
		if (!Arrays.equals(script, other.script))
			return false;
		if (synopsis == null) {
			if (other.synopsis != null)
				return false;
		} else if (!synopsis.equals(other.synopsis))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
/*		if (!Arrays.equals(trailers, other.trailers))
			return false;*/
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

/*	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", originalTitle=" + originalTitle + ", year=" + year
				+ ", duration=" + duration + ", flagCountry=" + flagCountry + ", country=" + country + ", director="
				+ Arrays.toString(director) + ", script=" + Arrays.toString(script) + ", music="
				+ Arrays.toString(music) + ", photography=" + Arrays.toString(photography) + ", cast="
				+ Arrays.toString(cast) + ", producer=" + Arrays.toString(producer) + "NÚMERO: "+producer.length + ", genre="
				+ Arrays.toString(genre) + "subgenre=" + Arrays.toString(subgenre) +   ", groups=" + Arrays.toString(groups) + ", synopsis=" + synopsis + ", rated="
				+ rated + ", images=" + Arrays.toString(images) + ", imageDefault=" + imageDefault + ", trailers="
				+ Arrays.toString(trailers) + "]";
	}*/

	@Override
	public String toString() {
		return "Movie [title=" + title + ", originalTitle=" + originalTitle + ", year=" + year
				+ ", duration=" + duration + ", country=" + country + ", director="
				+ Arrays.toString(director) + ", script=" + Arrays.toString(script) + ", music="
				+ Arrays.toString(music) + ", photography=" + Arrays.toString(photography) + ", cast="
				+ Arrays.toString(cast) + ", genre="
				+ Arrays.toString(genre) + "subgenre=" + Arrays.toString(subgenre) +  ", synopsis=" + synopsis + ", imageDefault=" + imageDefault;
	}

	
}
