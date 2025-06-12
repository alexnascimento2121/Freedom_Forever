package com.br.ff.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;


@Component
public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
   
       
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    
    @PostConstruct
    public void validateSecret() {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 32 characters long");
        }
    }
    @PostConstruct
    public void printJwtProperties() {
        logger.info("JWT Secret length: {}", secret != null ? secret.length() : "null");
        logger.info("JWT Expiration: {}", expiration);
    }


    // 🔐 Cria a chave de assinatura a partir da string secreta
    private Key getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    // ✅ Gera o token com claims
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return doGenerateToken(claims);
    }

    private String doGenerateToken(Map<String, Object> claims) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // ✅ Extrai o username do token
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return claims.getSubject();
        } catch (Exception e) {
            logger.error("Erro ao extrair username do token: {}", e.getMessage());
            return null;
        }
    }

    // ✅ Extrai data de expiração
    public Date getExpirationDateFromToken(String token) {
        try {
            return getClaimsFromToken(token).getExpiration();
        } catch (Exception e) {
            logger.error("Erro ao extrair expiração do token: {}", e.getMessage());
            return null;
        }
    }

    // ✅ Extrai claims do token
    private Claims getClaimsFromToken(String token) {
    	try {
    	    return Jwts.parserBuilder()
    	               .setSigningKey(getSigningKey())
    	               .build()
    	               .parseClaimsJws(token)
    	               .getBody();
    	} catch (ExpiredJwtException e) {
    	    logger.warn("Token expirado: {}", e.getMessage());
    	    throw e;
    	} catch (JwtException e) {
    	    logger.warn("Token inválido: {}", e.getMessage());
    	    throw e;
    	}
    }

    // ✅ Verifica se expirou
    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration != null && expiration.before(new Date());
    }

    // ✅ Valida o token
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        boolean isValid = username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token);

        logger.info("Token validation result: {}", isValid);
        return isValid;
    }

    // 🔄 Pode renovar o token
    public boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    // 🔄 Atualiza o token
    public String refreshToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            return doGenerateToken(claims);
        } catch (Exception e) {
            logger.error("Erro ao renovar token: {}", e.getMessage());
            return null;
        }
    }
}
