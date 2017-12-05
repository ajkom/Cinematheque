package server.project.Cinema;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import server.project.Cinema.domain.Country;
import server.project.Cinema.domain.CountryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryRepositoryTests {
	
	@Autowired CountryRepository crepository;
	
	@Test
	public void findByNameShouldReturnId() {
		List <Country> countries = crepository.findByName("UK");
		
		assertThat(countries).hasSize(1);
        assertThat(countries.get(0).getCountryid()).isEqualTo(1);
	}
	
	 @Test
	    public void createNewCountry() {
	    	Country country = new Country("Germany");
	    	crepository.save(country);
	    	assertThat(country.getCountryid()).isNotNull();
	    }
	    

}
