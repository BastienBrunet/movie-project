package com.mouvie.client.repository;

import com.mouvie.client.dto.model.MovieDto;
import com.mouvie.library.repository.CategoryLibRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CategoryLibRepository {
	
	@Query("SELECT " //
			+ " new com.mouvie.client.dto.model.MovieDto(m.id, m.name, m.description, m.releaseDate, m.rating) " //
			+ " FROM Category c " //
			+ " JOIN c.movies m " //
			+ " WHERE c.id = :id ") //
    List<MovieDto> findMoviesOfCategory(String id);
}
