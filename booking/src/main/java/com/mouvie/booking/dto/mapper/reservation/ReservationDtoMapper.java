package com.mouvie.booking.dto.mapper.reservation;

import com.mouvie.booking.dto.model.reservation.ReservationDto;
import com.mouvie.library.model.Reservation;

public class ReservationDtoMapper {

    public static ReservationDto toReservationDto(Reservation reservation){
        return new ReservationDto()
                .setUid(reservation.getId())
                .setRank(reservation.getRank())
                .setStatus(reservation.getStatus().getName())
                .setCreatedAt(reservation.getCreatedAt())
                .setUpdatedAt(reservation.getUpdatedAt())
                .setExpiresAt(reservation.getExpiresAt());
    }
}
