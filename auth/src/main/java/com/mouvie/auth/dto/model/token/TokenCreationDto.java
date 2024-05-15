package com.mouvie.auth.dto.model.token;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenCreationDto {

    private String login;
    private String password;
    private String from;
}
