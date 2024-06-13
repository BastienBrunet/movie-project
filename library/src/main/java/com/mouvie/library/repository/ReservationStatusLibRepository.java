package com.mouvie.library.repository;

import com.mouvie.library.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationStatusLibRepository extends JpaRepository<ReservationStatus, String> {
}
