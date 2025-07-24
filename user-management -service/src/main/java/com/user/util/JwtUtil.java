package com.user.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.user.constants.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET = Constants.JWT_SECRET_KEY;
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long JWT_EXPIRATION_MS = 60 * 60 * 1000;
    
    
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

       
        claims.put("roles", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

  
    public List<String> extractRoles(String token) {
        Claims claims = extractClaims(token);
        return claims.get("roles", List.class);
    }

   
    public String extractUsername(String token) {
        try {
            return extractClaims(token).getSubject(); 
        } catch (Exception e) {
            System.out.println("Failed to extract username: " + e.getMessage());
            return null;
        }
    }


    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }


    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
}
