package server.project.Cinema.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genreid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
	private List<Movie> movies;
	
	public Genre() {
	}
	
	public Genre(String name){
		super();
		this.name=name;
	}

	public Long getGenreid() {
		return genreid;
	}

	public void setGenreid(Long genreid) {
		this.genreid = genreid;
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
	public String toString(){
		return "Genre [genreid="+genreid+", name="+name+"]";
	}

}
