package com.mouvie.client.dto.model;

import com.mouvie.library.model.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;
import java.util.List;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MovieDto extends RepresentationModel<MovieDto> {

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
