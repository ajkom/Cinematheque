package server.project.Cinema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import server.project.Cinema.domain.User;
import server.project.Cinema.domain.Country;
import server.project.Cinema.domain.CountryRepository;
import server.project.Cinema.domain.FavRepository;
import server.project.Cinema.domain.Genre;
import server.project.Cinema.domain.GenreRepository;
import server.project.Cinema.domain.Movie;
import server.project.Cinema.domain.MovieRepository;
import server.project.Cinema.domain.UserRepository;
import server.project.Cinema.domain.Fav;

@SpringBootApplication
public class CinemaApplication {
	private static final Logger log = LoggerFactory.getLogger(CinemaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Bean
	public CommandLineRunner movieDemo(MovieRepository mrepository, GenreRepository grepository, CountryRepository crepository, UserRepository urepository, FavRepository frepository) {
		return (args) -> {
			// genres
			grepository.save(new Genre("action"));
			grepository.save(new Genre("drama"));
			grepository.save(new Genre("comedy"));
			grepository.save(new Genre("music"));
			grepository.save(new Genre("fantasy"));
			grepository.save(new Genre("biography"));
			grepository.save(new Genre("history"));

			
			// countries
			crepository.save(new Country("UK"));
			crepository.save(new Country("USA"));
			crepository.save(new Country("Germany"));
			crepository.save(new Country("New Zealand"));
			crepository.save(new Country("Australia"));
			crepository.save(new Country("Spain"));


			// movies
			mrepository.save(new Movie("Kingsman: The Secret Service", 2014, crepository.findByName("UK").get(0), "Matthew Vaughn",
				grepository.findByName("action").get(0),
				"https://images-na.ssl-images-amazon.com/images/M/MV5BMTkxMjgwMDM4Ml5BMl5BanBnXkFtZTgwMTk3NTIwNDE@._V1_SY1000_CR0,0,675,1000_AL_.jpg",
				"A spy organization recruits an unrefined, but promising street kid into the agency's ultra-competitive training program, just as a global threat emerges from a twisted tech genius."
				+ "\n Taron Egerton, Colin Firth, Mark Strong, Samuel L. Jackson"));
			
			mrepository.save(new Movie("Pink Floyd: The Wall", 1982, crepository.findByName("UK").get(0), "Alan Parker",
				grepository.findByName("music").get(0),
				"https://images-na.ssl-images-amazon.com/images/M/MV5BZDhlZTYxOTYtYTk3Ny00ZDljLTk3ZmItZTcxZWU5YTIyYmFkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,643,1000_AL_.jpg",
				"A confined but troubled rock star descends into madness in the midst of his physical and social isolation from everyone. Written by Roger Waters."+"<br>"
				+ "\n Bob Geldof, Christine Hargreaves, James Laurenson "));
			
			mrepository.save(new Movie("Rebel Without a Cause", 1955, crepository.findByName("USA").get(0), "Nicholas Ray",
				grepository.findByName("drama").get(0),
				"https://images-na.ssl-images-amazon.com/images/M/MV5BMjYyZjQwMTctYTU0NC00YTkwLTgyZjItMWFlODE0MTNmYTczXkEyXkFqcGdeQXVyMjU5OTg5NDc@._V1_SY1000_CR0,0,753,1000_AL_.jpg",
				"A rebellious young man with a troubled past comes to a new town, finding friends and enemies."
				+ "\nJames Dean, Natalie Wood, Sal Mineo"));
			
			mrepository.save(new Movie("Cry-Baby", 1990, crepository.findByName("USA").get(0), "John Waters", grepository.findByName("comedy").get(0),
				"https://images-na.ssl-images-amazon.com/images/M/MV5BMjAyODA5OTc3Ml5BMl5BanBnXkFtZTgwMTgxNTYxMTE@._V1_.jpg",
				"In 1950s Baltimore, a bad boy with a heart of gold wins the love of a good girl, whose boyfriend sets out for revenge."
				+ "\nJohnny Depp, Ricki Lake, Amy Locane"
					));
			
			mrepository.save(new Movie("20 000 Days on Earth", 2014, crepository.findByName("UK").get(0), "Iain Forsyth, Jane Pollard",
				grepository.findByName("music").get(0),
				"https://images-na.ssl-images-amazon.com/images/M/MV5BMTQzOTI2NjkxM15BMl5BanBnXkFtZTgwNjg4MDgyMjE@._V1_SY1000_CR0,0,677,1000_AL_.jpg",
				"Writer and musician Nick Cave marks his 20,000th day on the planet Earth."
				+ "\n Nick Cave, Susie Bick, Warren Ellis, Blixa Bargeld"
				));
			
			mrepository.save(new Movie("August Rush", 2007, crepository.findByName("USA").get(0), "Kirsten Sheridan",
				grepository.findByName("music").get(0),
				"https://images-na.ssl-images-amazon.com/images/M/MV5BNDIwNjE3NjU2NV5BMl5BanBnXkFtZTcwNTQ2NjQzMw@@._V1_SY1000_CR0,0,679,1000_AL_.jpg",
				"A drama with fairy tale elements, where an orphaned musical prodigy uses his gift as a clue to finding his birth parents."
				+ "\nFreddie Highmore, Jonathan Rhys Meyers, Robin Williams"));
			
			mrepository.save(new Movie("Der Himmel über Berlin", 1987, crepository.findByName("Germany").get(0), "Wim Wenders",
				grepository.findByName("drama").get(0),
				"https://images-na.ssl-images-amazon.com/images/M/MV5BMzMxZjUzOGQtOTFlOS00MzliLWJhNTUtOTgyNzYzMWQ2YzhmXkEyXkFqcGdeQXVyNjQ2MjQ5NzM@._V1_SY1000_CR0,0,693,1000_AL_.jpg",
				"An angel tires of overseeing human activity and wishes to become human when he falls in love with a mortal."
				+ "\n Bruno Ganz, Solveig Dommartin, Otto Sander"));
			
			mrepository.save(new Movie("Russendisko", 2012, crepository.findByName("Germany").get(0), "Oliver Ziegenbalg",
					grepository.findByName("comedy").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BMjE3OTUwMzIyNF5BMl5BanBnXkFtZTgwNTg1OTkyMTE@._V1_.jpg",
					"Three young Russian friends move from Moscow to Berlin in a lucky wave of emigration right after the fall of the Berlin wall."
					+ "\nMatthias Schweighöfer, Friedrich Mücke, Christian Friedel"));
			
			mrepository.save(new Movie("Mad Max: Fury Road", 2015, crepository.findByName("Australia").get(0), "George Miller",
					grepository.findByName("action").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BYTFmMDkzMDUtMjY2NC00YTY3LTliYjYtNGY3ZWM4NTk4OWFlXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SY1000_CR0,0,707,1000_AL_.jpg",
					"A woman rebels against a tyrannical ruler in postapocalyptic Australia in search for her home-land with the help of a group of female prisoners, a psychotic worshipper, and a drifter named Max."
					+ "\n  Tom Hardy, Charlize Theron, Nicholas Hoult"));
			
			mrepository.save(new Movie("Eddie the Eagle", 2016, crepository.findByName("UK").get(0), "Dexter Fletcher",
					grepository.findByName("biography").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxOTc5MTU1NF5BMl5BanBnXkFtZTgwODYyNTA1NzE@._V1_.jpg",
					"The story of Eddie Edwards, the notoriously tenacious British underdog ski jumper who charmed the world at the 1988 Winter Olympics."
					+ "\n  Taron Egerton, Hugh Jackman, Tom Costello"));
			
			mrepository.save(new Movie("Troy", 2004, crepository.findByName("USA").get(0), "Wolfgang Petersen",
					grepository.findByName("history").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BMTk5MzU1MDMwMF5BMl5BanBnXkFtZTcwNjczODMzMw@@._V1_SY1000_CR0,0,677,1000_AL_.jpg",
					"An adaptation of Homer's great epic, the film follows the assault on Troy by the united Greek forces and chronicles the fates of the men involved."
					+ "\n Brad Pitt, Eric Bana, Orlando Bloom"));
			
			mrepository.save(new Movie("Alexander", 2004, crepository.findByName("USA").get(0), "Oliver Stone",
					grepository.findByName("history").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BMTczNDMzMzA2MV5BMl5BanBnXkFtZTYwNTY5OTQ3._V1_.jpg",
					"Alexander, the King of Macedonia and one of the greatest army leaders in the history of warfare, conquers much of the known world."
					+ "\n  Colin Farrell, Anthony Hopkins, Angelina Jolie, Jared Leto"));
			
			mrepository.save(new Movie("El laberinto del fauno", 2006, crepository.findByName("Spain").get(0), "Guillermo del Toro",
					grepository.findByName("fantasy").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BMjI0OTA4ODUxOV5BMl5BanBnXkFtZTcwNjYzMTkwOA@@._V1_.jpg",
					"In the falangist Spain of 1944, the bookish young stepdaughter of a sadistic army officer escapes into an eerie but captivating fantasy world."
					+ "\n Ivana Baquero, Ariadna Gil, Sergi López"));
			
			mrepository.save(new Movie("The Theory of Everything", 2014, crepository.findByName("UK").get(0), "James Marsh",
					grepository.findByName("biography").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BMTU1NzEzNjI3OF5BMl5BanBnXkFtZTgwNzI3MjA0MjE@._V1_.jpg",
					"A look at the relationship between the famous physicist Stephen Hawking and his wife. Based on the memoirs of Jane Hawking"
					+ "\n Eddie Redmayne, Felicity Jones, Tom Prior"));
			
			mrepository.save(new Movie("Kill Your Darlings", 2013, crepository.findByName("USA").get(0), "John Krokidas",
					grepository.findByName("biography").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BMjA4MzAxMTE1N15BMl5BanBnXkFtZTgwMDY2OTIxMDE@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
					"A murder in 1944 draws together the great poets of the beat generation: Allen Ginsberg, Jack Kerouac and William Burroughs."
					+ "\n Daniel Radcliffe, Dane DeHaan, Michael C. Hall"));
			
			mrepository.save(new Movie("The Lord of the Rings: The Fellowship of the Ring", 2001, crepository.findByName("New Zealand").get(0), "Peter Jackson",
					grepository.findByName("fantasy").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_SY999_CR0,0,673,999_AL_.jpg",
					"A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle Earth from the Dark Lord Sauron."
					+ "\n Elijah Wood, Ian McKellen, Viggo Mortensen, Orlando Bloom"));
			
			mrepository.save(new Movie("Star Wars: Episode IV - A New Hope", 1977, crepository.findByName("USA").get(0), "George Lucas",
					grepository.findByName("fantasy").get(0),
					"https://images-na.ssl-images-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,643,1000_AL_.jpg",
					"Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy and to rescue Princess Leia from the evil Darth Vader."
					+ "\n Mark Hamill, Harrison Ford, Carrie Fisher"));
			
			// users admin/admin user/user
			User user1 = new User("user", "$2a$06$OZVm2CzF.vx1bb2XZcvSReBIUJOw77UIpEY8GFx9.HGL3gyhgcaSa", "USER");
			User user2 = new User("admin", "$2a$06$fClI3fmnqZPuWcB7Ac40bOwku33v124kdtl2wSwcUiguX.STIYpbG", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			// check favs
			frepository.save(new Fav(2, 5));
		
			log.info("fetch fav");
			for (Fav fav : frepository.findAll()) {
				log.info(fav.toString());
				
			}
			
			log.info("fetch all countries");
			for (Country country : crepository.findAll()) {
				log.info(country.toString());
			}
			
			log.info("fetch all movies");
			for (Movie movie : mrepository.findAll()) {
				log.info(movie.toString());
			}
			
			log.info("fetch all genres");
			for (Genre genre : grepository.findAll()) {
				log.info(genre.toString());
			}

		};
	}
}
