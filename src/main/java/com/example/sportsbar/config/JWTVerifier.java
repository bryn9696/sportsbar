package com.example.sportsbar.config;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;

import java.net.URL;
import java.security.interfaces.RSAPublicKey;

public class JWTVerifier {
    public static void main(String[] args) throws Exception {
        // URL for JWKS
        String jwksUrl = "https://dev-xxvb2v0tmw44fvj1.us.auth0.com/.well-known/jwks.json";

        // Retrieve the JWKS
        JWKSet jwkSet = JWKSet.load(new URL(jwksUrl));

        // Find the JWK with the correct 'kid' (Key ID)
        JWK jwk = jwkSet.getKeyByKeyId("L2CTTbFIW0iYuwaPWGHgz");

        // Convert JWK to RSAPublicKey
        RSAPublicKey publicKey = (RSAPublicKey) jwk.toRSAKey().toPublicKey();

        // Create a JWT verifier with the public key
        RSASSAVerifier verifier = new RSASSAVerifier(publicKey);

        // Now, use the verifier to verify your JWT tokens
    }
}
