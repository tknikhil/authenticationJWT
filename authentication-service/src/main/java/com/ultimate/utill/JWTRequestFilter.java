package com.ultimate.utill;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ultimate.service.JwtUserDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>For any incoming request this Filter class gets executed. It checks if the request has a valid JWT token. 
 * If it has a valid JWT Token then it sets the Authentication in the context, to specify that the current user
 *  is authenticated</p>
 * 
 * @author Nikhil TK
 * @see OncePerRequestFilter
 * @see UsernamePasswordAuthenticationToken
 */

@Component
@Slf4j
public class JWTRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("doFilterInternal");
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		// JWT Token is in the form "Bearer Token".Remove Bearer word and get the token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			log.info("doFilterInternal- if");
			jwtToken = requestTokenHeader.substring(7);

			try {
				log.info("doFilterInternal- try");
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
//				jwtTokenUtil.decodeToken(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			log.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the Token validate it
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.isToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context,we specify that the current
				// user is authenticated.So it passes the Spring Security configuration
				// successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
