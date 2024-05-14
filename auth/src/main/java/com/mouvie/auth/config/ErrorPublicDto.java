package com.mouvie.auth.config;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorPublicDto {

    private LocalDateTime errorDate;
    private String message;
    private Map<String, String> msgParameters;

    public ErrorPublicDto(
                          LocalDateTime errorDate,
                          String message,
                          Map<String, String> msgParameters) {
        this.errorDate = errorDate;
        this.message = message;
        this.msgParameters = msgParameters != null ? msgParameters : new HashMap<>();
    }
}