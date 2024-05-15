package com.mouvie.auth.controller;

import com.mouvie.auth.dto.model.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.model.token.OutputRefreshTokenDto;
import com.mouvie.auth.dto.model.token.TokenCreationDto;
import com.mouvie.auth.service.token.ITokenService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
public class TokenController {

    private final ITokenService tokenService;
    private final Bucket shortTimeBucket;
    private final Bucket longTimeBucket;

    public TokenController(ITokenService tokenService) {
        this.tokenService = tokenService;

        // Bucket that generates 3 tokens every 5 minutes
        Bandwidth limit = Bandwidth.classic(3,  Refill.intervally(3, Duration.ofMinutes(5)));
        this.shortTimeBucket = Bucket.builder()
                .addLimit(limit)
                .build();

        // Bucket that generates 1 token every 30 minutes
        Bandwidth longLimit = Bandwidth.classic(1,  Refill.intervally(1, Duration.ofMinutes(30)));
        this.longTimeBucket = Bucket.builder()
                .addLimit(longLimit)
                .build();
    }

    @PostMapping("/refresh-token/{refreshToken}/token")
    public ResponseEntity<OutputRefreshTokenDto> getRefreshToken(@PathVariable String refreshToken){
        return new ResponseEntity<>(tokenService.refreshToken(refreshToken), HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<OutputRefreshTokenDto> getToken(@Valid @RequestBody TokenCreationDto tokenCreationDto){
        return new ResponseEntity<>(tokenService.getToken(tokenCreationDto, shortTimeBucket, longTimeBucket), HttpStatus.CREATED);
    }

    @GetMapping("/validate/{accessToken}")
    public ResponseEntity<OutputAccessTokenDto> validateToken(@PathVariable String accessToken){
        return ResponseEntity.ok(tokenService.validate(accessToken));
    }
}
