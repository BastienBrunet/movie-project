package com.mouvie.booking.service.reservation;

import com.mouvie.booking.dto.model.reservation.ReservationDto;
import com.mouvie.booking.dto.model.reservation.ReservationInputDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {
    @Override
    public ReservationDto enterReservationTunnel(String movieId, ReservationInputDto reservationInputDto) {
        return null;
    }

    @Override
    public List<ReservationDto> getAllReservations(String movieId) {
        return List.of();
    }

    @Override
    public ReservationDto getReservationById(String id) {
        return null;
    }

    @Override
    public void confirmReservation(String id) {
        // TODO: send email to user

    }
}
