package com.mouvie.auth.service.token;

import com.mouvie.auth.dto.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.token.OutputRefreshTokenDto;
import com.mouvie.auth.dto.token.TokenCreationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService implements ITokenService {
    @Override
    public OutputRefreshTokenDto refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public OutputRefreshTokenDto getToken(TokenCreationDto tokenCreationDto) {
        return null;
    }

    @Override
    public OutputAccessTokenDto validate(String accessToken) {
        return null;
    }
}
