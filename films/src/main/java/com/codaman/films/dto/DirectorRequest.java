package com.codaman.films.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DirectorRequest {
    private Long id;
    private String name;
    private String surname;
    private Date birthday;
    private String gender;
    private String nationality;
    private List<Long> movieIds = Collections.emptyList();
}


