package com.mouvie.client.dto.model.movie;

import com.mouvie.client.controller.FileController;
import com.mouvie.client.controller.MovieController;
import com.mouvie.client.dto.model.page.PaginationPublicDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MovieDto extends RepresentationModel<MovieDto> {

    private String id;

    private String name;

    private String description;

    private Date releaseDate;

    private Integer rating;

    private String cover;

    //private List<Category> categories;
    
    public MovieDto() {
    }

    public MovieDto(String id, String name, String description, Date releaseDate, Integer rating, String coverFile, boolean isHalJson) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;

        if(isHalJson) {
            add(linkTo(methodOn(MovieController.class).getMovieById(id)).withSelfRel());
            add(linkTo(methodOn(MovieController.class).getCategoriesOfMovie(id)).withRel("categories"));
        }

        if (coverFile != null && !coverFile.isEmpty()) {
            cover = linkTo(methodOn(FileController.class).serveFile(coverFile)).toString();
        }
    }
}
