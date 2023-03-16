package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.repository.PartyRepository;
import com.partymanager.finalproject.repository.PlayerCharacterRepository;
import com.partymanager.finalproject.repository.UserRepository;

@Service
public class PartyService {
	
	@Autowired
	private PartyRepository partyRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PlayerCharacterRepository pCRepo;
	
	public List<Party> findAll() {
		return partyRepo.findAll();
	}

	public Optional<Party> findByPartyId(Long partyId) {
		return partyRepo.findById(partyId);
	}
	public List<Party> createTestParties(){
		List<Party> parties = new ArrayList<>();
		Party testParty = new Party();
		Party testParty2 = new Party();
		Party testParty3 = new Party();
		testParty.setPartyName("Washing Machine");
		testParty2.setPartyName("Band of Boobs");
		testParty3.setPartyName("The Two Crew");
		parties.add(testParty);
		parties.add(testParty2);
		parties.add(testParty3);
		return parties;
		
	}

	public Party save(Party party) {
		return partyRepo.save(party);
		
	}
	public Party createParty(String partyName) {
		Party party = new Party();
		List<User> emptyUsers = new ArrayList<User>(7);
		List<PlayerCharacter> emptyCharacters = new ArrayList<PlayerCharacter>(7);
		party.setPartyName(partyName);
		party.setPartyId(party.getPartyId());
		party.setUsers(emptyUsers);
		party.setCharacters(emptyCharacters);
		return party;
	}
	

}
