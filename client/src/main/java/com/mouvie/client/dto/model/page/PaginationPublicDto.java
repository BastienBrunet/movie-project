package com.mouvie.client.dto.model.page;



import com.mouvie.client.controller.BaseController;
import com.mouvie.client.controller.CategoryController;
import com.mouvie.client.controller.MovieController;
import com.mouvie.client.tools.helper.HalJsonHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationPublicDto extends RepresentationModel<PaginationPublicDto> {

    private List<?> content;
    private int totalElements;
    private int totalPages;
    private int pageNumber;


    public PaginationPublicDto(Page<?> page, Class<? extends BaseController> controller, boolean isHalJson) {
        this.content = page.getContent();
        this.totalElements = (int) page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageNumber = page.getNumber();

        if (isHalJson){
            add(HalJsonHelper.getPageableLink(controller, page.getPageable(), "self"));
            if (page.hasPrevious()) add(HalJsonHelper.getPageableLink(controller, page.previousPageable(), "previous"));
            if (page.hasNext()) add(HalJsonHelper.getPageableLink(controller, page.nextPageable(), "next"));
        }
    }
}