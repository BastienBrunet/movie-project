package com.mouvie.client.controller;

import com.mouvie.client.dto.model.page.PaginationPublicDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    public abstract ResponseEntity<PaginationPublicDto> getAll(Pageable pageable);
}
