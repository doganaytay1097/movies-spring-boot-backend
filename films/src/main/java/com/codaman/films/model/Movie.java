package com.codaman.films.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String description;
    private double rating;
    private int year;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "director_id")
    @JsonBackReference
    private Director director;

    public void setDirector(Director director, boolean updateRelation) {
        this.director = director;
        if (updateRelation && director != null) {
            director.addMovie(this);
        }
    }

    public void setDirector(Director director) {
        setDirector(director, true);
    }
}


