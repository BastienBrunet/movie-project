package com.mouvie.booking.repository.room;

import com.mouvie.booking.dto.model.room.RoomDto;
import com.mouvie.library.model.Room;
import com.mouvie.library.repository.RoomLibRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends RoomLibRepository {

    @Query("SELECT " +
            "new com.mouvie.booking.dto.model.room.RoomDto(r.id, r.name, r.seats, r.createdAt, r.updatedAt) " +
            "FROM Room r " +
            "WHERE r.cinema.id = :cinemaId")
    List<RoomDto> getRoomsByCinemaId(String cinemaId);

    @Query("FROM Room r WHERE r.cinema.id = :cinemaId AND r.id = :roomId")
    Optional<Room> getRoomByCinemaIdAndId(String cinemaId, String roomId);



}
