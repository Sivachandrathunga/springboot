package com.mphasis.parent.utility;
import io.jsonwebtoken.*;
 
import java.util.Date;
 
import org.springframework.stereotype.Component;
 
@Component
public class JwtUtil {
 
    private String secretKey="b3e5a697e9bf12d845f8739da31961283e1845a6fkj97d826db6843a4f8ef27fa";
 
    public JwtUtil() {
    }
 
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*15))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
 
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
 
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);
            return !isTokenExpired(claims);
        } catch (JwtException e) {
            System.err.println("Token validation error: " + e.getMessage());
            return false;
        }
    }
 
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
 
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}