package server.project.Cinema.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FavRepository extends CrudRepository<Fav, Long> {

	List<Fav> findById(Long id);
	
	List<Fav> findByUserid(Long userid);
	
	List<Fav> findByMovieid(Long movieid);

}