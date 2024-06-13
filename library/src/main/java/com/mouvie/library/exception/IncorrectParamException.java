package com.mouvie.library.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class IncorrectParamException extends RuntimeException {

    @Getter
    private final List<String> msgList;

    /**
     * Constructor
     *
     * @param message message to send
     */
    public IncorrectParamException(String message) {
        super(message);
        this.msgList = new ArrayList<>();
    }

}
