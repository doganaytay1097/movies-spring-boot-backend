package com.codaman.films.dto;

import com.codaman.films.model.Movie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    private Long id;
    private String title;
    private String genre;
    private String description;
    private double rating;
    private int year;
    private DirectorDto director;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.genre = movie.getGenre();
        this.description = movie.getDescription();
        this.rating = movie.getRating();
        this.year = movie.getYear();

        if (movie.getDirector() != null) {
            this.director = new DirectorDto(movie.getDirector());
        }
    }
}
