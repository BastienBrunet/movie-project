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
import com.mouvie.library.service.storage.IFileSystemStorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final IFileSystemStorageService storageService;
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

        return new PaginationPublicDto(movies, MovieController.class, appContext.isHalJson());
    }

    @Override
    public MovieDto create(MovieInputDto inputDto) {

        String completeFilename = saveMovieCover(inputDto);

        Movie movie = MovieFactory.createMovie(inputDto, completeFilename);
        movieRepository.save(movie);

        return MovieDtoMapper.toMovieDto(movie, appContext.isHalJson());
    }

    @Override
    public MovieDto update(MovieInputDto inputDto) {
        Movie movie = movieRepository.findById(inputDto.getId()).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Movie [id = %s]", inputDto.getId())));

        String completeFilename = saveMovieCover(inputDto);

        movie = MovieFactory.updateMovie(movie, inputDto, completeFilename);
        movieRepository.save(movie);

        return MovieDtoMapper.toMovieDto(movie, appContext.isHalJson());
    }

    @Override
    public void deleteMovie(String id) {
        if (!movieRepository.existsById(id)) throw new ElementNotFoundException(String.format("Unable to find Movie [id = %s]",id));
        movieRepository.deleteById(id);
    }

    private String saveMovieCover(MovieInputDto inputDto) {
        String completeFilename = null;

        if (inputDto.getCover() != null) {
            String uuid = UUID.randomUUID().toString();
            storageService.store(inputDto.getCover().getBase64(), uuid, inputDto.getCover().getExtension());
            completeFilename = uuid + inputDto.getCover().getExtension();
        }
        return completeFilename;
    }
}
