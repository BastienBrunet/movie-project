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
}
