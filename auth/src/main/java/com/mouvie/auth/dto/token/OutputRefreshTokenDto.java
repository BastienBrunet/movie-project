package com.mouvie.auth.dto.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OutputRefreshTokenDto extends RepresentationModel<OutputRefreshTokenDto> {

    private String accessToken;
    private String accessTokenExpiresAt;
    private String refreshToken;
    private String refreshTokenExpiresAt;
}
