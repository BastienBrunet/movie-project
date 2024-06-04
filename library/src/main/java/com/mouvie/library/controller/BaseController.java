package com.mouvie.library.controller;

import com.mouvie.library.dto.page.PaginationPublicDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    public abstract ResponseEntity<PaginationPublicDto> getAll(Pageable pageable);
}
