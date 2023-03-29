package com.partymanager.finalproject.dto;

// same principle as the party DTO, can be more easily be put onto the model
// and then transfer the properties into the actual character entity
// avoids dealing with null user/partyId
public class PlayerCharacterDto {

	private String name;
	private Integer xp;
	private String alignment;
	private String partyName;
	private Integer gold;
	private Integer silver;
	private Integer copper;
	private Integer level;

	public PlayerCharacterDto(String name, Integer xp, String alignment, String partyName, Integer gold, Integer silver,
			Integer copper, Integer level) {
		super();
		this.name = name;
		this.xp = xp;
		this.alignment = alignment;
		this.partyName = partyName;
		this.gold = gold;
		this.silver = silver;
		this.copper = copper;
		this.level = level;
	}

	public PlayerCharacterDto() {
		super();
	}

	public PlayerCharacterDto(Integer xp) {
		super();
		this.xp = xp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getXp() {
		return xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
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

}
