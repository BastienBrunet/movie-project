package com.mouvie.booking.service.cinema;

import com.mouvie.booking.dto.model.cinema.CinemaDto;
import com.mouvie.booking.dto.model.cinema.CinemaInputDto;
import com.mouvie.library.model.Cinema;

import java.util.List;

public interface ICinemaService {

    List<CinemaDto> getAllCinemas();

    CinemaDto getCinemaById(String id);

    CinemaDto createCinema(CinemaInputDto cinemaInputDto);

    CinemaDto updateCinema(String id, CinemaInputDto cinemaInputDto);

    void deleteCinema(String id);

    Cinema getCinema(String id);
}
