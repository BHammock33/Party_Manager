package com.partymanager.finalproject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;	

@Entity
@Table(name = "party")
public class Party {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long partyId;
	private String partyName;
	@ManyToMany(mappedBy = "parties"  )
	private List<User> users;
	//Many Users Can have Many Parties, Many Parties can have Many Users
	@OneToMany(mappedBy = "party")
	private List<PlayerCharacter> characters;
	//one character can only be in one party, parties can have many characters
	
	
	public Party(Long partyId, String partyName, List<User> users, List<PlayerCharacter> characters) {
		super();
		this.partyId = partyId;
		this.partyName = partyName;
		this.users = users;
		this.characters = characters;
	}
	
	public Party() {
		super();
	}
	

	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<PlayerCharacter> getCharacters() {
		return characters;
	}
	public void setCharacters(List<PlayerCharacter> characters) {
		this.characters = characters;
	}
	
	

}
