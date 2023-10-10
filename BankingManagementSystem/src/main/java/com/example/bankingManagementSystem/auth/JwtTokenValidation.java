package com.example.bankingManagementSystem.auth;

import com.example.bankingManagementSystem.exception.UnauthorisedException;
import com.example.bankingManagementSystem.jwt.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenValidation {

    private static final String SECRET_KEY = "yourSecretKey";

    private static final long EXPIRATION_TIME = 86400000;

    public Long validateToken(String authorization) {
        try {
            if (StringUtil.isNullOrEmpty(authorization)) {
                throw new UnauthorisedException("Authorization token cannot be empty");
            }
            String bearerToken = authorization.split(" ")[1];
            Claims claims = JwtHelper.decodeJwt(bearerToken);
            return Long.parseLong(claims.getSubject());
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return null;
        }
    }

    public static String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

}
