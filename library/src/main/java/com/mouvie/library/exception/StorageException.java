package com.mouvie.library.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class StorageException extends RuntimeException {


    @Getter
    private final List<String> msgList;

    /**
     * Constructor
     *
     * @param message message to send
     */
    public StorageException(String message) {
        super(message);
        this.msgList = new ArrayList<>();
    }
}
