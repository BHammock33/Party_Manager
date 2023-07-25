package com.partymanager.finalproject.security;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

import com.partymanager.finalproject.domain.User;

@Entity
public class Authorities implements GrantedAuthority {

	private static final long serialVersionUID = 6125802129870572206L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String authority;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	public Authorities() {
	}

	public Authorities(String authority) {
		this.authority = authority;
	}

	public Authorities(String authority, User user) {
		super();
		this.authority = authority;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getDmAuth(Authorities authorities) {
		if(authorities.getAuthority().equalsIgnoreCase("ROLE_DM")){
			return true;
		}
		return false;
	}

}
