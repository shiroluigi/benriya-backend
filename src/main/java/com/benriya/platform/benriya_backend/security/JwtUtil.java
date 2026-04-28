package com.benriya.platform.benriya_backend.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSecret(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private final long EXPIRATION = 1000 * 60 * 60; //1 hour

    public String generateToken(String email){
        return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(getSecret())
            .compact();                         
    }

    public String extractEmail(String token){
        return Jwts.parser()
            .verifyWith(getSecret())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }   
    public boolean validateToken(String token){
        try {
            Jwts.parser().verifyWith(getSecret())
            .build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
