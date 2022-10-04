package com.ultimate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{
	
	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private UserDetailsService jwtDetailsService;
	@Autowired
	private JWTRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(jwtDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
//	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//	    authenticationManagerBuilder.userDetailsService(jwtDetailsService).passwordEncoder(passwordEncoder());
//	}
	
//	@Bean
//	AuthenticationManager ldapAuthenticationManager(BaseLdapPathContextSource contextSource) {}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	  @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
          return authenticationConfiguration.getAuthenticationManager();
      }
	
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		  httpSecurity.csrf().disable()
			// dont authenticate this particular request
			.authorizeRequests().antMatchers("/authenticate").permitAll().
			// all other requests need to be authenticated
			anyRequest().authenticated().and().
			// make sure we use stateless session; session won't be used to
			// store user's state.
			exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
			return httpSecurity.build();
	  }

}
