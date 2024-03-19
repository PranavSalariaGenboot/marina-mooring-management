package com.marinamooringmanagement.security.config;

import com.marinamooringmanagement.dao.TokenRepository;
import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.model.entity.EmployeeEntity;
import com.marinamooringmanagement.model.entity.TokenEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.Key;
import java.util.*;

@Service
public class JwtUtil {

    @Autowired
    TokenRepository tokenRepository;

    @Value("${jwt.secret.key}")
    private String secret;

    private static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String extractUsername(String token) {
        final Claims claims = Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        return validate(authToken, secret) && isTokenValid(authToken);
    }

    private boolean isTokenValid(String token) {
        final TokenEntity tokenEntity = tokenRepository.findTokenEntityByToken(token);
        if(tokenEntity == null) {
            return false;
        }

        if(new Date().before(tokenEntity.getExpireAt())) {
            final EmployeeEntity employeeEntity = tokenEntity.getEmployee();
            return employeeEntity != null;
        }

        return false;
    }

    private boolean validate(String authToken, String secretKey) {
        try {
            Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | ExpiredJwtException ex) {
            throw ex;
        }
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<String> rolesFromToken = claims.get("roles", List.class);
        if(null != rolesFromToken) {
            rolesFromToken.forEach(roleFromToken -> roles.add(new SimpleGrantedAuthority(roleFromToken)));
        }
        return roles;
    }

    public Integer getEmployeeIdFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
        return claims.get("id", Integer.class);
    }

    public String getRoleFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<String> rolesFromToken = claims.get("roles", List.class);
        if(!CollectionUtils.isEmpty(rolesFromToken)) {
            return rolesFromToken.get(0);
        }

        return null;
    }

    public String generateToken(EmployeeDto userDetails) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("roles", Arrays.asList(userDetails.getRole().getName()));
        claims.put("id", userDetails.getId());

        return doGenerateToken(claims, userDetails.getEmail());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000*60*48))
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    public Date getExpireTimeFromToken(final String token) {
        final Claims claims = Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
