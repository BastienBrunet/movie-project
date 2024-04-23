package com.mouvie.client.dto.mapper;

import com.mouvie.client.dto.model.CategoryDto;
import com.mouvie.library.model.Category;

import java.util.List;

public class CategoryDtoMapper {

    public static CategoryDto toCategoryDto(Category category){
        return new CategoryDto()
                .setId(category.getId())
                .setName(category.getName())
                //.setMovies(category.getMovies()
                ;
    }

    public static List<CategoryDto> toCategoryDtoList(List<Category> categories){
        return categories.stream()
                .map(CategoryDtoMapper::toCategoryDto)
                .toList();
    }
}
