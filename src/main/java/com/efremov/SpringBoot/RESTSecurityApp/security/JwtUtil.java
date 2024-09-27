package com.efremov.SpringBoot.RESTSecurityApp.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {
    private static Algorithm algorithm = Algorithm.HMAC256("secret");

    public String generateToken(String name) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("name", name)
                .withIssuedAt(new Date())
                .withIssuer("ADMIN")
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm)
                .withSubject("User details")
                .withIssuer("ADMIN")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("name").asString();
    }

}
