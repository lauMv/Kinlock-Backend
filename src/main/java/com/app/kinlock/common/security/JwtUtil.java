package com.app.kinlock.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    private Key key;
    private final long expiration = 36000000;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        log.info("PROFILE: {}", Arrays.toString(env.getActiveProfiles()));
        log.info("JWT secret: {}", secret);

        byte[] decoded = Decoders.BASE64.decode(
                Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8)));
        this.key = Keys.hmacShaKeyFor(decoded);
    }

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
        } catch (io.jsonwebtoken.security.SecurityException | io.jsonwebtoken.MalformedJwtException e) {
            log.warn("Invalid JWT signature – {}", e.getMessage());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            log.warn("JWT expired – {}", e.getMessage());
        } catch (Exception e) {
            log.warn("JWT validation fail – {}", e.getMessage());
        }
        return false;
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()   // ← same builder as validate()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}