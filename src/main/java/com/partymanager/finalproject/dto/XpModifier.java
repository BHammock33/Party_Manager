package com.partymanager.finalproject.dto;

// same as coinModifier, put on to the model to allow for easier changing
// of character XP
public class XpModifier {

	private Integer amount = 0;

	public XpModifier(Integer amount) {
		super();
		this.amount = amount;
	}

	public XpModifier() {
		super();
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
