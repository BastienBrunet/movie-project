package com.mouvie.booking.tools.factory.reservation;

import com.mouvie.booking.dto.model.reservation.ReservationInputDto;
import com.mouvie.library.model.Reservation;
import com.mouvie.library.model.ReservationStatus;
import com.mouvie.library.model.User;

import java.time.Instant;

public class ReservationFactory {

    public static Reservation createReservation(int rank, ReservationInputDto inputDto, ReservationStatus reservationStatus, User user){
        return new Reservation()
                .setRank(rank)
                .setSeats(inputDto.getNbSeats())
                .setUser(user)
                .setStatus(reservationStatus);
    }
}
