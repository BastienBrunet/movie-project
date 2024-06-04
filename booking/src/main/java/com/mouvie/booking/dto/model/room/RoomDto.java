package com.mouvie.booking.dto.model.room;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class RoomDto {

    private String uid;

    private String name;

    private Integer seats;

    private Instant createdAt;

    private Instant updatedAt;

    public RoomDto() {
    }

    public RoomDto(String uid, String name, Integer seats, Instant createdAt, Instant updatedAt) {
        this.uid = uid;
        this.name = name;
        this.seats = seats;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
