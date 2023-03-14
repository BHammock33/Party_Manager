package com.partymanager.finalproject.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.partymanager.finalproject.domain.User;

public class CustomSecurityUser extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -447844476945983405L;
	
	public CustomSecurityUser() {}
	
	public CustomSecurityUser(User user) {
		this.setUserId(user.getUserId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getfirstName());
		this.setLastName(user.getLastName());
		//this.setCharacters(user.getCharacters());
		//this.setParties(user.getParties());
		this.setAuthorities(user.getAuthorities());
		
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	


}
