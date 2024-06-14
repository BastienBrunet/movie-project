package com.mouvie.booking.dto.model.reservation;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class ReservationDto {

    private String uid;

    private Integer rank;

    private String status;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant expiresAt;

    public ReservationDto() {
    }

    public ReservationDto(String uid, Integer rank, String status, Instant createdAt, Instant updatedAt, Instant expiresAt) {
        this.uid = uid;
        this.rank = rank;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
    }
}
