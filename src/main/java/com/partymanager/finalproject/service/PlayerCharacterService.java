package com.partymanager.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.repository.PlayerCharacterRepository;
import com.partymanager.finalproject.repository.UserRepository;

@Service
public class PlayerCharacterService {
	
	
	@Autowired
	private PlayerCharacterRepository pcRepo;
	@Autowired
	private UserRepository userRepo;
	
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
	
}
