package com.mouvie.booking.repository.sceance;

import com.mouvie.booking.dto.model.sceances.SceancesDto;
import com.mouvie.library.model.Sceance;
import com.mouvie.library.repository.SceanceLibRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SceanceRepository extends SceanceLibRepository {

    @Query("SELECT " +
            "new com.mouvie.booking.dto.model.sceances.SceancesDto(s.id, s.movie.id, s.date) " +
            "FROM Sceance s " +
            "WHERE s.room.id = :roomId")
    List<SceancesDto> getAllByRoomId(String roomId);

    @Query("FROM Sceance s WHERE s.room.id = :roomId AND s.id = :sceanceId")
    Optional<Sceance> getByRoomAndId(String roomId, String sceanceId);

    @Query("SELECT SUM(r.seats) FROM Reservation r WHERE r.sceance.id = :id AND r.status.id = '7b6627d8-9f4e-4a1a-82b8-a3bb5d0b43d9'")
    Integer getTotalConfirmedSeats(String id);
}
