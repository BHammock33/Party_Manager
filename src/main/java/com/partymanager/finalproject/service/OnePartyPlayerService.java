package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.OnePartyPlayer;

@Service
public class OnePartyPlayerService {

	@Autowired
	private PartyService partyService;
	@Autowired
	private PlayerCharacterService pcService;
	
	public List<OnePartyPlayer> createOnePartyPlayers(Long partyId){
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
	};return onePartyPlayers;
	}
}
