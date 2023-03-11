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
@Table(name = "coins")
public class Coin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long coinId;
	//maps coinId to specific characters
	private String type;
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "character_id")
	private PlayerCharacter playerCharacter;
	//should maybe be one to one? 
	// one character can have one list of coins, one list of coins belongs to one character
	//if characters are allowed in multiple parties theyd need multiple lists  

	

	public Coin(Long coinId, String type, Integer quantity, PlayerCharacter playerCharacter) {
		super();
		this.coinId = coinId;
		this.type = type;
		this.quantity = quantity;
		this.playerCharacter = playerCharacter;
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
	
	public PlayerCharacter getPlayerCharacter() {
		return playerCharacter;
	}

	public void setPlayerCharacter(PlayerCharacter playerCharacter) {
		this.playerCharacter = playerCharacter;
	}
	

}
