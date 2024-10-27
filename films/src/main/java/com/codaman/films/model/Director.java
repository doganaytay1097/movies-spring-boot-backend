package com.codaman.films.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private Date birthday;

    private String gender;

    private String nationality;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Movie> movies = new ArrayList<>();


    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
            movie.setDirector(this, false);
        }
    }
}
