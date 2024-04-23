package com.mouvie.client.service;

import com.mouvie.client.config.customexception.ElementNotFoundException;
import com.mouvie.client.dto.mapper.CategoryDtoMapper;
import com.mouvie.client.dto.model.CategoryDto;
import com.mouvie.client.dto.model.CategoryInputDto;
import com.mouvie.client.dto.model.MovieDto;
import com.mouvie.client.repository.CategoryRepository;
import com.mouvie.client.tools.factory.CategoryFactory;
import com.mouvie.library.model.Category;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto getById(String id) {
    	Category category = categoryRepository.findById(id).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Category [id = %s]", id)));
        return CategoryDtoMapper.toCategoryDto(category);
    }
    

    public List<MovieDto> getMoviesOfCategory(String id) {
    	if (!categoryRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Movie [id = %s]",id));
        return categoryRepository.findMoviesOfCategory(id);
    }

    public List<CategoryDto> getAll(){
        List<Category> categories = categoryRepository.findAll();
        return CategoryDtoMapper.toCategoryDtoList(categories);
    }

    public CategoryDto create(CategoryInputDto inputDto) {

    	Category category = CategoryFactory.createCategory(inputDto);
    	categoryRepository.save(category);

        return CategoryDtoMapper.toCategoryDto(category);
    }

    public CategoryDto update(CategoryInputDto inputDto) {
    	Category category = categoryRepository.findById(inputDto.getId()).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Category [id = %s]", inputDto.getId())));

    	category = CategoryFactory.updateCategory(category, inputDto);
        categoryRepository.save(category);

        return CategoryDtoMapper.toCategoryDto(category);
    }

    public void deleteCategory(String id){
        if (!categoryRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Category [id = %s]",id));
        categoryRepository.deleteById(id);
    }
}
