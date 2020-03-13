package com.codeWithMerald.RoyalSealLearningSystem.security;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.Role;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value(SecurityConstants.JWT_SECRET)
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Long id, String email, List<Role> roles, int validityInMilliseconds) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", id);
        claims.put("role", roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String generateToken(String email) {

        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();
        int validityInMilliseconds = 86400000;
        Date validity = new Date(validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getEmail(String token) {
        return getAllClaims(token).getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(SecurityConstants.TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = getEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return getAllClaims(token).getExpiration().before(new Date());
    }
}