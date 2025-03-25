package com.example.sportsbar.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private String secretKey = "your-secret-key";  // You can retrieve this securely from environment variables
    private int jwtExpirationInMs = 86400000; // 24 hours

    // Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Extract Claims from the JWT Token
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Get Username from the Token
    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    // Validate if the token is expired
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
