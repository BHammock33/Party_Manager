package com.partymanager.finalproject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "characters")
public class PlayerCharacter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long characterId;
	private String name;
	private Integer xp;
	private String allignment;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(targetEntity = com.partymanager.finalproject.domain.Coin.class)
	@JoinColumn(name = "coin_id")
	private List<Coin> coins;

	public PlayerCharacter(Long characterId, String name, Integer xp, String allignment, User user, List<Coin> coins) {
		super();
		this.characterId = characterId;
		this.name = name;
		this.xp = xp;
		this.allignment = allignment;
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

	public String getAllignment() {
		return allignment;
	}

	public void setAllignment(String allignment) {
		this.allignment = allignment;
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
