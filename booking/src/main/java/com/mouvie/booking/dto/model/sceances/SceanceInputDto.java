package com.mouvie.booking.dto.model.sceances;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;

@Data
public class SceanceInputDto {

    @NotBlank(message = "Movie can't be empty")
    private String movie;

    private Date date;
}
