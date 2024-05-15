package com.mouvie.auth.service.token;

import com.mouvie.auth.config.service.JwtService;
import com.mouvie.auth.dto.model.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.model.token.OutputRefreshTokenDto;
import com.mouvie.auth.dto.model.token.TokenCreationDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public OutputRefreshTokenDto getToken(TokenCreationDto tokenCreationDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tokenCreationDto.getLogin(), tokenCreationDto.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateFullToken(tokenCreationDto.getLogin());
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

    @Override
    public OutputAccessTokenDto validate(String accessToken) {
        return jwtService.generateAccessToken(jwtService.extractUsername(accessToken));
    }
}
