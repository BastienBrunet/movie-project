package com.mouvie.client.service;

import com.mouvie.client.dto.mapper.MovieDtoMapper;
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
        Movie movie = movieRepository.findById(id).orElseThrow();
        return MovieDtoMapper.toMovieDto(movie);
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

    public MovieDto update(MovieDto inputDto) {
        Movie movie = movieRepository.findById(inputDto.getId()).orElseThrow();

        movie = MovieFactory.updateMovie(movie, inputDto);
        movieRepository.save(movie);

        return MovieDtoMapper.toMovieDto(movie);
    }

    public void deleteMovie(String id){
        // TODO : Check if movie exists
        movieRepository.deleteById(id);
    }
}
