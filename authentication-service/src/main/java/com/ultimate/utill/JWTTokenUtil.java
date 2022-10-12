package com.ultimate.utill;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * The JwtTokenUtil is responsible for performing JWT operations like creation
 * and validation. It makes use of the io.jsonwebtoken.Jwts for achieving this
 * </p>
 * 
 * @author Nikhil TK
 * @see Claims
 */

@Component
@Slf4j
public class JWTTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		log.info("getUsernameFromToken {}", token);
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expire date from jwt token
	public Date getExpirationDateFromToken(String token) {
		log.info("getExpirationDateFromToken {}", token);
		log.info("getExpirationDateFromToken {}", getClaimFromToken(token, Claims::getExpiration).toInstant());
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// this method is called from getUsernameFromToken()
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		log.info("getClaimFromToken {}", token);
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieving any information from token we will need the secret key
	// secret.getBytes(Charset.forName("UTF-8")) use this to remove
	// io.jsonwebtoken.SignatureException
	private Claims getAllClaimsFromToken(String token) {
		log.info("getAllClaimsFromToken {}", token);
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

	}

	// check if the token is expired
	private Boolean isTokenExpired(String token) {
		log.info("isTokenExpired {}", token);
		// return
		// ZonedDateTime.now(ZoneOffset.UTC).toInstant().isAfter(getExpirationDateFromToken(token));
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date(System.currentTimeMillis()));
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		log.info("generateToken ");
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// defining claims of the token ,like issuer,Expiration,subject,Id
	// secret.getBytes(Charset.forName("UTF-8")) use this to remove
	// io.jsonwebtoken.SignatureException
	private String doGenerateToken(Map<String, Object> claims, String username) {
		// String encodedString =
		// Base64.getEncoder().encodeToString(secret.getBytes(Charset.forName("UTF-8")));
		log.info("doGenerateToken-setExpiration");
		log.info("doGenerateToken-setExpiration ");
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	// validate token
	public Boolean isToken(String token, UserDetails userDetails) {
		log.info("isToken {}", token);
		String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

	}

//	public LoginDTO decodeToken(String jwt) {
//	    Jws<Claims> jws;
//	    try {
//	    	jws=   Jwts.parser()
//                .setSigningKey(secret.getBytes(Charset.forName("UTF-8")))
//                .parseClaimsJws(token);
//	    }catch(ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
//            return null;
//        }
//		return jws;
//       
//}

}