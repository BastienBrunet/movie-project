package com.mouvie.booking.dto.model.sceances;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class SceancesDto {

    private String id;

    private Instant date;

    private String movieId;

    public SceancesDto() {
    }

    public SceancesDto(String id, String movieId, Instant date) {
        this.id = id;
        this.movieId = movieId;
        this.date = date;
    }
}
