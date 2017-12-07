package server.project.Cinema.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	List <Movie> findByTitle(String title);
	
	//i guess i don't need these
	/*List <Movie> findByGenre(Genre genre);
	List <Movie> findByCountryName(String countryname);*/


}
