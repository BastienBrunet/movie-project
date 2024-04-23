package com.mouvie.client.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.List;

import com.mouvie.library.model.Category;

@Data
@Accessors(chain = true)
public class MovieDto {

    private String id;

    private String name;

    private String description;

    private Date releaseDate;

    private Integer rating;
    
    //private List<Category> categories;
    
    public MovieDto() {
    }

    public MovieDto(String id, String name, String description, Date releaseDate, Integer rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }
}
