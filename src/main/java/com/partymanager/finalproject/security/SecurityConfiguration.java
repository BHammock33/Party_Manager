package com.partymanager.finalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeHttpRequests()
		.antMatchers("/admin/").hasAnyRole("ADMIN")
		.antMatchers("/register").permitAll()
		.antMatchers("/css/**").permitAll()
		.anyRequest().authenticated().and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/user", true).permitAll();
	}
	
	@Bean("myAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}