package com.partymanager.finalproject.dto;

public class OnePartyPlayer {
	
	private Long onePartyUserId;
	private String firstName;
	private String CharacterName;
	private Integer Experience;
	private String Alighment;
	private Long onePartyID;
	
	
	public OnePartyPlayer() {
		super();
	}


	public OnePartyPlayer(Long onePartyUserId, String firstName, String characterName, Integer experience,
			String alighment, Long onePartyID) {
		super();
		this.onePartyUserId = onePartyUserId;
		this.firstName = firstName;
		CharacterName = characterName;
		Experience = experience;
		Alighment = alighment;
		this.onePartyID = onePartyID;
	}


	public Long getOnePartyUserId() {
		return onePartyUserId;
	}


	public void setOnePartyUserId(Long onePartyUserId) {
		this.onePartyUserId = onePartyUserId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getCharacterName() {
		return CharacterName;
	}


	public void setCharacterName(String characterName) {
		CharacterName = characterName;
	}


	public Integer getExperience() {
		return Experience;
	}


	public void setExperience(Integer experience) {
		Experience = experience;
	}


	public String getAlighment() {
		return Alighment;
	}


	public void setAlighment(String alighment) {
		Alighment = alighment;
	}


	public Long getOnePartyID() {
		return onePartyID;
	}


	public void setOnePartyID(Long onePartyID) {
		this.onePartyID = onePartyID;
	}
	
	
	
	

}
