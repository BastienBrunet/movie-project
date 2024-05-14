package com.mouvie.client.service.movie;

import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.client.dto.model.movie.MovieInputDto;
import com.mouvie.client.dto.model.page.PaginationPublicDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMovieService {

    MovieDto getById(String id);

    List<CategoryDto> getCategoriesOfFilm(String id);

    PaginationPublicDto getAll(Pageable pageable);

    MovieDto create(MovieInputDto inputDto);

    MovieDto update(MovieInputDto inputDto);

    void deleteMovie(String id);
}
