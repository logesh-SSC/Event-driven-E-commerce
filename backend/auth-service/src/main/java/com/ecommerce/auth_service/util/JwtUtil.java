package com.ecommerce.auth_service.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(String username, String role) {

        /*
        System.out.println("----- JWUtil Token OF DETAILS -----");
        System.out.println("Token Generation for User: " + username + " with Role: " + role +"Token "+  Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(key)
                .compact());
        System.out.println("----- END OF DETAILS -----");
        */


        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
                .signWith(key)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)          // verifies signature
                .build()
                .parseSignedClaims(token) // parses signed JWT only
                .getPayload();
    }
}
