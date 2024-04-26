package com.mouvie.client.tools.factory;

import com.mouvie.client.dto.model.movie.MovieInputDto;
import com.mouvie.library.model.Movie;

public class MovieFactory {

    public static Movie createMovie(MovieInputDto inputDto, String coverFile){
        return new Movie()
                .setName(inputDto.getName())
                .setCover(coverFile)
                .setDescription(inputDto.getDescription())
                .setReleaseDate(inputDto.getReleaseDate())
                .setRating(inputDto.getRating());
    }

    public static Movie updateMovie(Movie movie, MovieInputDto inputDto, String coverFile){
        return movie
                .setName(inputDto.getName())
                .setCover(coverFile)
                .setDescription(inputDto.getDescription())
                .setReleaseDate(inputDto.getReleaseDate())
                .setRating(inputDto.getRating());
    }
}
