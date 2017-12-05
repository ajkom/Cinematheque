package server.project.Cinema;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import server.project.Cinema.domain.CountryRepository;
import server.project.Cinema.domain.GenreRepository;
import server.project.Cinema.domain.Movie;
import server.project.Cinema.domain.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTests {
	
	@Autowired
	private MovieRepository mrepository;
	@Autowired
	private CountryRepository crepository;
	@Autowired
	private GenreRepository grepository;

	@Test
	public void findByTitleShouldReturnMovie() {
		List<Movie> movies = mrepository.findByTitle("Pink Floyd: The Wall");
		
		assertThat(movies).hasSize(1);
		assertThat(movies.get(0).getYear()).isEqualTo(1982);
	}
	
	@Test
	public void createNewMovie() {
		Movie movie = new Movie("The Devil's Advocate", 1997, crepository.findByName("USA").get(0),
				"Taylor Hackford", grepository.findByName("thriller").get(0), "https://images-na.ssl-images-amazon.com/images/M/MV5BM2M2MDJhMDgtMmJkYy00MTgzLTkyZTktODM5NzE1MWUyNDA4XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_SY1000_CR0,0,703,1000_AL_.jpg", "");
		mrepository.save(movie);
		assertThat(movie.getId()).isNotNull();
	}
	
	@Test
	public void deleteMovie() {
		List<Movie> movies = mrepository.findByTitle("Pink Floyd: The Wall");
		Long id = movies.get(0).getId();
		
		mrepository.delete(id);
		assertThat(mrepository.findByTitle("Pink Floyd: The Wall")).hasSize(0);
	}
	

}
