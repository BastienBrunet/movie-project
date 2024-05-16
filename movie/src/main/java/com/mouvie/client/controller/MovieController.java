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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController extends BaseController {

    private final MovieService movieService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id){
        return ResponseEntity.ok(movieService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/categories")
    public ResponseEntity<List<CategoryDto>> getCategoriesOfMovie(@PathVariable String id){
        return ResponseEntity.ok(movieService.getCategoriesOfFilm(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    @GetMapping
    public ResponseEntity<PaginationPublicDto> getAll(Pageable pageable){
        return ResponseEntity.ok(movieService.getAll(pageable));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/search")
    public ResponseEntity<PaginationPublicDto> getMoviesByNameOrDescription(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            Pageable pageable) {
        return ResponseEntity.ok(movieService.getMoviesByTitleOrDescription(name, description, pageable));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieInputDto movieInputDto){
        return new ResponseEntity<>(movieService.create(movieInputDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieInputDto movieInputDto){
        return ResponseEntity.ok(movieService.update(movieInputDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
