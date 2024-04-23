package com.mouvie.client.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import com.mouvie.library.model.Movie;

@Data
@Accessors(chain = true)
public class CategoryDto {

    private String id;

    private String name;
    
   // private List<Movie> movies;
    
    public CategoryDto() {
    }

    public CategoryDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
