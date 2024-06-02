package com.mouvie.library.repository;

import com.mouvie.library.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaLibRepository extends JpaRepository<Cinema, String> {
}
