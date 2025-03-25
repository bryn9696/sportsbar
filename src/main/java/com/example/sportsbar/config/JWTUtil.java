package com.example.sportsbar.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtil {
    private static final String SECRET = "yourSecretKey";

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .sign(Algorithm.HMAC256(SECRET));
    }
}
