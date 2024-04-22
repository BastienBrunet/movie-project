package com.mouvie.client.tools.factory;

import com.mouvie.client.dto.model.MovieDto;
import com.mouvie.client.dto.model.MovieInputDto;
import com.mouvie.library.model.Movie;

public class MovieFactory {

    public static Movie createMovie(MovieInputDto inputDto){
        return new Movie()
                .setName(inputDto.getName())
                .setDescription(inputDto.getDescription())
                .setReleaseDate(inputDto.getReleaseDate())
                .setRating(inputDto.getRating());
    }

    public static Movie updateMovie(Movie movie, MovieInputDto inputDto){
        return movie
                .setName(inputDto.getName())
                .setDescription(inputDto.getDescription())
                .setReleaseDate(inputDto.getReleaseDate())
                .setRating(inputDto.getRating());
    }
}
