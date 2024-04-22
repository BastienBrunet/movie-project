package com.mouvie.client.config;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime errorDate;
    private HttpStatus status;
    private String     message;
    private Map<String, String> msgParameters;

    public ErrorPublicDto parseApiErrorToView(){
        return new ErrorPublicDto(
                this.getErrorDate(),
                this.getMessage(),
                this.getMsgParameters());
    }
}