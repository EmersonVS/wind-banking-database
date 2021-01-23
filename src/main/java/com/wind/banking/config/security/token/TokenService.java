package com.wind.banking.config.security.token;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.wind.banking.app.models.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${app.jwt.expiration}")
	private String expValue;
	
	@Value("S{app.jwt.secret}")
	private String secretKey;
	
	public String genToken(Authentication auth) {
		User logged = (User) auth.getPrincipal();
		Date tokenDate = new Date();
		Date tokenExp = new Date(tokenDate.getTime() + Long.parseLong(expValue));
		
		return Jwts.builder()
				.setIssuer("Wind Banking")
				.setSubject(logged.getUsername().toString())
				.setIssuedAt(tokenDate)
				.setExpiration(tokenExp)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {			
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUsername(String token) {
		String requestToken = token.replace("Bearer ", "");
		Claims body = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(requestToken).getBody();
		return body.getSubject();
	}

}
