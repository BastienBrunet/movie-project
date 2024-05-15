package com.mouvie.auth.dto.model.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.time.Instant;
import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OutputAccessTokenDto extends RepresentationModel<OutputAccessTokenDto> {

    private String accessToken;
    private Instant accessTokenExpiresAt;
}
