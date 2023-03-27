package com.partymanager.finalproject.domain;

import javax.persistence.Column;
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

	@ManyToOne
	@JoinColumn(name = "party_id")
	private Party party;
	//one character can only be in one party, parties can have many characters
	private Integer gold;
	private Integer silver;
	private Integer copper;
	//10 copper for 1 silver
	//100 copper for 1 gold
	//10 silver for 1 gold 
	private Integer level;
	private Integer xpToLevel;

	

	public PlayerCharacter() {
		super();
	}

	

	public PlayerCharacter(Long characterId, String name, Integer xp, String alignment, User user, Party party,
			Integer gold, Integer silver, Integer copper, Integer level, Integer xpToLevel) {
		super();
		this.characterId = characterId;
		this.name = name;
		this.xp = xp;
		this.alignment = alignment;
		this.user = user;
		this.party = party;
		this.gold = gold;
		this.silver = silver;
		this.copper = copper;
		this.level = level;
		this.xpToLevel = xpToLevel;
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

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}



	public Integer getGold() {
		return gold;
	}



	public void setGold(Integer gold) {
		this.gold = gold;
	}



	public Integer getSilver() {
		return silver;
	}



	public void setSilver(Integer silver) {
		this.silver = silver;
	}



	public Integer getCopper() {
		return copper;
	}



	public void setCopper(Integer copper) {
		this.copper = copper;
	}
	



	public Integer getLevel() {
		return level;
	}



	public void setLevel(Integer level) {
		this.level = level;
	}

	


	public Integer getXpToLevel() {
		return xpToLevel;
	}



	public void setXpToLevel(Integer xpToLevel) {
		this.xpToLevel = xpToLevel;
	}



	@Override
	public String toString() {
		return "PlayerCharacter [characterId=" + characterId + ", name=" + name + ", xp=" + xp + ", alignment="
				+ alignment + ", user=" + user + ", party=" + party + ", gold=" + gold + ", silver=" + silver
				+ ", copper=" + copper + ", level=" + level + ", xpToLevel=" + xpToLevel + "]";
	}



	


	
	
	

	

}
