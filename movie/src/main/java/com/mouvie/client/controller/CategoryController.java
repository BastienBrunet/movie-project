package com.mouvie.client.controller;

import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.category.CategoryInputDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.library.controller.BaseController;
import com.mouvie.library.dto.page.PaginationPublicDto;
import com.mouvie.client.service.category.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/movies")
    public ResponseEntity<List<MovieDto>> getMoviesOfCategory(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getMoviesOfCategory(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    @GetMapping
    public ResponseEntity<PaginationPublicDto> getAll(Pageable pageable){
        return ResponseEntity.ok(categoryService.getAll(pageable));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryInputDto categoryInputDto){
        return new ResponseEntity<>(categoryService.create(categoryInputDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryInputDto categoryInputDto){
        return ResponseEntity.ok(categoryService.update(categoryInputDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteCategory(@PathVariable String id){
    	categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
