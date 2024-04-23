package com.mouvie.client.controller;

import com.mouvie.client.dto.model.MovieDto;
import com.mouvie.client.dto.model.MovieInputDto;
import com.mouvie.client.dto.model.page.PaginationPublicDto;
import com.mouvie.client.service.MovieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id){
        return ResponseEntity.ok(movieService.getById(id));
    }

    @GetMapping
    private ResponseEntity<PaginationPublicDto> getAllMovies(Pageable pageable){
        return ResponseEntity.ok(movieService.getAll(pageable));
    }

    @PostMapping
    private ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieInputDto movieInputDto){
        return new ResponseEntity<>(movieService.create(movieInputDto), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieInputDto movieInputDto){
        return ResponseEntity.ok(movieService.update(movieInputDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
