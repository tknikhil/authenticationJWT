package com.ultimate.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ultimate.model.JWTRequest;
import com.ultimate.model.JWTResponse;
import com.ultimate.service.JwtUserDetailsService;
import com.ultimate.utill.JWTTokenUtil;

@RestController
@CrossOrigin
public class JWTAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService jwtUserDeatilsService;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest)throws Exception{
		authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
		
		final UserDetails userDetails =jwtUserDeatilsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String token=jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JWTResponse(token));
		
	}

	private void authenticate(String username, String password)throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
