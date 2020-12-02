package com.digimaster.mymovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	
	private final Path root = Paths.get("C://Users/rizki/Downloads/mymovies/images/");
	
//	private final Path root = Paths.get("uploads");
	
	public void saveFile(MultipartFile file) {
		try {
			if (!Files.exists(root)) {
					Files.createDirectories(root);
				}
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void saveFile(MultipartFile file, int id) {
		try {
			if (!Files.exists(root)) {
					Files.createDirectories(root);
				}
			
			Optional<MovieModel> currentMovie = movieRepository.findById(id);
			if(currentMovie.isPresent()) {
				currentMovie.get().setImage(file.getOriginalFilename());
				movieRepository.save(currentMovie.get());
				Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public MovieModel createMovie(MovieModel movieModel) {
		return movieRepository.save(movieModel);
	}
	
	public Iterable<MovieModel> createMovie(Iterable<MovieModel> movieModel) {
		return movieRepository.saveAll(movieModel);
	}

	public MovieModel getMovie(int id) {
		return movieRepository.findById(id).get();
	}
	
	public MovieModel getMovieWithValidation(int id) {
		if(movieRepository.findById(id).isPresent()) {
			return movieRepository.findById(id).get();
		} else {
			return null;
		}
	}
	
	public MovieModel getMovie(String title) {
		return movieRepository.getMovieModelByTitle(title);
	}
	
	public MovieModel getMovie(String title, String genre) {
		return movieRepository.getMovieModelByTitleAndGenre(title, genre);
	}
	
	public MovieModel getMovie (String title, String genre, int release_year) {
		return movieRepository.getMovieModelByTitleAndGenreAndReleaseYear(title, genre, release_year);
	}
	
	public Iterable<MovieModel> getAllMovies() {
		return movieRepository.findAll();
	}
	
	public Iterable<MovieModel> getMoviesByGenre(String genre) {
		return movieRepository.getMoviesModelByGenre(genre);
	}
	
	public MovieModel updateMovie(MovieModel movieModel, int id) {
		Optional<MovieModel> currentMovie = movieRepository.findById(id);
		
		if(currentMovie.isPresent()) {
			MovieModel updatedMovie = currentMovie.get();
			updatedMovie.setTitle(movieModel.getTitle());
			updatedMovie.setGenre(movieModel.getGenre());
			updatedMovie.setReleaseYear(movieModel.getReleaseYear());
			return movieRepository.save(updatedMovie);
		} else {
			return movieModel;
		}
	}
	
	public boolean deleteMovie(int id) {
		movieRepository.deleteById(id);
		return true;
	}
	
	public void deleteMovieByTitle(String title) {
		movieRepository.deleteMovieModelByTitle(title);
	}

}
