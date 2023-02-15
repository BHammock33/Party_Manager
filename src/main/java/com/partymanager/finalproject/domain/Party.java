package com.partymanager.finalproject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;	

@Entity
@Table(name = "party")
public class Party {
	
	private Long partyId;
	private String partyName;
	private List<User> users;
	private List<PlayerCharacter> characters;

}
