package com.mouvie.booking.dto.mapper.room;

import com.mouvie.booking.dto.model.room.RoomDto;
import com.mouvie.library.model.Room;

public class RoomDtoMapper {

    public static RoomDto toRoomDto(Room room) {
        return new RoomDto()
                .setUid(room.getId())
                .setName(room.getName())
                .setSeats(room.getSeats())
                .setCreatedAt(room.getCreatedAt())
                .setUpdatedAt(room.getUpdatedAt());
    }
}
