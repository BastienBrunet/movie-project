package com.mouvie.client.dto.model.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryInputDto {

    private String id;

    @NotBlank(message = "Name can't be empty")
    @Size(max = 128, message = "Name should be between 1 and 128 characters")
    private String name;
  
    //private List<Movie> movies;
}
