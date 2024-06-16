package com.mouvie.client.repository;

import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.library.repository.CategoryLibRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CategoryLibRepository {

	@Query("SELECT " +
			"new com.mouvie.client.dto.model.category.CategoryDto(c.id, c.name, :isHalJson) " +
			"FROM Category c")
	Page<CategoryDto> findPage(Pageable pageable, boolean isHalJson);

	@Query("SELECT " //
			+ " new com.mouvie.client.dto.model.movie.MovieDto(m.id, m.name, m.description, m.releaseDate, m.rating, m.cover, :isHalJson) " //
			+ " FROM Movie m " //
			+ " JOIN m.categories c " //
			+ " WHERE c.id = :id ") //
    List<MovieDto> findMoviesOfCategory(String id, boolean isHalJson);
}
