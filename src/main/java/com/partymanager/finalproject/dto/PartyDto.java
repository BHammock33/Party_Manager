package com.partymanager.finalproject.dto;

public class PartyDto {
	
	private String partyName;

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public PartyDto(String partyName) {
		super();
		this.partyName = partyName;
	}
	
	public PartyDto() {};

}
