package com.mouvie.auth.controller;

import com.mouvie.auth.dto.model.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.model.token.OutputRefreshTokenDto;
import com.mouvie.auth.dto.model.token.TokenCreationDto;
import com.mouvie.auth.service.token.ITokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TokenController {

    private final ITokenService tokenService;

    @PostMapping("/refresh-token/{refreshToken}/token")
    public ResponseEntity<OutputRefreshTokenDto> getRefreshToken(@PathVariable String refreshToken){
        return new ResponseEntity<>(tokenService.refreshToken(refreshToken), HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<OutputRefreshTokenDto> getToken(@Valid @RequestBody TokenCreationDto tokenCreationDto){
        return new ResponseEntity<>(tokenService.getToken(tokenCreationDto), HttpStatus.CREATED);
    }

    @GetMapping("/validate/{accessToken}")
    public ResponseEntity<OutputAccessTokenDto> validateToken(@PathVariable String accessToken){
        return ResponseEntity.ok(tokenService.validate(accessToken));
    }
}
