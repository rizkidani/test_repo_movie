package com.digimaster.mymovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/createMovie")
	public MovieModel createMovie(@RequestBody MovieModel movieModel) {
		return movieService.createMovie(movieModel);
	}
	
	@PostMapping("/createMovies")
	public Iterable<MovieModel> createMovie(@RequestBody Iterable<MovieModel> movieModel) {
		return movieService.createMovie(movieModel);
	}
	
	@GetMapping("/movie/{id}")
	public MovieModel getMovie(@PathVariable int id) {
		return movieService.getMovie(id);
	}
	
	@GetMapping("/movie2")
	public BaseResponse<MovieModel> getMovieWithBaseResponse(@RequestParam int id) {
		
		MovieModel movie = movieService.getMovieWithValidation(id);
		BaseResponse<MovieModel> baseResponse = new BaseResponse<>();
		
		if(movie != null) {
			baseResponse.setCode(200);
			baseResponse.setSuccess(true);
			baseResponse.setMessage("Movie found");
			baseResponse.setData(movie);
		} else {
			baseResponse.setCode(404);
			baseResponse.setSuccess(true);
			baseResponse.setMessage("Movie not found");
			baseResponse.setData(movie);
		}

		return baseResponse;
	}
	
	@GetMapping("/movie/title")
	public MovieModel getMovieByTitle(@RequestParam String title) {
		return movieService.getMovie(title);
	}
	
	@GetMapping("/movie/get")
	public MovieModel getMovieByTitleAndGenre(@RequestParam String title, @RequestParam String genre) {
		return movieService.getMovie(title, genre);
	}
	
	@GetMapping("/movie/get2")
	public MovieModel getMovieByTitleAndGenreAndReleaseYear(@RequestParam String title, @RequestParam String genre, @RequestParam int release_year) {
		return movieService.getMovie(title, genre, release_year);
	}
	
	@GetMapping("/movies")
	public Iterable<MovieModel> getMovies() {
		return movieService.getAllMovies();
	}
	
	@GetMapping("/movies2")
	public MoviesResponse<MovieModel> getMoviesWithResponse() {
		MoviesResponse<MovieModel> moviesResponse = new MoviesResponse<>();
		moviesResponse.setCode(200);
		moviesResponse.setSuccess(true);
		moviesResponse.setMessage("Movie found");
		moviesResponse.setData(movieService.getAllMovies());
		
		return moviesResponse;
	}
	
	@GetMapping("/movie/genre")
	public Iterable<MovieModel> getMoviesByGenre(@RequestParam String genre) {
		return movieService.getMoviesByGenre(genre);
	}
	
	@PutMapping("/movie/{id}")
	public MovieModel updateMovie(@PathVariable int id, @RequestBody MovieModel movieModel) {
		return movieService.updateMovie(movieModel, id);
	}
	
	@DeleteMapping("/movie/{id}")
	public boolean deleteMovie(@PathVariable int id) {
		return movieService.deleteMovie(id);
	}
	
	@DeleteMapping("/movie/title/{title}")
	public boolean deleteMovieByTitle(@PathVariable String title) {
		movieService.deleteMovieByTitle(title);
		return true;
	}
	
	@PostMapping("/file")
	public boolean uploadFile(@RequestParam("file") MultipartFile file) {
		movieService.saveFile(file);
		return true;
	}
	
	@PostMapping("/file/id")
	public boolean uploadFile(@RequestParam("file") MultipartFile file, @RequestParam int id) {
		movieService.saveFile(file, id);
		return true;
	}
	
	// Coba Branch 1
}
