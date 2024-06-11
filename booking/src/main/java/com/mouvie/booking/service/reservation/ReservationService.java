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
        // Create a reservation in the open status

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

        // Get reservation by id

        // Check if reservation is still in open status

        // If in open
            // check the seats left for the sceance
            // if its the last put all of the other reservations in expired status
            // if not the last, put the reservation in confirmed status

        // TODO: send email to user

    }
}
