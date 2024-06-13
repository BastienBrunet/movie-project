package com.mouvie.client.repository;

import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.library.model.Movie;
import com.mouvie.library.repository.MovieLibRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MovieLibRepository {

    @Query("SELECT " +
            "new com.mouvie.client.dto.model.movie.MovieDto(m.id, m.name, m.description, m.releaseDate, m.createdAt, m.updatedAt, m.rating, m.cover, m.duration, :isHalJson) " +
            "FROM Movie m")
    Page<MovieDto> findPage(Pageable pageable, boolean isHalJson);

	
	@Query("SELECT " //
			+ " new com.mouvie.client.dto.model.category.CategoryDto(c.id, c.name, :isHalJson) " //
			+ " FROM Movie m " //
			+ " JOIN m.categories c " //
			+ " WHERE m.id = :id ") //
    List<CategoryDto> findCategoriesOfMovie(String id, boolean isHalJson);
	
	 Page<Movie> findByNameContainingAndDescriptionContaining(String name, String description, Pageable pageable);
	
	 Page<Movie> findByNameContaining(String name, Pageable pageable);
    
	 Page<Movie> findByDescriptionContaining(String description, Pageable pageable);
}
