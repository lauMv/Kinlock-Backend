package com.app.kinlock.common.security;

import com.app.kinlock.domain.entity.Broker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // 512-bit
    private final long expiration = 3600000; // 1 h

    public String generate(String username, String authority) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", authority)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    /* ---------- helper ---------- */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()   // ← same builder as validate()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}