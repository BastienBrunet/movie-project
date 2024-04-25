package com.mouvie.client.service.category;

import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.category.CategoryInputDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.client.dto.model.page.PaginationPublicDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {

    CategoryDto getById(String id);

    List<MovieDto> getMoviesOfCategory(String id);

    PaginationPublicDto getAll(Pageable pageable);

    CategoryDto create(CategoryInputDto inputDto);

    CategoryDto update(CategoryInputDto inputDto);

    void deleteCategory(String id);
}
