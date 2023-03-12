package com.partymanager.finalproject.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.partymanager.finalproject.domain.User;

@Entity
public class Authorities implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6125802129870572206L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String authority;
	
	@ManyToMany(mappedBy="authorities")
	private Set<User> users = new HashSet<>();
	
	public Authorities() {}
	
	

	public Authorities(Long id, String authority, Set<User> users) {
		super();
		this.id = id;
		this.authority = authority;
		this.users = users;
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	

	
	
	
}
