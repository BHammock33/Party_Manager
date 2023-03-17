package com.partymanager.finalproject.dto;

/**
 * @author benne
 *
 */
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
	
	public PartyDto() {}

	@Override
	public String toString() {
		return "PartyDto [partyName=" + partyName + "]";
	};

	
}
