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
	
	private Long characterId;
	private String name;
	private Integer xp;
	private String allignment;
	private User user;
	private List<Coin> coins;
//	private Integer gold;
//	private Integer copper;
//	private Integer silver;
//	private Integer etherium;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ManyToOne
	@JoinColumn(name = "user_id")
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
	
//	public Integer getGold() {
//		return gold;
//	}
//	public void setGold(Integer gold) {
//		this.gold = gold;
//	}
//	public Integer getCopper() {
//		return copper;
//	}
//	public void setCopper(Integer copper) {
//		this.copper = copper;
//	}
//	public Integer getSilver() {
//		return silver;
//	}
//	public void setSilver(Integer silver) {
//		this.silver = silver;
//	}
//	public Integer getEtherium() {
//		return etherium;
//	}
//	public void setEtherium(Integer etherium) {
//		this.etherium = etherium;
//	}
	
	

}
