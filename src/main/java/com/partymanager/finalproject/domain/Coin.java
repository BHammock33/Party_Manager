package com.partymanager.finalproject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "coins")
public class Coin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long coinId;
	private String type;
	private Integer quantity;
	
	@OneToMany(mappedBy = "coins")
	private List<PlayerCharacter> playerCharacters;

	

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
