package com.codaman.films.controller;

import com.codaman.films.dto.DirectorDto;
import com.codaman.films.dto.DirectorRequest;
import com.codaman.films.model.Director;
import com.codaman.films.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/director")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/{directorId}")
    public ResponseEntity<DirectorDto> getDirector(@PathVariable Long directorId) {

        DirectorDto directorDto = new DirectorDto(directorService.getDirector(directorId));

        return ResponseEntity.ok(directorDto);
    }

    @PostMapping
    public ResponseEntity<Director> addDirector(@RequestBody DirectorRequest directorRequest) {
        return new ResponseEntity<>(directorService.addDirector(directorRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{directorId}")
    public ResponseEntity<Director> updateDirector(@PathVariable Long directorId, @RequestBody DirectorRequest directorRequest) {
        return ResponseEntity.ok(directorService.updateDirector(directorId,directorRequest));
    }

    @PutMapping("/{directorId}/addMovie/{movieId}")
    public ResponseEntity<DirectorDto> addMovieToDirector(@PathVariable Long directorId, @PathVariable Long movieId) {
        Director director = directorService.addMovieToDirector(directorId, movieId);
        return ResponseEntity.ok(new DirectorDto(director));
    }


    @DeleteMapping("/{directorId}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long directorId) {
        directorService.deleteDirector(directorId);
        return ResponseEntity.noContent().build();
    }
}
