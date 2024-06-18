package com.mouvie.booking.service.sceances;

import com.mouvie.booking.config.RabbitMQConfig;
import com.mouvie.booking.config.customexception.ElementNotFoundException;
import com.mouvie.booking.dto.mapper.sceance.SceanceDtoMapper;
import com.mouvie.booking.dto.model.sceances.SceanceInputDto;
import com.mouvie.booking.dto.model.sceances.SceancesDto;
import com.mouvie.booking.repository.sceance.SceanceRepository;
import com.mouvie.booking.service.room.RoomService;
import com.mouvie.booking.tools.factory.sceance.SceanceFactory;
import com.mouvie.library.model.Movie;
import com.mouvie.library.model.Room;
import com.mouvie.library.model.Sceance;
import com.mouvie.library.repository.MovieLibRepository;
import com.mouvie.library.repository.UserLibRepository;
import com.mouvie.library.tools.factory.EmailFactory;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SceancesService implements ISceancesService {

    private final SceanceRepository sceanceRepository;
    private final RoomService roomService;
    private final MovieLibRepository movieRepository;
    private final RabbitTemplate rabbitTemplate;
    private final UserLibRepository userLibRepository;

    @Override
    public List<SceancesDto> getAllSceances(String cinemaId, String roomId) {

        Room room = roomService.getRoomByCinemaIdAndId(cinemaId, roomId);

        return sceanceRepository.getAllByRoomId(room.getId());
    }

    @Override
    public SceancesDto getSceanceById(String cinemaId, String roomId, String sceanceId) {
        return SceanceDtoMapper.toDto(getSceance(cinemaId, roomId, sceanceId));
    }

    @Override
    public SceancesDto createSceance(String cinemaId, String roomId, SceanceInputDto input) {
        Room room = roomService.getRoomByCinemaIdAndId(cinemaId, roomId);
        Movie movie = movieRepository.findById(input.getMovie()).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find Movie [id = %s]", input.getMovie())));

        Sceance newSceance = SceanceFactory.createSceance(input, room, movie);

        // Send email to user
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE_NAME, EmailFactory.buildNewSceanceEmail(userLibRepository.findAll(), newSceance));

        return SceanceDtoMapper.toDto(sceanceRepository.save(newSceance));
    }

    @Override
    public SceancesDto updateSceance(String cinemaId, String roomId, String sceanceId, SceanceInputDto input) {

        Sceance sceanceToUpdate = getSceance(cinemaId, roomId, sceanceId);
        Movie movie = movieRepository.findById(input.getMovie()).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find Movie [id = %s]", input.getMovie())));

        sceanceToUpdate = SceanceFactory.updateSceance(sceanceToUpdate, input, movie);

        return SceanceDtoMapper.toDto(sceanceRepository.save(sceanceToUpdate));
    }

    @Override
    public void deleteSceance(String cinemaId, String roomId, String sceanceId) {

        Sceance sceance = getSceance(cinemaId, roomId, sceanceId);
        sceanceRepository.delete(sceance);

    }

    @Override
    public Sceance getSceance(String cinemaId, String roomId, String sceanceId) {
        Room room = roomService.getRoomByCinemaIdAndId(cinemaId, roomId);

        return sceanceRepository.getByRoomAndId(room.getId(), sceanceId).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find Sceance [id = %s] for Room [id = %s]", sceanceId, roomId)));
    }

    @Override
    public Sceance getSceance(String id) {
        return sceanceRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find Sceance [id = %s]", id )));
    }

    @Override
    public Integer getAvailableSeatsForSceance(String id) {
        Sceance sceance = getSceance(id);
        Integer totalConfirmedSeats = sceanceRepository.getTotalConfirmedSeats(id);

        totalConfirmedSeats = totalConfirmedSeats != null ? totalConfirmedSeats : 0;

        return sceance.getRoom().getSeats() - totalConfirmedSeats;
    }


}
