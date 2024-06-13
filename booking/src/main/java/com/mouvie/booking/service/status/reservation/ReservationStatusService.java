package com.mouvie.booking.service.status.reservation;

import com.mouvie.booking.config.customexception.ElementNotFoundException;
import com.mouvie.library.model.ReservationStatus;
import com.mouvie.library.repository.ReservationStatusLibRepository;
import com.mouvie.library.tools.enumeration.ReservationStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationStatusService {

    private ReservationStatusLibRepository reservationStatusLibRepository;

    public ReservationStatus getReservationStatus(ReservationStatusEnum status) {
        return reservationStatusLibRepository.findById(status.getId()).orElseThrow(() -> new ElementNotFoundException(String.format("ReservationStatus [id=%s] not found", status.getId())));
    }
}
