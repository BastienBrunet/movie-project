package com.mouvie.client.controller;

import com.mouvie.client.dto.model.CategoryDto;
import com.mouvie.client.dto.model.CategoryInputDto;
import com.mouvie.client.dto.model.MovieDto;
import com.mouvie.client.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    private ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getById(id));
    }
    

    @GetMapping("/{id}/movies")
    private ResponseEntity<List<MovieDto>> getMoviesOfCategory(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getMoviesOfCategory(id));
    }

    @GetMapping
    private ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryInputDto categoryInputDto){
        return new ResponseEntity<>(categoryService.create(categoryInputDto), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryInputDto categoryInputDto){
        return ResponseEntity.ok(categoryService.update(categoryInputDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteCategory(@PathVariable String id){
    	categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
