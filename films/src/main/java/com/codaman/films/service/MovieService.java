package com.codaman.films.service;

import com.codaman.films.dto.DirectorRequest;
import com.codaman.films.dto.MovieDto;
import com.codaman.films.dto.MovieRequest;
import com.codaman.films.model.Director;
import com.codaman.films.model.Movie;
import com.codaman.films.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final DirectorService directorService;


    public MovieService(MovieRepository movieRepository, DirectorService directorService) {
        this.movieRepository = movieRepository;
        this.directorService = directorService;
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
       return movies.stream().map(MovieDto::new).collect(Collectors.toList());
    }

    public MovieDto getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + movieId));
        return new MovieDto(movie); // MovieDto döndür
    }

    public void something(Movie movie, MovieRequest movieRequest) {
        movie.setTitle(movieRequest.getTitle());
        movie.setYear(movieRequest.getYear());
        movie.setGenre(movieRequest.getGenre());
        movie.setDescription(movieRequest.getDescription());
        movie.setRating(movieRequest.getRating());


        Director director = directorService.findOrCreateDirector(
                movieRequest.getDirectorName(),
                movieRequest.getDirectorSurname(),
                movieRequest.getDirectorBirthday(),
                movieRequest.getDirectorGender(),
                movieRequest.getDirectorNationality()
        );

        movie.setDirector(director);
    }

    public Movie addMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        something(movie, movieRequest);
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long movieId, MovieRequest movieRequest) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + movieId));
        something(movie, movieRequest);
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}
