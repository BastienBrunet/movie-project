package com.mouvie.client.tools.factory;

import java.util.List;

import com.mouvie.client.dto.model.movie.MovieInputDto;
import com.mouvie.library.model.Category;
import com.mouvie.library.model.Movie;

public class MovieFactory {

    public static Movie createMovie(MovieInputDto inputDto, String coverFile, List<Category> categories){
        return new Movie()
                .setName(inputDto.getName())
                .setCover(coverFile)
                .setDescription(inputDto.getDescription())
                .setReleaseDate(inputDto.getReleaseDate())
                .setDuration(inputDto.getDuration())
                .setRating(inputDto.getRating())
                .setCategories(categories);
    }

    public static Movie updateMovie(Movie movie, MovieInputDto inputDto, String coverFile, List<Category> categories){
        return movie
                .setName(inputDto.getName())
                .setCover(coverFile)
                .setDescription(inputDto.getDescription())
                .setReleaseDate(inputDto.getReleaseDate())
                .setDuration(inputDto.getDuration())
                .setRating(inputDto.getRating())
                .setCategories(categories);
    }
}
