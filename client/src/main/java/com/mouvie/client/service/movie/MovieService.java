package com.mouvie.client.service.movie;


import com.mouvie.client.config.appcontext.AppContext;
import com.mouvie.client.config.customexception.ElementNotFoundException;
import com.mouvie.client.controller.MovieController;
import com.mouvie.client.dto.mapper.MovieDtoMapper;
import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.client.dto.model.movie.MovieInputDto;
import com.mouvie.client.dto.model.page.PaginationPublicDto;
import com.mouvie.client.repository.MovieRepository;
import com.mouvie.client.tools.factory.MovieFactory;
import com.mouvie.library.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final AppContext appContext;

    @Override
    public MovieDto getById(String id) {
        Movie movie = movieRepository.findById(id).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Movie [id = %s]", id)));
        return MovieDtoMapper.toMovieDto(movie, appContext.isHalJson());
    }

    @Override
    public List<CategoryDto> getCategoriesOfFilm(String id) {
    	if (!movieRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Movie [id = %s]",id));
        return movieRepository.findCategoriesOfMovie(id, appContext.isHalJson());
    }

    @Override
    public PaginationPublicDto getAll(Pageable pageable) {
        Page<MovieDto> movies = movieRepository.findPage(pageable, appContext.isHalJson());

        return new PaginationPublicDto(movies, MovieController.class, appContext.isHalJson() );
    }

    @Override
    public MovieDto create(MovieInputDto inputDto) {

        Movie movie = MovieFactory.createMovie(inputDto);
        movieRepository.save(movie);

        return MovieDtoMapper.toMovieDto(movie, appContext.isHalJson());
    }

    @Override
    public MovieDto update(MovieInputDto inputDto) {
        Movie movie = movieRepository.findById(inputDto.getId()).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Movie [id = %s]", inputDto.getId())));

        movie = MovieFactory.updateMovie(movie, inputDto);
        movieRepository.save(movie);

        return MovieDtoMapper.toMovieDto(movie, appContext.isHalJson());
    }

    @Override
    public void deleteMovie(String id) {
        if (!movieRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Movie [id = %s]",id));
        movieRepository.deleteById(id);
    }
}
