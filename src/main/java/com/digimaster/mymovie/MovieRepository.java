package com.digimaster.mymovie;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieModel, Integer> {
	MovieModel getMovieModelByTitle(String title);
	
	MovieModel getMovieModelByTitleAndGenre(String title, String genre);
	
	MovieModel getMovieModelByTitleAndGenreAndReleaseYear(String title, String genre, int release_year);
	
	Iterable<MovieModel> getMoviesModelByGenre(String genre);
	
	@Transactional
	void deleteMovieModelByTitle(String title);
}
