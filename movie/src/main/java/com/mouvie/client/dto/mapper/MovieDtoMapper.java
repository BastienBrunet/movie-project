package com.mouvie.client.dto.mapper;

import com.mouvie.client.controller.FileController;
import com.mouvie.client.controller.MovieController;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.library.model.Movie;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class MovieDtoMapper {

    public static MovieDto toMovieDto(Movie movie, boolean isHalJson){

        MovieDto movieDto = new MovieDto()
                .setUid(movie.getId())
                .setName(movie.getName())
                .setDescription(movie.getDescription())
                .setReleaseDate(movie.getReleaseDate())
                .setRate(movie.getRating())
        		.setDuration(movie.getDuration())
        		.setCreatedAt(movie.getCreatedAt())
                .setUpdatedAt(movie.getUpdatedAt());

        if(isHalJson){
            movieDto.add(linkTo(methodOn(MovieController.class).getMovieById(movieDto.getUid())).withSelfRel());
            movieDto.add(linkTo(methodOn(MovieController.class).getCategoriesOfMovie(movieDto.getUid())).withRel("categories"));
        }

        if (movie.getCover() != null && !movie.getCover().isEmpty()) {
            movieDto.setCover(linkTo(methodOn(FileController.class).serveFile(movie.getCover())).toString());
        }

        return movieDto;
    }

}
