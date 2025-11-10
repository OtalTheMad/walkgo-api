package org.walkgo.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.*;
import java.security.Key;

@Service
public class JwtService {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    private Key GetSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String GenerateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(GetSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String ExtractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(GetSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean IsTokenValid(String token, UserDetails userDetails) {
        String _username = ExtractUsername(token);
        return (_username.equals(userDetails.getUsername()) && !IsTokenExpired(token));
    }

    private boolean IsTokenExpired(String token) {
        Date _exp = Jwts.parserBuilder()
                .setSigningKey(GetSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return _exp.before(new Date());
    }
}