package com.mouvie.booking.dto.mapper.sceance;

import com.mouvie.booking.dto.model.sceances.SceancesDto;
import com.mouvie.library.model.Sceance;

public class SceanceDtoMapper {

    public static SceancesDto toDto(Sceance sceance) {
        return new SceancesDto()
                .setId(sceance.getId())
                .setDate(sceance.getDate())
                .setMovieId(sceance.getMovie().getId());
    }
}
