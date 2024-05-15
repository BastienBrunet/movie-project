package com.mouvie.auth.dto.model.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Accessors(chain = true)
public class InputAccountDto {

    private String login;
    private String password;
    private List<String> roles;
    private String status;
}
