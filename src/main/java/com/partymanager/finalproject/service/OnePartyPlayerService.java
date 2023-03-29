package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.CoinModifier;
import com.partymanager.finalproject.dto.OnePartyPlayer;


@Service
public class OnePartyPlayerService {

	@Autowired
	private PartyService partyService;
	@Autowired
	private PlayerCharacterService pcService;
	@Autowired
	private UserService userService;

	// Take All of the users in a party and convert them to "single party users"
	// This allows for easier thymeleaf mapping on the model 
	// Filters down to the character the user has in the specific party
	public List<OnePartyPlayer> createOnePartyPlayers(Long partyId) {
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		List<User> players = party.getUsers();
		List<OnePartyPlayer> onePartyPlayers = new ArrayList<>();

		for (User player : players) {
			OnePartyPlayer onePartyPlayer = new OnePartyPlayer();
			onePartyPlayer.setOnePartyUserId(player.getUserId());
			onePartyPlayer.setFirstName(player.getFirstName());
			onePartyPlayer.setOnePartyID(partyId);
			Long userId = player.getUserId();
			try {
				PlayerCharacter characterInParty = pcService.findUserCharactersByPartyId(userId, partyId);
				String characterName = characterInParty.getName();
				String characterAlignment = characterInParty.getAlignment();
				Integer characterExperience = characterInParty.getXp();
				onePartyPlayer.setCharacterName(characterName);
				onePartyPlayer.setExperience(characterExperience);
				onePartyPlayer.setLevel(characterInParty.getLevel());
				onePartyPlayer.setAlignment(characterAlignment);
				onePartyPlayer.setGold(characterInParty.getGold());
				onePartyPlayer.setSilver(characterInParty.getSilver());
				onePartyPlayer.setCopper(characterInParty.getCopper());
				onePartyPlayer.setOnePartyCharacterId(characterInParty.getCharacterId());
				Integer xpToLevel = pcService.xpToNextLevel(onePartyPlayer.getOnePartyCharacterId());
				onePartyPlayer.setXpToLevel(xpToLevel);
				pcService.levelUpCharacter(onePartyPlayer.getOnePartyCharacterId());
				pcService.coinConversion(onePartyPlayer.getOnePartyCharacterId());
			} catch (Exception e) {
				System.out.println("No character in party");
			}
			onePartyPlayers.add(onePartyPlayer);
		}
		;
		return onePartyPlayers;
	}
	
	// logic for adding/spending coins
	public void addGold(String characterName, CoinModifier amount) {
		PlayerCharacter pc = pcService.findByName(characterName);
		Integer goldAdd = amount.getAmount();
		Integer newGold = goldAdd + (pc.getGold());
		pc.setGold(newGold);
		pcService.coinConversion(pc.getCharacterId());
		pcService.save(pc);
		User user = pc.getUser();
		userService.save(user);
	}
	public void addSilver(String characterName, CoinModifier amount) {
		PlayerCharacter pc = pcService.findByName(characterName);
		Integer silverAdd = amount.getAmount();
		Integer newSilver = silverAdd + (pc.getSilver());
		pc.setSilver(newSilver);
		pcService.coinConversion(pc.getCharacterId());
		pcService.save(pc);
		User user = pc.getUser();
		userService.save(user);
	}
	public void addCopper(String characterName, CoinModifier amount) {
		PlayerCharacter pc = pcService.findByName(characterName);
		Integer copperAdd = amount.getAmount();
		Integer newCopper = copperAdd + (pc.getCopper());
		pc.setCopper(newCopper);
		pcService.coinConversion(pc.getCharacterId());
		pcService.save(pc);
		User user = pc.getUser();
		userService.save(user);
	}
	public void prepForDeletion(Long partyId) {
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		List<User> players = party.getUsers();
		List<Long> userIds = new ArrayList<>();
		for(User player : players) {
			Long playerId = player.getUserId();
			userIds.add(playerId);
		}
		for(Long userId : userIds) {
			partyService.removeFromParty(userId, partyId);
			User userById = userService.findById(userId);
			userService.save(userById);
		}
		partyService.deleteParty(partyId);
	}
}
