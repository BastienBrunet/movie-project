package com.mouvie.auth.service.token;

import com.mouvie.auth.dto.model.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.model.token.OutputRefreshTokenDto;
import com.mouvie.auth.dto.model.token.TokenCreationDto;
import io.github.bucket4j.Bucket;

public interface ITokenService {

    OutputRefreshTokenDto refreshToken(String refreshToken);

    OutputRefreshTokenDto getToken(TokenCreationDto tokenCreationDto, Bucket shortTimeBucket, Bucket longTimeBucket);

    OutputAccessTokenDto validate(String accessToken);
}
