package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.PartyDto;
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
	@Autowired
	private PartyDtoService partyDtoService;
	@Autowired
	private UserService userService;
	
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
	public Party createParty(PartyDto partyDto) {
		Party party = new Party();
		List<User> emptyUsers = new ArrayList<User>(7);
		List<PlayerCharacter> emptyCharacters = new ArrayList<PlayerCharacter>(7);
		System.out.println(partyDto);
		party.setPartyName(partyDto.getPartyName());
		party.setPartyId(party.getPartyId());
		party.setUsers(emptyUsers);
		party.setCharacters(emptyCharacters);
		partyRepo.save(party);
		System.out.println(party);
		return party;
	}

	public Party findByPartyName(String partyName) {
		Party party = partyRepo.findByPartyName(partyName);
		return party;
	}
	public List<Party> joinParty(Long partyDtoId, Long userId) {
		User foundUser = userService.findById(userId);
		String error = "cant find party";
		Party failedParty = new Party();
		failedParty.setPartyName("Fail");
		PartyDto partyDto = partyDtoService.findById(partyDtoId);
		String partyDtoName = partyDto.getPartyName();
		List<Party> allParties = partyRepo.findAll();
		for(Party party : allParties) {
			String partyName = party.getPartyName();
			if(partyName.equalsIgnoreCase(partyDtoName)) {
				Party partyToBeJoined = party;
				List<Party> userParties = foundUser.getParties();
				userParties.add(partyToBeJoined);
				userService.save(foundUser);
				partyRepo.save(partyToBeJoined);
				System.out.println(partyToBeJoined +"Inside For loop");
				return userParties;
			}else {
				System.out.println(error); 
				List<Party> fail = new ArrayList<>();
				fail.add(failedParty);
				System.out.println("Insdie fail loop");
				return fail;
			}
		}
		
		return null;
	}
	
	public List<PartyDto> createDtoList(){
		List<Party> allParties = partyRepo.findAll();
		List<PartyDto> partyDtoList = new ArrayList<>();
		for(Party party : allParties) {
			PartyDto partyDto = new PartyDto();
			partyDto.setPartyName(party.getPartyName());
			partyDto.setPartyDtoId(party.getPartyId());
			partyDtoList.add(partyDto);
		}
		System.out.println(partyDtoList +"create DTO list");
		return partyDtoList;
	}
//	public Party compareParties(Long partyDtoId) {
//		PartyDto partyDto = partyDtoService.findById(partyDtoId);
//		String partyDtoName = partyDto.getPartyName();
//		List<Party> allParties = partyRepo.findAll();
//		for(Party party : allParties) {
//			String partyName = party.getPartyName();
//			if(partyName.equalsIgnoreCase(partyDtoName)) {
//				Party partyToBeJoined = party;
//				partyToBeJoined.setPartyId(partyToBeJoined.getPartyId());
//				partyToBeJoined.setUsers(partyToBeJoined.getUsers());
//				partyToBeJoined.setPartyName(partyName);
//				partyToBeJoined.setCharacters(partyToBeJoined.getCharacters());
//				return partyToBeJoined;
//			}
//		}
//		return null;
//	}//didn't solve my issue

}
