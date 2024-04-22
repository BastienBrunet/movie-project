package com.mouvie.client.dto.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;

@Data
public class MovieInputDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Name is required")
    private String description;

    private Date releaseDate;

    @Max(value = 5)
    @Min(value = 0)
    private Integer rating;
}
