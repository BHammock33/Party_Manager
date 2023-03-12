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
		this.setUserId(getUserId());
		this.setUsername(getUsername());
		this.setPassword(getPassword());
		this.setFirstName(getfirstName());
		this.setLastName(getLastName());
		this.setCharacters(getCharacters());
		this.setParties(getParties());
		this.setAuthorities(getAuthorities());
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
