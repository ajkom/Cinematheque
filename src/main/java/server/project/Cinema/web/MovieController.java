package server.project.Cinema.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import server.project.Cinema.domain.Country;
import server.project.Cinema.domain.CountryRepository;
import server.project.Cinema.domain.FavRepository;
import server.project.Cinema.domain.Fav;
import server.project.Cinema.domain.Genre;
import server.project.Cinema.domain.GenreRepository;
import server.project.Cinema.domain.Movie;
import server.project.Cinema.domain.MovieRepository;
import server.project.Cinema.domain.User;
import server.project.Cinema.domain.UserRepository;

@Controller
public class MovieController {
	@Autowired
	private GenreRepository grepository;

	@Autowired
	private MovieRepository mrepository;

	@Autowired
	private FavRepository frepository;

	@Autowired
	private CountryRepository crepository;

	@Autowired
	private UserRepository urepository;

	// login
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	// redirect to login
	@RequestMapping(value = "/")
	public String sendToLogin() {
		return "redirect:login";
	}

	// show all movies
	@RequestMapping(value = "/showall")
	public String showALlMovies(Model model) {
		model.addAttribute("movies", mrepository.findAll());

		// these are for dropdowns
		model.addAttribute("genres", grepository.findAll());
		model.addAttribute("countries", crepository.findAll());

		return "choosemovie";
	}

	// show the movie page by id, description part shown
	@RequestMapping(value = "/movieinfo/{id}")
	public String showMovie(@PathVariable("id") Long movieId, Model model) {
		model.addAttribute("movie", mrepository.findOne(movieId));
		return "movieinfo";
	}

	// show drop down
	@RequestMapping(value = "/choosemovie")
	public String show(Model model) {
		model.addAttribute("genres", grepository.findAll());
		model.addAttribute("countries", crepository.findAll());
		return "choosemovie";
	}

	// show filtered movies
	@RequestMapping(value = "/findmovie", method = RequestMethod.GET)
	public String filterMovies(@RequestParam("country") Long countryId, @RequestParam("genre") Long genreId,
			Model model) {

		Genre genre = grepository.findOne(genreId);
		Country country = crepository.findOne(countryId);

		// get the common movies from two lists (by country and by genre)
		List<Movie> filtered = new ArrayList<Movie>(genre.getMovies());
		filtered.retainAll(country.getMovies());

		model.addAttribute("movies", filtered);

		// these are for dropdowns
		model.addAttribute("genres", grepository.findAll());
		model.addAttribute("countries", crepository.findAll());
		return "choosemovie";
	}

	// add movie to watch list
	@RequestMapping(value = "/addmovie/{id}", method = RequestMethod.GET)
	public String addMovie(@PathVariable("id") Long movieId, Model model, Principal principal) {
		// get user id
		String currUserName = principal.getName();
		User currUser = urepository.findByUsername(currUserName);
		Long currUserId = currUser.getId();

		// create new Fav (class/object linking movies chosen by user to the
		// user id)
		Fav fav = new Fav(currUserId, movieId);

		// check if this movie already exists in this user's list:
		// find the favs that have current movie id and check if user id is also
		// the same
		// could also be done the other way around:
		// frepository.findByUserid(currUserid).size() ...
		Boolean unique = true;
		for (int i = 0; i < frepository.findByMovieid(movieId).size(); i++) {
			if ((frepository.findByMovieid(movieId).get(i).getMovieid() == movieId)
					&& (frepository.findByMovieid(movieId).get(i).getUserid() == currUserId)) {
				unique = false;
			}
		}

		if (unique) {
			frepository.save(fav);
		}
		// these are for dropdowns
		model.addAttribute("genres", grepository.findAll());
		model.addAttribute("countries", crepository.findAll());
		return "redirect:/choosemovie";
	}

	// show userpage
	@RequestMapping(value = "/userpage")
	public String showFavs(Model model, Principal principal) {
		// get current user's id
		String currUserName = principal.getName();
		User currUser = urepository.findByUsername(currUserName);
		Long currUserId = currUser.getId();

		// get the list of favs associated with the curent user
		List<Fav> favList = frepository.findByUserid(currUserId);
		List<Movie> movieList = new ArrayList<Movie>();

		// loop through the list of favs to get the movies and add them to
		// movielist
		for (int i = 0; i < favList.size(); i++) {
			Long movie_id = favList.get(i).getMovieid();
			movieList.add(mrepository.findOne(movie_id));
		}
		model.addAttribute("movies", movieList);
		return "userpage";
	}

	// remove a movie from personal page
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String removeMovie(@PathVariable("id") Long movieId, Model model, Principal principal) {
		// get current user's id
		String currUserName = principal.getName();
		User currUser = urepository.findByUsername(currUserName);
		Long currUserId = currUser.getId();

		// only delete the movie from current user's list
		for (int i = 0; i < frepository.findByMovieid(movieId).size(); i++) {
			if ((frepository.findByMovieid(movieId).get(i).getMovieid() == movieId)
					&& (frepository.findByMovieid(movieId).get(i).getUserid() == currUserId)) {

				frepository.delete(frepository.findByMovieid(movieId).get(i).getId());
			}
		}

		return "redirect:/userpage";
	}

	//----------------------------- ADMIN methods -----------------------------

		// delete a movie (admin)
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteMovie(@PathVariable("id") Long movieId, Model model) {
			mrepository.delete(movieId);
			return "redirect:/choosemovie";
		}

		// edit movie (admin)
		@RequestMapping(value = "/edit/{id}")
		public String editBook(@PathVariable("id") Long movieId, Model model) {
			model.addAttribute("movie", mrepository.findOne(movieId));
			model.addAttribute("genres", grepository.findAll());
			model.addAttribute("countries", crepository.findAll());
			return "editmovie";
		}
		
		// add a movie (admin)
		@RequestMapping(value = "/add")
		public String addMovie(Model model) {
			model.addAttribute("movie", new Movie());
			model.addAttribute("genres", grepository.findAll());
			model.addAttribute("countries", crepository.findAll());
			return "addmovie";
		}

		// save movie (admin)
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Movie movie) {
			mrepository.save(movie);
			return "redirect:/choosemovie";
		}
	
	//----------------------------- REST -----------------------------

	// RESTful service to get all movies
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public @ResponseBody List<Movie> movieListRest() {
		return (List<Movie>) mrepository.findAll();
	}
	
	// REST get movie by id
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
	public @ResponseBody Movie findMovieRest(@PathVariable("id") Long movieId) {
		return mrepository.findOne(movieId);
	}

	// RESTful service to get all genres
	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public @ResponseBody List<Genre> genreListRest() {
		return (List<Genre>) grepository.findAll();
	}

	// REST get genre by id
	@RequestMapping(value = "/genre/{id}", method = RequestMethod.GET)
	public @ResponseBody Genre findGenreRest(@PathVariable("id") Long genreId) {
		return grepository.findOne(genreId);
	}

	// RESTful service to get all countries
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public @ResponseBody List<Country> countryListRest() {
		return (List<Country>) crepository.findAll();
	}

	// REST get country by id
	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
	public @ResponseBody Country findCountryRest(@PathVariable("id") Long countryId) {
		return crepository.findOne(countryId);
	}

	// RESTful service to get all users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> userListRest() {
		return (List<User>) urepository.findAll();
	}

	// REST get user by id
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody User findUserRest(@PathVariable("id") Long userId) {
		return urepository.findOne(userId);
	}

	// RESTful service to get all favs
	@RequestMapping(value = "/favs", method = RequestMethod.GET)
	public @ResponseBody List<Fav> favListRest() {
		return (List<Fav>) frepository.findAll();
	}

	// REST get fav by id
	@RequestMapping(value = "/fav/{id}", method = RequestMethod.GET)
	public @ResponseBody Fav findFavRest(@PathVariable("id") Long favId) {
		return frepository.findOne(favId);
	}
	
	
	// RESTful service to delete movie by id
	@RequestMapping(value = "/movie/{id}/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteMovieRest(@PathVariable("id") Long movieId) {
		mrepository.delete(movieId);
		return "Movie with id " + movieId + " deleted succesfully";
	}

}
