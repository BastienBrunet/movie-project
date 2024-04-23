package com.mouvie.client.repository;

import com.mouvie.client.dto.model.CategoryDto;
import com.mouvie.library.repository.MovieLibRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MovieLibRepository {
	
	@Query("SELECT " //
			+ " new com.mouvie.client.dto.model.CategoryDto(c.id, c.name) " //
			+ " FROM Movie m " //
			+ " JOIN m.categories c " //
			+ " WHERE m.id = :id ") //
    List<CategoryDto> findCategoriesOfMovie(String id);
}
