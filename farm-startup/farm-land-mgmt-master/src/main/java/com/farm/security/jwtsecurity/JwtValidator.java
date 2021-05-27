package com.farm.security.jwtsecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.farm.security.jwtsecurity.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtValidator {

	@Value("${api.auth.secret}")
	private String secret;
	
	private static final String ROLE_CLIAM = "role";
	
	Logger logger = LoggerFactory.getLogger(JwtValidator.class);
	
	public JwtUser validate(String token){
		JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			jwtUser = new JwtUser();
			jwtUser.setUserName(body.getSubject());
			// TO DO need to set email
			// jwtUser.setId((String) body.get("emailId"));
			jwtUser.setId(body.getSubject());
			jwtUser.setRole((String) body.get(ROLE_CLIAM));
		} 
		catch (SignatureException ex) {
            logger.error("Invalid JWT signature ", ex);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token ", ex);
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token ", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token ", ex);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.", ex);
        } catch (Exception e) {
			logger.error("Error while parsing Jwt token, token Incorrect ", e);
		}
		return jwtUser;
	}
}
