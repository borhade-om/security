package com.batch.BatchProcessing.config;

import com.batch.BatchProcessing.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private String secretKey="CF2YJApSupE3o4epHmp9QdY6NkhgggoZVOt8X8moqqc=";



    public String generateToken( UserDetails userDetails) {
        Map<String,Object> claims= new HashMap<>();
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(userDetails.getUsername())
                .issuer("om")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*20*1000))
                .and()
                .signWith(generateKey())
                .compact();

    }

    private SecretKey generateKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

//    public String getSecretKey(){
//        return secretKey="CF2YJApSupE3o4epHmp9QdY6NkhgggoZVOt8X8moqqc=";
//    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
    Claims claims=  extractClaims(token);
    return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
       return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final  String userName=extractUserName(token);
        return(userName.equals(userDetails.getUsername()) && !istokenExpired(token)) ;
    }

    private boolean istokenExpired(String token) {
        return extractExpriration(token).before(new Date());
    }

    private Date extractExpriration(String token) {
        return  extractClaims(token,Claims::getExpiration);
    }
}
