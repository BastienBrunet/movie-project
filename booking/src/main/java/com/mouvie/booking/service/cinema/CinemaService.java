package com.mouvie.booking.service.cinema;

import com.mouvie.booking.config.customexception.ElementNotFoundException;
import com.mouvie.booking.dto.mapper.cinema.CinemaDtoMapper;
import com.mouvie.booking.dto.model.cinema.CinemaDto;
import com.mouvie.booking.dto.model.cinema.CinemaInputDto;
import com.mouvie.booking.repository.cinema.CinemaRepository;
import com.mouvie.booking.tools.factory.cinema.CinemaFactory;
import com.mouvie.library.model.Cinema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaService implements ICinemaService {

    private final CinemaRepository cinemaRepository;

    @Override
    public List<CinemaDto> getAllCinemas() {
        return cinemaRepository.getAll();
    }

    @Override
    public CinemaDto getCinemaById(String id) {
        Cinema cinema = getCinema(id);

        return CinemaDtoMapper.toCinemaDto(cinema);
    }

    @Override
    public CinemaDto createCinema(CinemaInputDto cinemaInputDto) {

        Cinema newCinema = CinemaFactory.createCinema(cinemaInputDto);
        return CinemaDtoMapper.toCinemaDto(cinemaRepository.save(newCinema));
    }

    @Override
    public CinemaDto updateCinema(String id, CinemaInputDto cinemaInputDto) {
        Cinema cinemaToUpdate = getCinema(id);
        Cinema updatedCinema = CinemaFactory.updateCinema(cinemaToUpdate, cinemaInputDto);

        return CinemaDtoMapper.toCinemaDto(cinemaRepository.save(updatedCinema));
    }

    @Override
    public void deleteCinema(String id) {

        Cinema cinemaToDelete = getCinema(id);
        cinemaRepository.delete(cinemaToDelete);
    }

    @Override
    public Cinema getCinema(String id) {
        return cinemaRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find Cinema [id = %s]", id)));
    }
}
