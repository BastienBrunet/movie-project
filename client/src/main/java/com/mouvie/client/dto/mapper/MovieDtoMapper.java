package com.mouvie.client.dto.mapper;

import com.mouvie.client.controller.MovieController;
import com.mouvie.client.dto.model.MovieDto;
import com.mouvie.library.model.Movie;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class MovieDtoMapper {

    public static MovieDto toMovieDto(Movie movie){

        MovieDto movieDto = new MovieDto()
                .setId(movie.getId())
                .setName(movie.getName())
                .setDescription(movie.getDescription())
                .setReleaseDate(movie.getReleaseDate())
                .setRating(movie.getRating())
                //.setCategories(movie.getCategories())
                ;
//        movieDto.add(linkTo(methodOn(MovieController.class).getMovieById(movieDto.getId())).withSelfRel());

        return movieDto;
    }

    public static List<MovieDto> toMovieDtoList(List<Movie> movies){
        return movies.stream()
                .map(MovieDtoMapper::toMovieDto)
                .toList();
    }
}
