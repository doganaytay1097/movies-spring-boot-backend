package com.codaman.films.service;

import com.codaman.films.dto.DirectorDto;
import com.codaman.films.dto.DirectorRequest;
import com.codaman.films.exceptions.ResourceNotFoundException;
import com.codaman.films.model.Director;
import com.codaman.films.model.Movie;
import com.codaman.films.repository.DirectorRepository;
import com.codaman.films.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;

    public DirectorService(DirectorRepository directorRepository, MovieRepository movieRepository) {
        this.directorRepository = directorRepository;
        this.movieRepository = movieRepository;
    }

    public Director findOrCreateDirector(String name, String surname, Date birthday, String gender, String nationality) {
        return directorRepository.findByNameAndSurname(name, surname)
                .orElseGet(() -> {
                    Director director = new Director();
                    director.setName(name);
                    director.setSurname(surname);
                    director.setBirthday(birthday);
                    director.setGender(gender);
                    director.setNationality(nationality);
                    return directorRepository.save(director);
                });
    }

    public Director getDirector(Long directorId) {



        return directorRepository.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Director not found with id " + directorId));
    }

    public Director addDirector(DirectorRequest directorRequest) {

        Director director = new Director();

        changeSomething(director, directorRequest);

        return directorRepository.save(director);
    }


    public Director addMovieToDirector(Long directorId, Long movieId) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Director not found with id " + directorId));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + movieId));


        director.addMovie(movie);


        movieRepository.save(movie);
        return directorRepository.save(director);
    }


    public Director updateDirector(Long directorId, DirectorRequest directorRequest) {
        Director director = directorRepository.findById(directorId).get();

        changeSomething(director, directorRequest);

        return directorRepository.save(director);
    }



    public void changeSomething(Director director, DirectorRequest directorRequest) {
        director.setName(directorRequest.getName());
        director.setSurname(directorRequest.getSurname());
        director.setGender(directorRequest.getGender());
        director.setBirthday(directorRequest.getBirthday());
        director.setNationality(directorRequest.getNationality());


        List<Movie> movies = (directorRequest.getMovieIds() != null) ?
                directorRequest.getMovieIds().stream()
                        .map(movieId -> movieRepository.findById(movieId)
                                .orElseThrow(() -> new RuntimeException("Movie not found with id " + movieId)))
                        .collect(Collectors.toList())
                : Collections.emptyList();

        director.setMovies(movies);
    }



    public void deleteDirector(Long directorId) {
        directorRepository.deleteById(directorId);
    }
}
