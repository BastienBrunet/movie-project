package com.mouvie.auth.dto.model.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OutputAccountDto extends RepresentationModel<OutputAccountDto> {

    private String uid;
    private String login;
    private List<String> roles;
    private Instant createdAt;
    private Instant updatedAt;
}
