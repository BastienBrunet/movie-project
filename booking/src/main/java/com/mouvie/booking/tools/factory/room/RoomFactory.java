package com.mouvie.booking.tools.factory.room;

import com.mouvie.booking.dto.model.room.RoomInputDto;
import com.mouvie.library.model.Cinema;
import com.mouvie.library.model.Room;

public class RoomFactory {

    public static Room createRoom(Cinema cinema, RoomInputDto inputDto){
        return new Room()
                .setName(inputDto.getName())
                .setSeats(inputDto.getSeats());
    }

    public static Room updateRoom(Room room, RoomInputDto inputDto){
        return room
                .setName(inputDto.getName())
                .setSeats(inputDto.getSeats());
    }
}
