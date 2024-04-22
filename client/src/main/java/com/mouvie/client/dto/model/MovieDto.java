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
}
