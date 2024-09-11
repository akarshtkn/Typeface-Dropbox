package com.typeface.dropbox.jwt.service.implementation;

import com.typeface.dropbox.jwt.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImplementation implements JwtService {

    Logger logger = LoggerFactory.getLogger(JwtServiceImplementation.class);

    @Value("${spring.security.jwt.secretKey}")
    private String secretKey;

    @Value("${spring.security.jwt.expirationMs}")
    private long jwtExpirationMs;

    public String getTokenFromHeader(HttpServletRequest request) {
        String token = null;
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (token != null) {
            logger.debug("Token successfully retrieved from cookies.");
        } else {
            logger.warn("No token found in cookies.");
        }
        return token;
    }

    public String extractUsername(String token) {
        String username = extractClaim(token, Claims::getSubject);
        if (username != null) {
            logger.debug("Successfully extracted username from token.");
        } else {
            logger.warn("Failed to extract username from token.");
        }
        return username;
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        boolean isExpired = expiration.before(new Date());

        if (isExpired) {
            logger.warn("Token has expired.");
        } else {
            logger.debug("Token is still valid.");
        }
        return isExpired;
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        logger.debug("Generating token for user: {}", userDetails.getUsername());
        return buildToken(extraClaims, userDetails, jwtExpirationMs);
    }

    public String generateToken(UserDetails userDetails) {
        logger.debug("Generating token for user: {}", userDetails.getUsername());
        return buildToken(new HashMap<>(), userDetails, jwtExpirationMs);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long jwtExpiration) {
        String username = userDetails.getUsername();
        Date issuedAt = new Date();
        Date expiration = new Date(System.currentTimeMillis() + jwtExpiration);

        String token = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        logger.debug("Generated token for user: {}", username);
        logger.trace("Token details: \n"
                        + "- Issued at: {}\n"
                        + "- Expiration: {}\n",
                issuedAt, expiration);

        return token;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        T claim = claimsResolver.apply(claims);

        if (claim != null) {
            logger.debug("Successfully extracted claim: {}", claim);
        } else {
            logger.warn("No claim found in the token.");
        }

        return claim;
    }

    private Claims extractAllClaims(String token) {
        logger.debug("Extracting all claims from token.");

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            logger.error("Token has expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Malformed JWT token: {}", e.getMessage());
        } catch (SecurityException e) {
            logger.error("JWT signature does not match: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }

        throw new IllegalArgumentException("Invalid token provided.");
    }

    private Key getSignInKey() {
        logger.debug("Building sign-in key from secret key.");
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
