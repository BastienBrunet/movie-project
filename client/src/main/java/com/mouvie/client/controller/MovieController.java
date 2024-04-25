package com.mouvie.client.controller;

import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.client.dto.model.movie.MovieInputDto;
import com.mouvie.client.dto.model.page.PaginationPublicDto;
import com.mouvie.client.service.movie.MovieService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController extends BaseController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id){
        return ResponseEntity.ok(movieService.getById(id));
    }
    

    @GetMapping("/{id}/categories")
    public ResponseEntity<List<CategoryDto>> getCategoriesOfMovie(@PathVariable String id){
        return ResponseEntity.ok(movieService.getCategoriesOfFilm(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<PaginationPublicDto> getAll(Pageable pageable){
        return ResponseEntity.ok(movieService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieInputDto movieInputDto){
        return new ResponseEntity<>(movieService.create(movieInputDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieInputDto movieInputDto){
        return ResponseEntity.ok(movieService.update(movieInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
