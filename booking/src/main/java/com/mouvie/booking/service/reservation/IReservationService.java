package com.mouvie.booking.service.reservation;

import com.mouvie.booking.dto.model.reservation.ReservationDto;
import com.mouvie.booking.dto.model.reservation.ReservationInputDto;
import com.mouvie.library.model.Reservation;

import java.util.List;

public interface IReservationService {

    ReservationDto enterReservationTunnel(String movieId, ReservationInputDto reservationInputDto);

    List<ReservationDto> getAllReservations(String movieId);

    ReservationDto getReservationById(String id);

    void confirmReservation(String id);

    Reservation getReservation(String id);

}
