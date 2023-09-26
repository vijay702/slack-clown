package com.example.slackclown.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtHelper {

    private final static String SLACK_CLONE = "slack-clone";

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateJwt(String subject, Map<String, String> claims) {
        JwtBuilder builder = Jwts.builder()
                .setIssuer(SLACK_CLONE)
                .setSubject(subject)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date());
        claims.forEach(builder::claim);
        return builder.compact();
    }

    public static Claims decodeJwt(String accessToken) throws SignatureException {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }
}
