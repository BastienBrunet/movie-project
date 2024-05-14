package com.mouvie.auth.service.token;

import com.mouvie.auth.dto.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.token.OutputRefreshTokenDto;
import com.mouvie.auth.dto.token.TokenCreationDto;

public interface ITokenService {

    OutputRefreshTokenDto refreshToken(String refreshToken);

    OutputRefreshTokenDto getToken(TokenCreationDto tokenCreationDto);

    OutputAccessTokenDto validate(String accessToken);
}
