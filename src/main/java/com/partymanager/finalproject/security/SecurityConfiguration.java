package com.partymanager.finalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.partymanager.finalproject.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authz) -> authz
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/user/**").hasRole("USER")
//				.anyRequest().authenticated());
//		return http.build();
//	}
	
	@Bean
	public InMemoryUserDetailsManager userDertailsService() {
		UserDetails user1 = User.withUsername("user1").password(passwordEncoder().encode("user1password")).roles("USER").build();
		System.out.println(user1);
		UserDetails user2 = User.withUsername("User2").password(passwordEncoder().encode("user2password")).roles("USER").build();
		System.out.println(user2);
		UserDetails user3 = User.withUsername("Admin").password(passwordEncoder().encode("adminpassword")).roles("ADMIN").build();
		System.out.println(user3);
		return new InMemoryUserDetailsManager(user1, user2, user3);
	}
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/admin/**").hasAnyRole("ADMIN")
//		.antMatchers("/anonymous*").anonymous()
//		.antMatchers("/login*").permitAll()
//		.antMatchers("/register*").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/login.html")
//		.loginProcessingUrl("/perform_login")
//		.defaultSuccessUrl("/user.html", true)
//		.failureUrl("/register.html")
//		.failureHandler(authenticationFailureHandler())
//		.and()
//		.logout()
//		.logoutUrl("/perform_logout")
//		.deleteCookies("JSESSIONID")
//		.logoutSuccessHandler(logoutSuccessHandler());
//		return http.build();
//		
//	}
//
//	private LogoutSuccessHandler logoutSuccessHandler() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private AuthenticationFailureHandler authenticationFailureHandler() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication
			.userDetailsService(userDetailsServiceImpl)
			.passwordEncoder(passwordEncoder());
	}
	
	
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeHttpRequests()
		.antMatchers("/admin/").hasAnyRole("ADMIN")
		.antMatchers("/register*").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/images/**").permitAll()
		.anyRequest().authenticated().and()
		.formLogin()
		.loginPage("/login").permitAll()
		.defaultSuccessUrl("/user/{userId}", true).permitAll();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
}
