package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.repository.PlayerCharacterRepository;
import com.partymanager.finalproject.repository.UserRepository;

@Service
public class PlayerCharacterService {
	
	
	@Autowired
	private PlayerCharacterRepository pcRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private PartyService partyService;
	
	public PlayerCharacter save(PlayerCharacter pc) {
		return pcRepo.save(pc);
	}
	public PlayerCharacter findById(long characterId){
		return pcRepo.findById(characterId).orElse(new PlayerCharacter());
	}
	public List<PlayerCharacter> findAll(){
		return pcRepo.findAll();
	}
	public PlayerCharacter findByName(String name) {
		return pcRepo.findByName(name);
	}
	public List<PlayerCharacter> findByUsername(String username) {
		return userRepo.findUserCharacters(username);
	}
	public PlayerCharacter findUserCharactersByPartyId(Long userId, Long partyId) {
//		User user = userService.findById(userId);
//		Party partyById = partyService.findByPartyId(partyId).orElseThrow();
//		List<PlayerCharacter> userCharacters = user.getCharacters();
//		Optional<PlayerCharacter> optionalCharacterInParty = userCharacters.stream().filter(pc -> pc.getParty().equals(partyById)).findAny();
//		PlayerCharacter characterInParty = optionalCharacterInParty.get();
//		System.out.println("pc Service "+ characterInParty);
		User user = userService.findById(userId);
		Party partyById = partyService.findByPartyId(partyId).orElseThrow();
		List<PlayerCharacter> userCharacters = user.getCharacters();
		List<PlayerCharacter> charactersInParty = new ArrayList<>();
		for(PlayerCharacter pc : userCharacters) {
			if(pc.getParty() == partyById) {
				System.out.println(pc);
				charactersInParty.add(pc);
			}
		}
		PlayerCharacter pcInParty = charactersInParty.get(0);
		System.out.println(pcInParty);
		return pcInParty;
		
	}
	
}
