package com.mouvie.client.repository;

import com.mouvie.client.dto.model.category.CategoryDto;
import com.mouvie.client.dto.model.movie.MovieDto;
import com.mouvie.library.model.Movie;
import com.mouvie.library.repository.MovieLibRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MovieLibRepository {

    @Query("SELECT " +
            "new com.mouvie.client.dto.model.movie.MovieDto(m.id, m.name, m.description, m.releaseDate, m.createdAt, m.updatedAt, m.rating, m.cover, m.duration," +
			"(SELECT COUNT(r) > 0 FROM Reservation r WHERE r.sceance.movie.id = m.id AND r.status.id = '7dac7d38-f35b-4e37-8629-84967a240fdc')," +
			" :isHalJson) " +
            "FROM Movie m")
    Page<MovieDto> findPage(Pageable pageable, boolean isHalJson);

	
	@Query("SELECT " //
			+ " new com.mouvie.client.dto.model.category.CategoryDto(c.id, c.name, :isHalJson) " //
			+ " FROM Movie m " //
			+ " JOIN m.categories c " //
			+ " WHERE m.id = :id ") //
    List<CategoryDto> findCategoriesOfMovie(String id, boolean isHalJson);

	@Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.sceance.movie.id = :id AND r.status.id = '7dac7d38-f35b-4e37-8629-84967a240fdc'")
	boolean hasReservationAvailable(String id);
	
	 Page<Movie> findByNameContainingAndDescriptionContaining(String name, String description, Pageable pageable);
	
	 Page<Movie> findByNameContaining(String name, Pageable pageable);
    
	 Page<Movie> findByDescriptionContaining(String description, Pageable pageable);
}
