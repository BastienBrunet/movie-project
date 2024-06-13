package com.mouvie.library.tools.enumeration;

import lombok.Getter;

@Getter
public enum ReservationStatusEnum {

    OPEN      ("7dac7d38-f35b-4e37-8629-84967a240fdc"),
    EXPIRED   ("313f6e74-cab8-4ec2-9433-784e4e8bee47"),
    CONFIRMED ("7b6627d8-9f4e-4a1a-82b8-a3bb5d0b43d9");

    private final String id;

    ReservationStatusEnum(String id){
        this.id = id;
    }
}
