package com.ultimate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ultimate.model.LoginDTO;
import com.ultimate.repository.LoginRepository;

/**
 * <p>
 * JWTUserDetailsService implements the Spring Security UserDetailsService
 * interface. It overrides the loadUserByUsername for fetching user details from
 * the database using the username. The Spring Security Authentication Manager
 * calls this method for getting the user details from the database when
 * authenticating the user details provided by the user.
 * </p>
 * 
 * @author Nikhil TK
 * 
 * @see UserDetailsService
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LoginDTO loginDTO = loginRepository.findByUserCode(username);

		if (username == null) {
			throw new UsernameNotFoundException("User not found :" + username);
		} else {
//			with username and password we are also passing authorities to that user coming from springframework 
			return User.builder().username(loginDTO.getUserCode()).password(loginDTO.getPassword())
					.authorities(new SimpleGrantedAuthority("ultimate")).build();
		}

	}

}
