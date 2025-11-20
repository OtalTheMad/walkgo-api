package org.walkgo.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key signKey;

    public JwtService() {
        String _secret = System.getenv("JWT_SECRET");
        if (_secret == null || _secret.isBlank()) {
            throw new IllegalStateException("JWT_SECRET environment variable is missing or empty.");
        }
        this.signKey = Keys.hmacShaKeyFor(_secret.getBytes(StandardCharsets.UTF_8));
    }

    public String GenerateToken(UserDetails userDetails) {
        return GenerateToken(userDetails, null);
    }

    public String GenerateToken(UserDetails userDetails, Integer idUsuario) {
        Date _now = new Date(System.currentTimeMillis());
        Date _exp = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24);
        var _builder = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(_now)
                .setExpiration(_exp);
        if (idUsuario != null) {
            _builder.claim("id_usuario", idUsuario);
        }
        return _builder.signWith(signKey, SignatureAlgorithm.HS256).compact();
    }

    public String ExtractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean IsTokenValid(String token, UserDetails userDetails) {
        String _username = ExtractUsername(token);
        return _username.equals(userDetails.getUsername()) && !IsTokenExpired(token);
    }

    private boolean IsTokenExpired(String token) {
        Date _exp = Jwts.parserBuilder()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return _exp.before(new Date());
    }
}