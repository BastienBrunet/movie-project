package com.mouvie.client.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@Accessors(chain = true)
public class MovieDto {

    private String id;

    private String name;

    private String description;

    private Date releaseDate;

    private Integer rating;

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
