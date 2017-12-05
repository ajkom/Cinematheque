package server.project.Cinema;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import server.project.Cinema.domain.Genre;
import server.project.Cinema.domain.GenreRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GenreRepositoryTests {
	
	@Autowired GenreRepository grepository;

	@Test
	public void findByNameShouldReturnId() {
		List <Genre> genres = grepository.findByName("music");
		
		assertThat(genres).hasSize(1);
        assertThat(genres.get(0).getGenreid()).isEqualTo(4);
	}
	
	@Test
    public void createNewGenre() {
    	Genre genre = new Genre("fantasy");
    	grepository.save(genre);
    	assertThat(genre.getGenreid()).isNotNull();
    }

}
