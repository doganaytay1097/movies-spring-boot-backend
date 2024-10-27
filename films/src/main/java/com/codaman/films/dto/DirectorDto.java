package com.codaman.films.dto;

import com.codaman.films.model.Director;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DirectorDto {

    private Long id;
    private String name;
    private String surname;
    private Date birthday;
    private String gender;
    private String nationality;

    public DirectorDto(Director director) {
        this.id = director.getId();
        this.name = director.getName();
        this.surname = director.getSurname();
        this.birthday = director.getBirthday();
        this.gender = director.getGender();
        this.nationality = director.getNationality();
    }
}
