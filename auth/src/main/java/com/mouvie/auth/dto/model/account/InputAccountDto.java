package com.mouvie.auth.dto.model.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class InputAccountDto {

    private String login;
    private String password;
    private List<String> roles;
    private String status;
}
