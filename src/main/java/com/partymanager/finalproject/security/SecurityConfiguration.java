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
public class SecurityConfiguration {//extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authz) -> authz
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/user/**").hasRole("USER")
//				.anyRequest().authenticated());
//		return http.build();
//	}
	
	//Ercan Tweaks
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http)  throws Exception{
			return http.getSharedObject(AuthenticationManagerBuilder.class)
					.userDetailsService(userDetailsServiceImpl)
					.passwordEncoder(passwordEncoder())
					.and()
					.build();
		}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/home")
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/login")
			.permitAll()
		.and()
			.authorizeHttpRequests()
			.antMatchers("/admin").hasAnyRole("ADMIN")
			.antMatchers("/DM").hasAnyRole("DM")
			.antMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
			.antMatchers("/index", "/register").permitAll()
			.antMatchers("/home").permitAll()
			.anyRequest()
			.authenticated();
		
		return http.build();
	}
	
//	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
//		authentication
//			.userDetailsService(userDetailsServiceImpl)
//			.passwordEncoder(passwordEncoder());
//	}
//	
//	
//	protected void configure(HttpSecurity http) throws Exception{
//		http.csrf().disable().authorizeHttpRequests()
//		.antMatchers("/admin/").hasAnyRole("ADMIN")
//		.antMatchers("/DM/").hasAnyRole("DM")
//		.antMatchers("/register").permitAll()
//		.antMatchers("/css/**").permitAll()
//		.antMatchers("/images/**").permitAll()
//		.anyRequest().authenticated().and()
//		.formLogin()
//		.loginPage("/login")
//		.defaultSuccessUrl("/home", true).permitAll();
//	}
//	
//	@Bean
//	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
}
