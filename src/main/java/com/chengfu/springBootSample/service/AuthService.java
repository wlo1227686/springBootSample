package com.chengfu.springBootSample.service;


import java.security.Key;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@Service
public class AuthService {
    
    // Issuer
    private static final String ISSUER_JWT = "JWT";
    
//    private static final String AUDIENCE_AAD = "AAD";
    
    private static final String AUDIENCE_RESERVE = "RESERVE";
    
    // JWT Use Of Hardcoded Secret
    @Value ("${auth.jwt.key:}")
    private String jwtKey;
    
    @Value ("${auth.jwt.expiration-time:86400000}")
    private Integer jwtExpirationTime;
    
    /* ************************************************************************** *
     * 
     * Public Operation
     * 
     * ************************************************************************** */
    
    /**
     * 
     * @param userPK
     * @return
     */
    public String generateJwtToken (String userPK) {
        
        // 查詢DB的User清單
        
        // 產生JWT Token
        Map<String, Object> params = new HashMap<String, Object> ();
        params.put ("userPK", userPK);
        
        String jwtToken = this.generateToken (params);
        return jwtToken;
    }
    
    
    /**
     * 驗證 JWT Token，並取得Subject
     * 
     * @param request
     * @param token
     * @return Subject( User PK on System)
     * @throws Exception
     */
    public UsernamePasswordAuthenticationToken getAuthentication (String token, Claims claims) {
        Collection<? extends GrantedAuthority> authorities = Arrays.stream (claims.toString ().split (",")).map (
                SimpleGrantedAuthority::new).toList ();
        
        User principal = new User (claims.getSubject (), "", authorities);
        
        return new UsernamePasswordAuthenticationToken (principal, token, authorities);
    }
    
    
    /* ************************************************************************** *
     * 
     * Private Operation
     * 
     * ************************************************************************** */
    
    
    /**
     * 產生 JWT Token
     */
    private String generateToken (Map<String, Object> params) {
        Calendar calendar = Calendar.getInstance ();
        calendar.add (Calendar.MILLISECOND, jwtExpirationTime);
        Claims claims = Jwts.claims ();
        for (String key : params.keySet ()) {
            claims.put (key, params.get (key));
        }
        claims.setExpiration (calendar.getTime ());
        claims.setIssuer (ISSUER_JWT);
        claims.setAudience (AUDIENCE_RESERVE);
        claims.setSubject ((String) params.get ("userPK"));
        
        Key secretKey = Keys.hmacShaKeyFor (jwtKey.getBytes ());
        return Jwts.builder ().setClaims (claims).signWith (secretKey).compact ();
    }
    
    
}
