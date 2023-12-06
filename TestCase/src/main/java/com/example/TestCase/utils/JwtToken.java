package com.example.TestCase.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class JwtToken {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.lifetime}")
    private Duration lifetime;

    public String generateToken(UserDetails userDetails){

        Map<String,Object> claims = new HashMap<>();
        List<String> rolesList=userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).
                toList();
        claims.put("roles",rolesList);
        Date issuedDate= new Date();
        Date expiredDate = new Date(issuedDate.getTime()+lifetime.toMillis());
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(key,SignatureAlgorithm.ES256)
                .compact();
    }
    public String getUsername(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return getClaimsFromToken(token).getSubject();
    }
    public List<String> getRoles(String token) {
        return getClaimsFromToken(token).get("roles", List.class);
    }
    private Claims getClaimsFromToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
