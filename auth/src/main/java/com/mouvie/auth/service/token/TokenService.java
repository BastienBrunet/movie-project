package com.mouvie.auth.service.token;

import com.mouvie.auth.config.customexception.TooManyRequestsException;
import com.mouvie.auth.config.service.JwtService;
import com.mouvie.auth.dto.model.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.model.token.OutputRefreshTokenDto;
import com.mouvie.auth.dto.model.token.TokenCreationDto;
import io.github.bucket4j.Bucket;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService implements ITokenService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public OutputRefreshTokenDto refreshToken(String refreshToken) {
        return jwtService.generateFullToken(jwtService.extractUsername(refreshToken));
    }

    @Override
    public OutputRefreshTokenDto getToken(TokenCreationDto tokenCreationDto, Bucket shortTimeBucket, Bucket longTimeBucket) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tokenCreationDto.getLogin(), tokenCreationDto.getPassword()));
        } catch (Exception e) {
            if (shortTimeBucket.tryConsume(1) && longTimeBucket.getAvailableTokens() > 0)
            {
                throw new UsernameNotFoundException("Invalid login or password");
            } else {
                longTimeBucket.tryConsume(1);
                throw new TooManyRequestsException("Too many failed attempts");
            }
        }

        shortTimeBucket.reset();
        return jwtService.generateFullToken(tokenCreationDto.getLogin());
    }

    @Override
    public OutputAccessTokenDto validate(String accessToken) {
        return jwtService.generateAccessToken(jwtService.extractUsername(accessToken));
    }
}
