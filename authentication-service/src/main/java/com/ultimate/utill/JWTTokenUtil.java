package com.ultimate.utill;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;


	@Value("${jwt.secret}")
	private String secret;

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		System.out.println("getUsernameFromToken :"+token);
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expire date from jwt token
	public Date getExpirationDateFromToken(String token) {
		System.out.println("getExpirationDateFromToken :"+token);
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// this method is called from getUsernameFromToken()
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		System.out.println("getClaimFromToken :"+token);
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieving any information from token we will need the secret key
//	secret.getBytes(Charset.forName("UTF-8")) use this to remove io.jsonwebtoken.SignatureException: 

	private Claims getAllClaimsFromToken(String token) {
		System.out.println("getAllClaimsFromToken :"+token);
		
		return Jwts.parser().setSigningKey(secret.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token)
				.getBody();
		
		
	}

	// check if the token is expired
	private Boolean isTokenExpired(String token) {
		System.out.println("isTokenExpired :"+token);
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date(System.currentTimeMillis()));
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		System.out.println("generateToken :");
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// defining claims of the token ,like issuer,Expiration,subject,Id
//	secret.getBytes(Charset.forName("UTF-8")) use this to remove 
	private String doGenerateToken(Map<String, Object> claims, String username) {
		String encodedString = Base64.getEncoder().encodeToString(secret.getBytes(Charset.forName("UTF-8")));
		System.out.println("doGenerateToken :");
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512,encodedString ).compact();

	}

	// validate token
	public Boolean isToken(String token, UserDetails userDetails) {
		System.out.println("isToken :"+token);
//		final String username;
//		boolean validation =false;
//		try {
//			username = getUsernameFromToken(token);
//			if(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//		    	validation = true;
//		} catch (ExpiredJwtException e) {
//		    System.out.println(" Token expired ");
//		} catch (SignatureException e) {
////		    Logger.getLogger(JWTController.class.getName()).log(Level.ERROR, e);
//			e.printStackTrace();
//		} catch(Exception e){
//		    System.out.println(" Some other exception in JWT parsing ");
//		}
		try {
		Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		return true;
	} catch (SignatureException ex) {
		System.err.println("Invalid JWT signature");
	} catch (MalformedJwtException ex) {
		System.err.println("Invalid JWT token");
	} catch (ExpiredJwtException ex) {
		System.err.println("Expired JWT token");
	} catch (UnsupportedJwtException ex) {
		System.err.println("Unsupported JWT token");
	} catch (IllegalArgumentException ex) {
		System.err.println("JWT claims string is empty.");
	}
	return false;
		
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
// }

}