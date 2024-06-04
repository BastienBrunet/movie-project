package com.mouvie.booking.service.room;

import com.mouvie.booking.dto.model.room.RoomDto;
import com.mouvie.booking.dto.model.room.RoomInputDto;
import com.mouvie.library.model.Room;

import java.util.List;

public interface IRoomService {

    List<RoomDto> getRooms(String cinemaId);

    RoomDto getRoom(String cinemaId, String roomId);

    RoomDto createRoom(String cinemaId, RoomInputDto roomInputDto);

    RoomDto updateRoom(String cinemaId, String roomId, RoomInputDto roomInputDto);

    void deleteRoom(String cinemaId, String roomId);

    Room getRoomByCinemaIdAndId(String cinemaId, String roomId);
}
