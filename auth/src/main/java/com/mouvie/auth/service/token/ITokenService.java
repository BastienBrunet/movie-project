package com.mouvie.auth.service.token;

import com.mouvie.auth.dto.model.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.model.token.OutputRefreshTokenDto;
import com.mouvie.auth.dto.model.token.TokenCreationDto;

public interface ITokenService {

    OutputRefreshTokenDto refreshToken(String refreshToken);

    OutputRefreshTokenDto getToken(TokenCreationDto tokenCreationDto);

    OutputAccessTokenDto validate(String accessToken);
}
