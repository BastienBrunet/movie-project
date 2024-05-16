package com.mouvie.client.service.category;

import com.mouvie.security.config.appcontext.AppContext;
import com.mouvie.client.config.customexception.ElementNotFoundException;
import com.mouvie.client.controller.CategoryController;
import com.mouvie.client.dto.mapper.CategoryDtoMapper;
import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.category.CategoryInputDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.client.dto.model.page.PaginationPublicDto;
import com.mouvie.client.repository.CategoryRepository;
import com.mouvie.client.tools.factory.CategoryFactory;
import com.mouvie.library.model.Category;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final AppContext appContext;

    @Override
    public CategoryDto getById(String id) {
    	Category category = categoryRepository.findById(id).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Category [id = %s]", id)));
        return CategoryDtoMapper.toCategoryDto(category, appContext.isHalJson());
    }

    @Override
    public List<MovieDto> getMoviesOfCategory(String id) {
    	if (!categoryRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Movie [id = %s]",id));
        return categoryRepository.findMoviesOfCategory(id, appContext.isHalJson());
    }

    @Override
    public PaginationPublicDto getAll(Pageable pageable) {
        Page<CategoryDto> categories = categoryRepository.findPage(pageable, appContext.isHalJson());
        return new PaginationPublicDto(categories, CategoryController.class, appContext.isHalJson());
    }

    @Override
    public CategoryDto create(CategoryInputDto inputDto) {

    	Category category = CategoryFactory.createCategory(inputDto);

    	categoryRepository.save(category);

        return CategoryDtoMapper.toCategoryDto(category, appContext.isHalJson());
    }

    @Override
    public CategoryDto update(CategoryInputDto inputDto) {
    	Category category = categoryRepository.findById(inputDto.getId()).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Category [id = %s]", inputDto.getId())));

    	category = CategoryFactory.updateCategory(category, inputDto);

        categoryRepository.save(category);

        return CategoryDtoMapper.toCategoryDto(category, appContext.isHalJson());
    }

    @Override
    public void deleteCategory(String id) {
        if (!categoryRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Category [id = %s]",id));
        categoryRepository.deleteById(id);
    }
}
