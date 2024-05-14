package com.mouvie.auth.config.customexception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class InvalidFileFormatException extends RuntimeException {


    @Getter
    private final List<String> msgList;

    /**
     * Constructor
     *
     * @param message message to send
     */
    public InvalidFileFormatException(String message) {
        super(message);
        this.msgList = new ArrayList<>();
    }
}
