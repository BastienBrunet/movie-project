package com.mouvie.client.dto.model.category;

import com.mouvie.client.controller.CategoryController;
import com.mouvie.client.dto.model.movie.MovieDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends RepresentationModel<CategoryDto> {

    private String id;

    private String name;
    
   // private List<Movie> movies;
    
    public CategoryDto() {
    }

    public CategoryDto(String id, String name, boolean isHalJson) {
        this.id = id;
        this.name = name;

        if (isHalJson) {
            add(linkTo(methodOn(CategoryController.class).getCategoryById(id)).withSelfRel());
            add(linkTo(methodOn(CategoryController.class).getMoviesOfCategory(id)).withRel("movies"));
        }

    }
}
