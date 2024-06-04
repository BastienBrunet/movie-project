package com.mouvie.library.dto.page;


import com.mouvie.library.controller.BaseController;
import com.mouvie.library.tools.helper.HalJsonHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;


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