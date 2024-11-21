package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.repository.TokenRepository;
import com.group2.watchstorecover.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {



    @Value("${app.token.key}")
    private static String KEY;

    @Value("${app.token.expiration}")
    private static long EXPIRATION;

    @Override
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(UserDetails userDetails, Long time){
        return generateToken(new HashMap<>(),userDetails, time);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Long time){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() + EXPIRATION))) //Expire after 24 hours
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public Long getExpirationToken(String token){
        Long distance = (this.extractExpiration(token).getTime() - extractClaim(token,Claims::getIssuedAt).getTime());
        return distance;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    @Override
    public  boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
