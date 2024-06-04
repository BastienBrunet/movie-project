package com.mouvie.client.service.movie;


import com.mouvie.client.config.customexception.ElementNotFoundException;
import com.mouvie.client.config.customexception.InvalidFileFormatException;
import com.mouvie.client.controller.MovieController;
import com.mouvie.client.dto.mapper.MovieDtoMapper;
import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.client.dto.model.movie.MovieInputDto;
import com.mouvie.library.dto.page.PaginationPublicDto;
import com.mouvie.client.repository.CategoryRepository;
import com.mouvie.client.repository.MovieRepository;
import com.mouvie.client.tools.factory.MovieFactory;
import com.mouvie.library.model.Category;
import com.mouvie.library.model.Movie;
import com.mouvie.library.service.storage.IFileSystemStorageService;
import com.mouvie.security.config.appcontext.AppContext;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final IFileSystemStorageService storageService;
    private final AppContext appContext;

    private final List<String> IMAGE_EXTENSION = List.of(".jpg", ".jpeg", ".png", ".webp");

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
    
    public PaginationPublicDto getMoviesByTitleOrDescription(String name, String description, Pageable pageable) {
    	Page<MovieDto> movies;
    	if (name != null && description != null) {
    		 Page<Movie>moviesPage = movieRepository.findByNameContainingAndDescriptionContaining(name, description, pageable);
    		 movies = moviesPage.map(movie -> MovieDtoMapper.toMovieDto(movie, appContext.isHalJson()));
        } else if (name != null) {
        	 Page<Movie>moviesPage = movieRepository.findByNameContaining(name, pageable);
        	 movies = moviesPage.map(movie -> MovieDtoMapper.toMovieDto(movie, appContext.isHalJson()));
        } else if (description != null) {
        	 Page<Movie>moviesPage = movieRepository.findByDescriptionContaining(description, pageable);
        	 movies = moviesPage.map(movie -> MovieDtoMapper.toMovieDto(movie, appContext.isHalJson()));
        } else {
        	 Page<Movie>moviesPage = movieRepository.findAll(pageable);
        	 movies = moviesPage.map(movie -> MovieDtoMapper.toMovieDto(movie, appContext.isHalJson()));
        }
    	 return new PaginationPublicDto(movies, MovieController.class, appContext.isHalJson());
    }

    @Override
    public MovieDto create(MovieInputDto inputDto) {

        String completeFilename = saveMovieCover(inputDto);
        
        List<Category> categories = checkCategoryExist(inputDto.getCategoryIds());
        
        Movie movie = MovieFactory.createMovie(inputDto, completeFilename, categories);
        
        movieRepository.save(movie);

        return MovieDtoMapper.toMovieDto(movie, appContext.isHalJson());
    }

    @Override
    public MovieDto update(MovieInputDto inputDto) {
        Movie movie = movieRepository.findById(inputDto.getId()).orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Movie [id = %s]", inputDto.getId())));

        String completeFilename = saveMovieCover(inputDto);

        List<Category> categories = checkCategoryExist(inputDto.getCategoryIds());
        
        movie = MovieFactory.updateMovie(movie, inputDto, completeFilename, categories);
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
            if (inputDto.getCover().getExtension() == null || !IMAGE_EXTENSION.contains(inputDto.getCover().getExtension())) throw new InvalidFileFormatException("Invalid cover file format, valid formats are: .jpg, .jpeg, .png, .webp");

            String uuid = UUID.randomUUID().toString();
            storageService.store(inputDto.getCover().getBase64(), uuid, inputDto.getCover().getExtension());
            completeFilename = uuid + inputDto.getCover().getExtension();
        }
        return completeFilename;
    }
    
    // Vérifier l'existence des catégories
    private List<Category> checkCategoryExist (List<String> categoryIds) {
    	
        List<Category> categories = new ArrayList<>();
        for (String categoryId : categoryIds) {
            Category category = categoryRepository.findById(categoryId)
            		.orElseThrow( () -> new ElementNotFoundException(String.format("Unable to find Category [id = %s]", categoryId)));
            categories.add(category);
        }
        return categories;
    }
}
