package com.SoundBox.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.SoundBox.core.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenSecurity {
	
	@Value("${jwt.secret}")
	public String secret;
	@Value("${jwt.time}")
	public String time;
	
	public String tokenCreate(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date expirationDate = new Date(new Date().getTime() + Long.parseLong(time));
		
		return Jwts.builder().setIssuer("CARE_API")
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public Integer getIdUserAcces(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Integer.parseInt(claims.getSubject());
	}

}
