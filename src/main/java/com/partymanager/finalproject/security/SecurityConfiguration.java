package com.partymanager.finalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.partymanager.finalproject.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER")
				.anyRequest().authenticated());
		return http.build();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
//		authentication
//			.userDetailsService(userDetailsServiceImpl)
//			.passwordEncoder(passwordEncoder());
//	}
//	
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http.csrf().disable().authorizeHttpRequests()
//		.antMatchers("/admin/").hasAnyRole("ADMIN")
//		.antMatchers("/register").permitAll()
//		.antMatchers("/css/**").permitAll()
//		.antMatchers("/images/**").permitAll()
//		.anyRequest().authenticated().and()
//		.formLogin()
//		.loginPage("/login")
//		.defaultSuccessUrl("/user", true).permitAll();
//	}
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
}
