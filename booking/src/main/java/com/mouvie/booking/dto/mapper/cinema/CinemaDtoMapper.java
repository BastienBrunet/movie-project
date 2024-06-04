package com.mouvie.booking.dto.mapper.cinema;

import com.mouvie.booking.dto.model.cinema.CinemaDto;
import com.mouvie.library.model.Cinema;

public class CinemaDtoMapper {

    public static CinemaDto toCinemaDto(Cinema cinema) {
        return new CinemaDto()
                .setUid(cinema.getId())
                .setName(cinema.getName())
                .setCreatedAt(cinema.getCreatedAt())
                .setUpdatedAt(cinema.getUpdatedAt());
    }
}
