package com.mouvie.client.service;

import com.mouvie.client.config.customexception.ElementNotFoundException;
import com.mouvie.client.dto.mapper.MovieDtoMapper;
import com.mouvie.client.dto.model.CategoryDto;
import com.mouvie.client.dto.model.MovieDto;
import com.mouvie.client.dto.model.MovieInputDto;
import com.mouvie.client.repository.MovieRepository;
import com.mouvie.client.tools.factory.MovieFactory;
import com.mouvie.library.model.Movie;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieDto getById(String id) {
        Movie movie = movieRepository.findById(id).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Movie [id = %s]", id)));
        return MovieDtoMapper.toMovieDto(movie);
    }
    

    public List<CategoryDto> getCategoriesOfFilm(String id) {
    	if (!movieRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Movie [id = %s]",id));
        return movieRepository.findCategoriesOfMovie(id);
    }

    public List<MovieDto> getAll(){
        List<Movie> movies = movieRepository.findAll();
        return MovieDtoMapper.toMovieDtoList(movies);
    }

    public MovieDto create(MovieInputDto inputDto) {

        Movie movie = MovieFactory.createMovie(inputDto);
        movieRepository.save(movie);

        return MovieDtoMapper.toMovieDto(movie);
    }

    public MovieDto update(MovieInputDto inputDto) {
        Movie movie = movieRepository.findById(inputDto.getId()).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Movie [id = %s]", inputDto.getId())));

        movie = MovieFactory.updateMovie(movie, inputDto);
        movieRepository.save(movie);

        return MovieDtoMapper.toMovieDto(movie);
    }

    public void deleteMovie(String id){
        if (!movieRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Movie [id = %s]",id));
        movieRepository.deleteById(id);
    }
}
