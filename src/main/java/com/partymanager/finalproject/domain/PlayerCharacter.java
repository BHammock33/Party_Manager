package com.partymanager.finalproject.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "characters")
public class PlayerCharacter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long characterId;
	//will set to character_id
	private String name;
	private Integer xp;
	@Column(name = "alignment")
	private String alignment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	//joins many characters under one user_id

	@OneToMany(mappedBy="playerCharacter")
	private List<Coin> coins;
	//one list of coins mapped to one character
	//need to change because a collection on one side
	//one character to many coins 
	// Issue Still here, referenced property unknown Java.util.List.playerCharacter 

	@ManyToOne
	@JoinColumn(name = "party_id")
	private Party party;
	//one character can only be in one party, parties can have many characters

	public PlayerCharacter(Long characterId, String name, Integer xp, String alignment, User user, List<Coin> coins) {
		super();
		this.characterId = characterId;
		this.name = name;
		this.xp = xp;
		this.alignment = alignment;
		this.user = user;
		this.coins = coins;
	}

	public PlayerCharacter() {
		super();
	}

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getXp() {
		return xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Coin> getCoins() {
		return coins;
	}

	public void setCoins(List<Coin> coins) {
		this.coins = coins;
	}

}
