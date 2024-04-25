package com.mouvie.client.dto.mapper;

import com.mouvie.client.controller.MovieController;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.library.model.Movie;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class MovieDtoMapper {

    public static MovieDto toMovieDto(Movie movie, boolean isHalJson){

        MovieDto movieDto = new MovieDto()
                .setId(movie.getId())
                .setName(movie.getName())
                .setDescription(movie.getDescription())
                .setReleaseDate(movie.getReleaseDate())
                .setRating(movie.getRating());

        if(isHalJson){
            movieDto.add(linkTo(methodOn(MovieController.class).getMovieById(movieDto.getId())).withSelfRel());
            movieDto.add(linkTo(methodOn(MovieController.class).getCategoriesOfMovie(movieDto.getId())).withRel("categories"));
        }

        return movieDto;
    }

}
