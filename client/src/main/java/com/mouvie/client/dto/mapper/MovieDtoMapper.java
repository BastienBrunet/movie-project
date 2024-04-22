package com.mouvie.client.dto.mapper;

import com.mouvie.client.dto.model.MovieDto;
import com.mouvie.library.model.Movie;

import java.util.List;

public class MovieDtoMapper {

    public static MovieDto toMovieDto(Movie movie){
        return new MovieDto()
                .setId(movie.getId())
                .setName(movie.getName())
                .setDescription(movie.getDescription())
                .setReleaseDate(movie.getReleaseDate())
                .setRating(movie.getRating());
    }

    public static List<MovieDto> toMovieDtoList(List<Movie> movies){
        return movies.stream()
                .map(MovieDtoMapper::toMovieDto)
                .toList();
    }
}
