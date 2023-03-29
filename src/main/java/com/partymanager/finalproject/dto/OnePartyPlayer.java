package com.partymanager.finalproject.dto;

//used to put a user onto the party specific model, allows for easier grabbing of properties by thymeleaf
//a user can have many characters/parties so this allows for filtering down to just one party and the character in that party
//shares properties of user/character needed for one specific party
public class OnePartyPlayer {

	// id of one party user = id of user
	private Long onePartyUserId;
	// id of party specific to the one party user
	private Long onePartyID;
	// one party character id = id of character in party
	private Long onePartyCharacterId;
	// user first name
	private String firstName;
	// player character name
	private String characterName;
	// player character xp
	private Integer experience;
	// player charcter alignment
	private String alignment;
	// used to give the xp needed to reach next level
	private Integer xpToLevel;
	// coins
	private Integer gold;
	private Integer silver;
	private Integer copper;
	// level of player character in party
	private Integer level;

	public OnePartyPlayer() {
		super();
	}

	public OnePartyPlayer(Long onePartyUserId, String firstName, String characterName, Integer experience,
			String alignment, Long onePartyID, Long onePartyCharacterId, Integer xpToLevel, Integer gold,
			Integer silver, Integer copper, Integer level) {
		super();
		this.onePartyUserId = onePartyUserId;
		this.firstName = firstName;
		this.characterName = characterName;
		this.experience = experience;
		this.alignment = alignment;
		this.onePartyID = onePartyID;
		this.onePartyCharacterId = onePartyCharacterId;
		this.xpToLevel = xpToLevel;
		this.gold = gold;
		this.silver = silver;
		this.copper = copper;
		this.level = level;
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

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getSilver() {
		return silver;
	}

	public void setSilver(Integer silver) {
		this.silver = silver;
	}

	public Integer getCopper() {
		return copper;
	}

	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getOnePartyCharacterId() {
		return onePartyCharacterId;
	}

	public void setOnePartyCharacterId(Long onePartyCharacterId) {
		this.onePartyCharacterId = onePartyCharacterId;
	}

	public Integer getXpToLevel() {
		return xpToLevel;
	}

	public void setXpToLevel(Integer xpToLevel) {
		this.xpToLevel = xpToLevel;
	}

	@Override
	public String toString() {
		return "OnePartyPlayer [onePartyUserId=" + onePartyUserId + ", firstName=" + firstName + ", characterName="
				+ characterName + ", experience=" + experience + ", alignment=" + alignment + ", onePartyID="
				+ onePartyID + ", onePartyCharacterId=" + onePartyCharacterId + ", xpToLevel=" + xpToLevel + ", gold="
				+ gold + ", silver=" + silver + ", copper=" + copper + ", level=" + level + "]";
	}

}
