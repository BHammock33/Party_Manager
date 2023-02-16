package com.partymanager.finalproject.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coins")
public class Coin {

	@Id
	@Column(name = "character_id")
	private Long coinId;
	//maps coinId to specific characters
	private String type;
	private Integer quantity;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "character_id")
	private List<PlayerCharacter> playerCharacters;
	//should maybe be one to one? 
	// one character can have one list of coins, one list of coins belongs to one character
	//if characters are allowed in multiple parties theyd need multiple lists  

	

	public Coin(Long coinId, String type, Integer quantity, List<PlayerCharacter> playerCharacter) {
		super();
		this.coinId = coinId;
		this.type = type;
		this.quantity = quantity;
		this.playerCharacters = playerCharacter;
	}

	public Coin() {
		super();
	}

	
	public Long getCoinId() {
		return coinId;
	}

	public void setCoinId(Long coinId) {
		this.coinId = coinId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public List<PlayerCharacter> getPlayerCharacter() {
		return playerCharacters;
	}

	public void setPlayerCharacter(List<PlayerCharacter> playerCharacter) {
		this.playerCharacters = playerCharacter;
	}
	

}
