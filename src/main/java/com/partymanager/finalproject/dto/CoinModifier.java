package com.partymanager.finalproject.dto;

//used to put on model to perform logic of earning/spending coins
public class CoinModifier {

	private Integer amount = 0;

	public CoinModifier() {
		super();
	}

	public CoinModifier(Integer amount) {
		super();
		this.amount = amount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
