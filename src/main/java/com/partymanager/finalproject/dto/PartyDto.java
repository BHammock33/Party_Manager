package com.partymanager.finalproject.dto;

/**
 * @author benne
 *
 */

public class PartyDto {
	
	private String partyName;
	private Long partyDtoId;
	

	public Long getPartyDtoId() {
		return partyDtoId;
	}

	public void setPartyDtoId(Long partyDtoId) {
		this.partyDtoId = partyDtoId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public PartyDto(String partyName, Long partyDtoId) {
		super();
		this.partyName = partyName;
		this.partyDtoId = partyDtoId;
	}
	
	public PartyDto() {}

	@Override
	public String toString() {
		return "PartyDto [partyName=" + partyName + ", partyDtoId=" + partyDtoId + "]";
	}

	

	
}
