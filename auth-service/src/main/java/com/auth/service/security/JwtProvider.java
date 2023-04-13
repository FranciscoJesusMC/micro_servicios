package com.auth.service.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth.service.entity.AuthUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	@Value("${app.jwt-secret}")
	private String secret;
	
	
	public String generarToken(AuthUser authUser) {
		Map<String, Object> claims = new HashMap<>();
		claims = Jwts.claims().setSubject(authUser.getUsername());
		claims.put("id", authUser.getId());
		Date now =new Date();
		Date exp = new Date(now.getTime() + 3600000);
		return Jwts.builder().setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	
	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUserNameFromToken(String token) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			
		} catch (Exception e) {
			return "bad token";
		}
	}
}