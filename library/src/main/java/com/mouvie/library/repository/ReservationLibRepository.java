package com.mouvie.library.repository;

import com.mouvie.library.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationLibRepository extends JpaRepository<Reservation, String> {
}
