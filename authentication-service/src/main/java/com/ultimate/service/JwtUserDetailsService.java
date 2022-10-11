package com.ultimate.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ultimate.model.LoginDTO;
import com.ultimate.repository.LoginRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LoginDTO loginDTO=loginRepository.findByUserCode(username);
		
		if(username==null) {
			throw new UsernameNotFoundException("User not found :" + username);
		}else {
			return User.builder().username(loginDTO.getUserCode()).password(loginDTO.getPassword()).build();
		}
		
//		if ("javainusetoday".equals(username)) {
//			return new User("javainusetoday", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found :" + username);
//		}

	}

}
