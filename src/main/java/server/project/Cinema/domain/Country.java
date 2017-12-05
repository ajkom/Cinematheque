package server.project.Cinema.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long countryid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	private List<Movie> movies;

	public Country() {
	}

	public Country(String name) {
		super();
		this.name = name;
	}

	

	public Long getCountryid() {
		return countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Country [countryid=" + countryid + ", name=" + name + "]";
	}

	
	

}
