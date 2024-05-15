package com.mouvie.auth.config.service;

import com.mouvie.auth.dto.model.token.OutputAccessTokenDto;
import com.mouvie.auth.dto.model.token.OutputRefreshTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.security.KeyRep.Type.SECRET;

@Component
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secret;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public OutputRefreshTokenDto generateFullToken(String username){
        Date accessTokenExpiration = new Date(System.currentTimeMillis() + 60000 * 60);
        Date refreshTokenExpiration = new Date(System.currentTimeMillis() + 60000 * 120);

        return new OutputRefreshTokenDto()
                .setAccessToken(createToken(username, accessTokenExpiration))
                .setAccessTokenExpiresAt(accessTokenExpiration.toInstant())
                .setRefreshToken(createToken(username, refreshTokenExpiration))
                .setRefreshTokenExpiresAt(refreshTokenExpiration.toInstant());
    }

    public OutputAccessTokenDto generateAccessToken(String username){
        Date accessTokenExpiration = new Date(System.currentTimeMillis() + 60000 * 60);

        return new OutputAccessTokenDto()
                .setAccessToken(createToken(username, accessTokenExpiration))
                .setAccessTokenExpiresAt(accessTokenExpiration.toInstant());
    }

    private String createToken(String username, Date expirationDate) {

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
