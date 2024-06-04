package com.mouvie.booking.dto.model.cinema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class CinemaDto {

    private String uid;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    public CinemaDto() {
    }

    public CinemaDto(String uid, String name, Instant createdAt, Instant updatedAt) {
        this.uid = uid;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
