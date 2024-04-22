package com.mouvie.client.dto.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;

@Data
public class MovieInputDto {

    private String id;

    @NotBlank(message = "Name can't be empty")
    @Size(max = 128, message = "Name should be between 1 and 128 characters")
    private String name;

    @NotBlank(message = "Description can't be empty")
    @Size(max = 2048, message = "Description should be between 1 and 2048 characters")
    private String description;

    private Date releaseDate;

    @Max(value = 5, message = "Rating should be between 0 and 5")
    @Min(value = 0, message = "Rating should be between 0 and 5")
    private Integer rating;
}
