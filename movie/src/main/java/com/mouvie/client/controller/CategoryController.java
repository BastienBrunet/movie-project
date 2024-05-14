package com.mouvie.client.controller;

import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.category.CategoryInputDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.client.dto.model.page.PaginationPublicDto;
import com.mouvie.client.service.category.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getById(id));
    }
    

    @GetMapping("/{id}/movies")
    public ResponseEntity<List<MovieDto>> getMoviesOfCategory(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getMoviesOfCategory(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<PaginationPublicDto> getAll(Pageable pageable){
        return ResponseEntity.ok(categoryService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryInputDto categoryInputDto){
        return new ResponseEntity<>(categoryService.create(categoryInputDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryInputDto categoryInputDto){
        return ResponseEntity.ok(categoryService.update(categoryInputDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteCategory(@PathVariable String id){
    	categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
