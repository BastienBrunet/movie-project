package com.mouvie.booking.tools.factory.cinema;

import com.mouvie.booking.dto.model.cinema.CinemaInputDto;
import com.mouvie.library.model.Cinema;

public class CinemaFactory {

    public static Cinema createCinema(CinemaInputDto inputDto){
        return new Cinema()
                .setName(inputDto.getName());
    }

    public static Cinema updateCinema(Cinema cinema, CinemaInputDto inputDto){
        return cinema
                .setName(inputDto.getName());
    }
}
