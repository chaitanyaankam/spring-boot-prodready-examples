package com.farm.security.jwtsecurity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.farm.security.jwtsecurity.model.JwtUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	@Value("${api.auth.secret}")
	private String signingKey;

	@Value("${api.auth.tokenExpirationMsec}")
	private long tokenExpirationMsec;

	public String createToken(JwtUserDetails jwtUserDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + tokenExpirationMsec);

		 return Jwts.builder()
	                .setSubject(jwtUserDetails.getId())
	                .setIssuedAt(new Date())
	                .setExpiration(expiryDate)
	                .signWith(SignatureAlgorithm.HS512, signingKey)
	                .compact();
	}
}
