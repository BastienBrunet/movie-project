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
}
