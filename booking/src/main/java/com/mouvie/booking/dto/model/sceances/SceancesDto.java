package com.mouvie.booking.dto.model.sceances;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@Accessors(chain = true)
public class SceancesDto {

    private String id;

    private Date date;

    private String movieId;

    public SceancesDto() {
    }

    public SceancesDto(String id, String movieId, Date date) {
        this.id = id;
        this.movieId = movieId;
        this.date = date;
    }
}
