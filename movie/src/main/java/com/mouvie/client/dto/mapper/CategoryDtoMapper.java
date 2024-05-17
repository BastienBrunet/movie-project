package com.mouvie.client.dto.mapper;

import com.mouvie.client.controller.CategoryController;
import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.library.model.Category;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CategoryDtoMapper {

    public static CategoryDto toCategoryDto(Category category, boolean isHalJson){

        CategoryDto categoryDto = new CategoryDto()
                .setId(category.getId())
                .setName(category.getName());

        if(isHalJson){
            categoryDto.add(linkTo(methodOn(CategoryController.class).getCategoryById(categoryDto.getId())).withSelfRel());
            categoryDto.add(linkTo(methodOn(CategoryController.class).getMoviesOfCategory(categoryDto.getId())).withRel("movies"));
        }

        return categoryDto;
    }

}
