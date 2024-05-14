package com.mouvie.auth.config;


import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    private ResponseEntityBuilder() {
    }

    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError.parseApiErrorToView(), apiError.getStatus());
    }
}
