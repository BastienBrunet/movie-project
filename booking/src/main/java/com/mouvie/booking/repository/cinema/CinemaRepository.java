package com.mouvie.booking.repository.cinema;

import com.mouvie.booking.dto.model.cinema.CinemaDto;
import com.mouvie.library.repository.CinemaLibRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends CinemaLibRepository {

    @Query("SELECT " +
            "new com.mouvie.booking.dto.model.cinema.CinemaDto(c.id, c.name, c.createdAt, c.updatedAt) " +
            "FROM Cinema c")
    List<CinemaDto> getAll();

}
