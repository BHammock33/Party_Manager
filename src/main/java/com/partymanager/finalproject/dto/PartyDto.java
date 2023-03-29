package com.partymanager.finalproject.dto;

//used for the creation of parties to pass party name into party entity
//simplifies party creation because the DTO can be put onto the model without having to 
// account for the many null values that would be part of party
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

	public PartyDto() {
	}

	@Override
	public String toString() {
		return "PartyDto [partyName=" + partyName + ", partyDtoId=" + partyDtoId + "]";
	}

}
