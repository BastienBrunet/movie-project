package com.mouvie.booking.service.room;

import com.mouvie.booking.config.customexception.ElementNotFoundException;
import com.mouvie.booking.dto.mapper.room.RoomDtoMapper;
import com.mouvie.booking.dto.model.room.RoomDto;
import com.mouvie.booking.dto.model.room.RoomInputDto;
import com.mouvie.booking.repository.room.RoomRepository;
import com.mouvie.booking.service.cinema.CinemaService;
import com.mouvie.booking.tools.factory.room.RoomFactory;
import com.mouvie.library.model.Cinema;
import com.mouvie.library.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService implements IRoomService{

    private final RoomRepository roomRepository;
    private final CinemaService cinemaService;

    @Override
    public List<RoomDto> getRooms(String cinemaId) {

        // Used to ensure that the cinema exists
        Cinema cinema = cinemaService.getCinema(cinemaId);

        return roomRepository.getRoomsByCinemaId(cinema.getId());
    }

    @Override
    public RoomDto getRoom(String cinemaId, String roomId) {
        return RoomDtoMapper.toRoomDto(getRoomByCinemaIdAndId(cinemaId, roomId));
    }

    @Override
    public RoomDto createRoom(String cinemaId, RoomInputDto roomInputDto) {
        Cinema cinema = cinemaService.getCinema(cinemaId);

        Room newRoom = RoomFactory.createRoom(cinema, roomInputDto);

        return RoomDtoMapper.toRoomDto(roomRepository.save(newRoom));
    }

    @Override
    public RoomDto updateRoom(String cinemaId, String roomId, RoomInputDto roomInputDto) {

        Room roomToUpdate = getRoomByCinemaIdAndId(cinemaId, roomId);

        roomToUpdate = RoomFactory.updateRoom(roomToUpdate, roomInputDto);

        return RoomDtoMapper.toRoomDto(roomRepository.save(roomToUpdate));
    }

    @Override
    public void deleteRoom(String cinemaId, String roomId) {
        Room roomToDelete = getRoomByCinemaIdAndId(cinemaId, roomId);
        roomRepository.delete(roomToDelete);
    }

    @Override
    public Room getRoomByCinemaIdAndId(String cinemaId, String roomId) {
        return roomRepository.getRoomByCinemaIdAndId(cinemaId, roomId)
                .orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find Room [id = %s] for Cinema [id = %s]", roomId, cinemaId)));
    }
}
