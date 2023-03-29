package com.partymanager.finalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.partymanager.finalproject.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsServiceImpl)
				.passwordEncoder(passwordEncoder()).and().build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.formLogin().loginPage("/login")
				.defaultSuccessUrl("/home").permitAll()
				.and()
				.logout()
					.logoutSuccessUrl("/login").permitAll()
				.and()
					.authorizeHttpRequests()
					.antMatchers("/admin").hasAnyRole("ADMIN")
					.antMatchers("/DM").hasAnyRole("DM")
					.antMatchers("/", "/css/**", "/js/**", "/images/**", "/error/**").permitAll()
					.antMatchers("/index", "/register").permitAll()
					.antMatchers("/home").permitAll()
					.anyRequest()
					.authenticated();

		return http.build();
	}

}
