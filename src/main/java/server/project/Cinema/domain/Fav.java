package server.project.Cinema.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
  This is a table that contains movies that user has added to his list
  By connecting user id to movie id
 */


@Entity
public class Fav {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private long userid;
	private long movieid;
	
	public Fav(long userid, long movieid) {
		super();
		this.userid = userid;
		this.movieid = movieid;
	}

	public Fav() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getMovieid() {
		return movieid;
	}

	public void setMovieid(long movieid) {
		this.movieid = movieid;
	}

	@Override
	public String toString() {
		return "Fav [id=" + id + ", userid=" + userid + ", movieid=" + movieid + "]";
	}
}
