package com.partymanager.finalproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coins")
public class Coin {
	
	public Long coinId;
	public String type;
	public Integer quantity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	

}
