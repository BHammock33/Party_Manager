package com.partymanager.finalproject.dto;

/**
 * @author benne
 *
 */
public class OnePartyPlayer {
	
	private Long onePartyUserId;
	private String firstName;
	private String characterName;
	private Integer experience;
	private String alignment;
	private Long onePartyID;
	
	
	public OnePartyPlayer() {
		super();
	}
	


	public OnePartyPlayer(Long onePartyUserId, String firstName, String characterName, Integer experience,
			String alignment, Long onePartyID) {
		super();
		this.onePartyUserId = onePartyUserId;
		this.firstName = firstName;
		this.characterName = characterName;
		this.experience = experience;
		this.alignment = alignment;
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
		return characterName;
	}


	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}


	public Integer getExperience() {
		return experience;
	}


	public void setExperience(Integer experience) {
		this.experience = experience;
	}


	public String getAlignment() {
		return alignment;
	}


	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}


	public Long getOnePartyID() {
		return onePartyID;
	}


	public void setOnePartyID(Long onePartyID) {
		this.onePartyID = onePartyID;
	}



	@Override
	public String toString() {
		return "OnePartyPlayer [onePartyUserId=" + onePartyUserId + ", firstName=" + firstName + ", characterName="
				+ characterName + ", experience=" + experience + ", alignment=" + alignment + ", onePartyID="
				+ onePartyID + "]";
	}

	

	
	
	
	
	

}
