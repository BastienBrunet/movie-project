package com.mouvie.library.tools;

import lombok.Getter;

@Getter
public enum StatusEnum {

	open("open"),
	expired("expired"),
	confirmed("confirmed");

    final String status;

    StatusEnum(String status){
        this.status = status;
    }
}
