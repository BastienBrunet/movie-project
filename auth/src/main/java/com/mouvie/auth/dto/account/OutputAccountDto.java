package com.mouvie.auth.dto.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OutputAccountDto extends RepresentationModel<OutputAccountDto> {

    private String uuid;
    private String login;
    private List<String> roles;
    private String createdAt;
    private String updatedAt;
}
