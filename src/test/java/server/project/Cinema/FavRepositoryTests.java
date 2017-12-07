package server.project.Cinema;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import server.project.Cinema.domain.Fav;
import server.project.Cinema.domain.FavRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FavRepositoryTests {

		@Autowired
	    private FavRepository frepository;
    
	    @Test
	    public void createNewFav() {
	    	Fav fav = new Fav(1,8);
	    	frepository.save(fav);
	    	assertThat(fav.getId()).isNotNull();
	    }
	    
	    // test search methods
	    @Test
	    public void findByUserIdShouldReturnMovieId() {
	    	List <Fav> favs = frepository.findByUserid((long) 2);
	    	
	    	assertThat(favs).hasSize(1);
	    	assertThat(favs.get(0).getMovieid()).isEqualTo(5);
	    }
	    
	    @Test
	    public void findByMovieIdShouldReturnUserId() {
	    	List <Fav> favs = frepository.findByMovieid((long) 5);
	    	
	    	assertThat(favs).hasSize(1);
	    	assertThat(favs.get(0).getUserid()).isEqualTo(2);
	    }

	}