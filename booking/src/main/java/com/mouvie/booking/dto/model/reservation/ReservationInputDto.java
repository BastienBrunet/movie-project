package com.mouvie.booking.dto.model.reservation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReservationInputDto {

    @NotBlank(message = "Sceance can't be empty")
    private String sceance;

    @Min(value = 1, message = "NbSeats should be superior to 0")
    private int nbSeats;

    @NotBlank(message = "Room can't be empty")
    private String room;
}
