package server.project.Cinema.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private int year;
	
	@ManyToOne
    @JsonIgnore
	@JoinColumn(name = "countryid")
	private Country country;
	
	
	private String director;
	private String src;
	
	@ManyToOne
    @JsonIgnore
	@JoinColumn(name = "genreid")
	private Genre genre; 
	
	private String description;
	
	
	public Movie(){
	}
	
	public Movie(String title, int year, Country country, String director, Genre genre, String src, String description) {
		super();
		this.title=title;
		this.year=year;
		this.country=country;
		this.director=director;
		this.genre=genre;
		this.src=src;
		this.description=description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", country=" + this.getCountry() +  ", director=" + director + ", src=" + src
				+ ", genre=" + this.getGenre() + "]";
	}


}
