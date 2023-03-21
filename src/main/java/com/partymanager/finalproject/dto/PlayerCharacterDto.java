package com.partymanager.finalproject.dto;

public class PlayerCharacterDto {
	
	private String name;
	private Integer xp;
	private String alignment;
	
	public PlayerCharacterDto() {
		super();
	}
	public PlayerCharacterDto(String name, Integer xp, String alignment) {
		super();
		this.name = name;
		this.xp = xp;
		this.alignment = alignment;
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
	
	

}
