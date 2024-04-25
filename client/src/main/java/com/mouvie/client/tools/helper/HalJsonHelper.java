package com.mouvie.client.tools.helper;

import com.mouvie.client.controller.BaseController;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class HalJsonHelper {

    public static Link getPageableLink(Class<? extends BaseController> controller, Pageable pageable, String rel) {

        UriComponentsBuilder uriBuilder = linkTo(methodOn(controller).getAll(pageable)).toUriComponentsBuilder();

        uriBuilder.queryParam("page", pageable.getPageNumber());
        uriBuilder.queryParam("size", pageable.getPageSize());
        if (pageable.getSort().isSorted()) uriBuilder.queryParam("sort", pageable.getSort().toString());

        return Link.of(uriBuilder.toUriString()).withRel(rel);
    }
}
