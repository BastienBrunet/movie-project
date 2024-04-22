package com.mouvie.library.repository;

import com.mouvie.library.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieLibRepository extends JpaRepository<Movie, String> {
}
