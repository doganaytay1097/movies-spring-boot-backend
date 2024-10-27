package com.codaman.films.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieRequest {
    private String title;
    private String genre;
    private String description;
    private double rating;
    private int year;


    private String directorName;
    private String directorSurname;
    private Date directorBirthday;
    private String directorGender;
    private String directorNationality;
}

