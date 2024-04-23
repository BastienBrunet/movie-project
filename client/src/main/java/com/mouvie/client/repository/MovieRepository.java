package com.mouvie.client.repository;

import com.mouvie.client.dto.model.MovieDto;
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
            "new com.mouvie.client.dto.model.MovieDto(m.id, m.name, m.description, m.releaseDate, m.rating) " +
            "FROM Movie m")
    Page<MovieDto> findPage(Pageable pageable);

}
