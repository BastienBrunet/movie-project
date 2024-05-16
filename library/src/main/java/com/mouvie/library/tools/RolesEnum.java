package com.mouvie.library.tools;

import lombok.Getter;

@Getter
public enum RolesEnum {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    final String role;

    RolesEnum(String role){
        this.role = role;
    }
}
