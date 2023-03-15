package com.partymanager.finalproject.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.partymanager.finalproject.security.Authorities;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "characters")
	@OneToMany(mappedBy = "user")
	private List<PlayerCharacter> characters;
	//one user to many characters
	
	@Column(name = "parties")
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "user_parties", joinColumns = {@JoinColumn(referencedColumnName = "userId")}, inverseJoinColumns = {@JoinColumn(referencedColumnName = "partyID")})
	private List<Party> parties;
	//many users to many parties
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities = new HashSet<>();
	
	
	public User(Long userId, String username, String password, String firstName, String lastName, List<PlayerCharacter> characters, List<Party> parties, Set<Authorities> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.characters = characters;
		this.parties = parties;
		this.authorities = authorities;
	}
	
	
	public User() {
		super();
	}


	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<PlayerCharacter> getCharacters() {
		return characters;
	}
	public void setCharacters(List<PlayerCharacter> characters) {
		this.characters = characters;
	}


	public List<Party> getParties() {
		return parties;
	}


	public void setParties(List<Party> parties) {
		this.parties = parties;
	}


	public Set<Authorities> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", characters=" + characters + ", parties=" + parties
				+ ", authorities=" + authorities + "]";
	}

	


	
	
	
	
	
	
	

}
