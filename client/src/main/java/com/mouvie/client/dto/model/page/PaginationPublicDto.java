package com.mouvie.client.dto.model.page;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginationPublicDto {

    private List<?> content;
    private int totalElements;
    private int totalPages;
    private int pageNumber;


    public PaginationPublicDto(Page<?> page) {
        this.content = page.getContent();
        this.totalElements = (int) page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageNumber = page.getNumber();
    }
}