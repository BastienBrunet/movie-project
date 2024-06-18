package com.mouvie.booking.repository.reservation;

import com.mouvie.booking.dto.model.reservation.ReservationDto;
import com.mouvie.library.repository.ReservationLibRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends ReservationLibRepository {

    @Query("SELECT " +
            "new com.mouvie.booking.dto.model.reservation.ReservationDto(r.id, r.rank, r.status.name, r.createdAt, r.updatedAt, r.expiresAt) " +
            "FROM Reservation r " +
            "WHERE r.sceance.movie.id = :movieId")
    List<ReservationDto> getReservationsByMovieId(String movieId);
}
