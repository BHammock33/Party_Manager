package com.partymanager.finalproject.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.partymanager.finalproject.dto.Note;

@Entity
@Table(name = "party")
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long partyId;
	@Column(unique = true)
	private String partyName;
	@ManyToMany(mappedBy = "parties")
	private List<User> users;
	// Many Users Can have Many Parties, Many Parties can have Many Users
	@OneToMany(mappedBy = "party", cascade = CascadeType.REMOVE)
	private List<PlayerCharacter> characters;
	// one character can only be in one party, parties can have many characters
	@OneToOne
	@JoinColumn(name = "note_id", referencedColumnName = "id")
	private Note note;

	public Party(Long partyId, String partyName, List<User> users, List<PlayerCharacter> characters, Note note) {
		// super();
		this.partyId = partyId;
		this.partyName = partyName;
		this.users = users;
		this.characters = characters;
		this.note = note;
	}

	// no arg constructor
	public Party() {
		// super();
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<PlayerCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(List<PlayerCharacter> characters) {
		this.characters = characters;
	}
	
	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Party other = (Party) obj;
		return Objects.equals(partyId, other.partyId);
	}

}
