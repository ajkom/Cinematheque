package server.project.Cinema;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import server.project.Cinema.domain.User;
import server.project.Cinema.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {


		@Autowired
	    private UserRepository urepository;

	    @Test
	    public void findByUsernameShouldReturnRole() {
	        User user = urepository.findByUsername("user");
	        
	        assertThat(user).isNotNull();
	        assertThat(user.getRole()).isEqualTo("USER");
	    }
	    
	    @Test
	    public void createNewUser() {
	    	User user = new User("user2", "password", "ADMIN");
	    	urepository.save(user);
	    	assertThat(user.getUsername()).isNotNull();
	    }

	}

