package com.partymanager.finalproject.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.partymanager.finalproject.domain.Party;



@Entity
@Table(name="notes")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@OneToOne(mappedBy = "note", cascade = CascadeType.ALL)
	private Party party;
	
	private String text;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Note(Long id, Party party, String text) {
		super();
		this.id = id;
		this.party = party;
		this.text = text;
	}

	public Note() {
		super();
	}

	
	
	

}
