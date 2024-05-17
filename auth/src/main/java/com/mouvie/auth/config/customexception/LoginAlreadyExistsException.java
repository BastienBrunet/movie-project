package com.mouvie.auth.config.customexception;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class LoginAlreadyExistsException extends RuntimeException {


    @Getter
    private final List<String> msgList;

    /**
     * Constructor
     *
     * @param message message to send
     */
    public LoginAlreadyExistsException(String message) {
        super(message);
        this.msgList = new ArrayList<>();
    }
}