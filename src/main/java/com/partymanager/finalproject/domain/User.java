package com.partymanager.finalproject.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String username;
	private String password;
	private String name;
	@OneToMany(mappedBy = "user")
	private List<PlayerCharacter> characters;
	//one user to many characters
	@ManyToMany(mappedBy = "users", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<Party> parties;
	//many users to many parties
	
	
	
	public User(Long userId, String username, String password, String name, List<PlayerCharacter> characters, List<Party> parties) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.characters = characters;
		this.parties = parties;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	
	
	

}
