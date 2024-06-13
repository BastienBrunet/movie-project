package com.mouvie.booking.service.sceances;

import com.mouvie.booking.dto.model.sceances.SceanceInputDto;
import com.mouvie.booking.dto.model.sceances.SceancesDto;
import com.mouvie.library.model.Sceance;

import java.util.List;

public interface ISceancesService {

    List<SceancesDto> getAllSceances(String cinemaId, String roomId);

    SceancesDto getSceanceById(String cinemaId, String roomId, String sceanceId);

    SceancesDto createSceance(String cinemaId, String roomId, SceanceInputDto input);

    SceancesDto updateSceance(String cinemaId, String roomId, String sceanceId, SceanceInputDto input);

    void deleteSceance(String cinemaId, String roomId, String sceanceId);

    Sceance getSceance(String cinemaId, String roomId, String sceanceId);

    Sceance getSceance(String id);

    Integer getAvailableSeatsForSceance(String id);
}
