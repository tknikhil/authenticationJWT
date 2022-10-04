package com.ultimate.config;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ultimate.service.JwtUserDetailsService;
import com.ultimate.utill.JWTTokenUtil;


@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken=null;

		// JWT Token is in the form "Bearer Token".Remove Bearer word and get the token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken= requestTokenHeader.substring(7);
			System.out.println("doFilterInternal :"+requestTokenHeader);
			System.out.println(jwtToken);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			LOGGER.warn("JWT Token does not begin with Bearer String");
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
