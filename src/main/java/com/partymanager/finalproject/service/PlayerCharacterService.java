package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.PlayerCharacterDto;
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

	public PlayerCharacter findById(long characterId) {
		return pcRepo.findById(characterId).orElse(new PlayerCharacter());
	}

	public List<PlayerCharacter> findAll() {
		return pcRepo.findAll();
	}

	public PlayerCharacter findByName(String name) {
		return pcRepo.findByName(name);
	}

	public List<PlayerCharacter> findByUsername(String username) {
		return userRepo.findUserCharacters(username);
	}

	public PlayerCharacter findUserCharactersByPartyId(Long userId, Long partyId) {
		User user = userService.findById(userId);
		Party partyById = partyService.findByPartyId(partyId).orElseThrow();
		List<PlayerCharacter> userCharacters = user.getCharacters();
		List<PlayerCharacter> charactersInParty = new ArrayList<>();
		for (PlayerCharacter pc : userCharacters) {
			if (pc.getParty() == partyById) {
				System.out.println(pc);
				charactersInParty.add(pc);
			}
		}
		PlayerCharacter pcInParty = charactersInParty.get(0);
		System.out.println(pcInParty);
		return pcInParty;

	}

	public Boolean checkIfInParty(Long partyId, Long userId) {
		User user = userService.findById(userId);
		List<PlayerCharacter> userCharacters = user.getCharacters();
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		List<PlayerCharacter> partyCharacters = party.getCharacters();
		Boolean characterInParty = partyCharacters.stream().anyMatch(userCharacters::contains);
		System.out.println("characterInParty");
		return characterInParty;
	}
	public PlayerCharacter convertDtoToPc(PlayerCharacterDto playerCharacterDto, User user) {
		PlayerCharacter playerCharacter = new PlayerCharacter();
		
		
		String partyName = playerCharacterDto.getPartyName();
		Party party = partyService.findByPartyName(partyName);
		playerCharacter.setCharacterId(playerCharacter.getCharacterId());
		playerCharacter.setName(playerCharacterDto.getName());
		playerCharacter.setXp(playerCharacterDto.getXp());
		playerCharacter.setAlignment(playerCharacterDto.getAlignment());
		playerCharacter.setParty(party);
		playerCharacter.setUser(user);
		playerCharacter.setGold(playerCharacterDto.getGold());
		playerCharacter.setSilver(playerCharacterDto.getSilver());
		playerCharacter.setCopper(playerCharacterDto.getCopper());
		playerCharacter.setLevel(playerCharacterDto.getLevel());
		partyService.save(party);
		
		return playerCharacter;
	}
	
	//Breaks down in the try block
	
//	public List<OnePartyPlayer> convertPlayersToOneParty(Long partyId){
//		Party party = partyService.findByPartyId(partyId).orElseThrow();
//		List<User> players = party.getUsers();
//		List<OnePartyPlayer> onePartyPlayers = new ArrayList<>();
//		PlayerCharacterService pcService = new PlayerCharacterService();
//		for (User player : players) {
//			OnePartyPlayer onePartyPlayer = new OnePartyPlayer();
//			onePartyPlayer.setOnePartyUserId(player.getUserId());
//			onePartyPlayer.setFirstName(player.getFirstName());
//			onePartyPlayer.setOnePartyID(partyId);
//			Long userId = player.getUserId();
//			try {
//				PlayerCharacter characterInParty = pcService.findUserCharactersByPartyId(userId, partyId);
//				String characterName = characterInParty.getName();
//				String characterAlignment = characterInParty.getAlignment();
//				Integer characterExperience = characterInParty.getXp();
//				onePartyPlayer.setCharacterName(characterName);
//				onePartyPlayer.setExperience(characterExperience);
//				onePartyPlayer.setLevel(characterInParty.getLevel());
//				onePartyPlayer.setAlignment(characterAlignment);
//				onePartyPlayer.setGold(characterInParty.getGold());
//				onePartyPlayer.setSilver(characterInParty.getSilver());
//				onePartyPlayer.setCopper(characterInParty.getCopper());
//				onePartyPlayer.setOnePartyCharacterId(characterInParty.getCharacterId());
//				Integer xpToLevel = pcService.xpToNextLevel(onePartyPlayer.getOnePartyCharacterId());
//				onePartyPlayer.setXpToLevel(xpToLevel);
//				pcService.levelUpCharacter(onePartyPlayer.getOnePartyCharacterId());
//				pcService.coinConversion(onePartyPlayer.getOnePartyCharacterId());
//			
//				
//			} catch (Exception e) {
//				System.out.println("No character in party");
//			}
//			
//			onePartyPlayers.add(onePartyPlayer);
//		}
//		return onePartyPlayers;
//	}

	
	public void coinConversion(Long characterId) {
		PlayerCharacter pc = pcRepo.findById(characterId).orElseThrow();
		Integer pcGold = pc.getGold();
		Integer pcSilver = pc.getSilver();
		Integer pcCopper = pc.getCopper();
		if (pcCopper >= 10) {
			Integer newCop = (pcCopper % 10);
			Integer newSilver = (pcCopper / 10);
			pc.setCopper(newCop);
			pcSilver = (pcSilver + newSilver);
			pc.setSilver(pcSilver);
		}
		Integer secondGrabSilver = pc.getSilver();
		if (secondGrabSilver >= 10) {
			Integer newSilver = (secondGrabSilver % 10);
			Integer newGold = (secondGrabSilver / 10);
			pc.setSilver(newSilver);
			pcGold = (pcGold + newGold);
			pc.setGold(pcGold);
		}
		pcRepo.save(pc);
	}

	// No formula relates xp to level up, there is no relationship or constant value
	// between the two
	// that is to say the experience needed to go from level to level is variable by
	// design
	//this design choice leads to the terribly ugly code below
	public void levelUpCharacter(Long characterId) {
		PlayerCharacter pc = pcRepo.findById(characterId).orElseThrow();
		Integer pcExperience = pc.getXp();
		if (pcExperience < 300) {
			pc.setLevel(1);
			pcRepo.save(pc);
		}
		if (pcExperience >= 300 && pcExperience < 900) {
			pc.setLevel(2);
			pcRepo.save(pc);
		}
		if (pcExperience >= 900 && pcExperience < 2700) {
			pc.setLevel(3);
			pcRepo.save(pc);
		}
		if (pcExperience >= 2700 && pcExperience < 6500) {
			pc.setLevel(4);
			pcRepo.save(pc);
		}
		if (pcExperience >= 6500 && pcExperience < 14000) {
			pc.setLevel(5);
			pcRepo.save(pc);
		}
		if (pcExperience >= 14000 && pcExperience < 23000) {
			pc.setLevel(6);
			pcRepo.save(pc);
		}
		if (pcExperience >= 23000 && pcExperience < 34000) {
			pc.setLevel(7);
			pcRepo.save(pc);
		}
		if (pcExperience >= 34000 && pcExperience < 48000) {
			pc.setLevel(8);
			pcRepo.save(pc);
		}
		if (pcExperience >= 48000 && pcExperience < 64000) {
			pc.setLevel(9);
			pcRepo.save(pc);
		}
		if (pcExperience >= 64000 && pcExperience < 85000) {
			pc.setLevel(10);
			pcRepo.save(pc);
		}
		if (pcExperience >= 85000 && pcExperience < 100000) {
			pc.setLevel(11);
			pcRepo.save(pc);
		}
		if (pcExperience >= 100000 && pcExperience < 120000) {
			pc.setLevel(12);
			pcRepo.save(pc);
		}
		if (pcExperience >= 120000 && pcExperience < 140000) {
			pc.setLevel(13);
			pcRepo.save(pc);
		}
		if (pcExperience >= 140000 && pcExperience < 165000) {
			pc.setLevel(14);
			pcRepo.save(pc);
		}
		if (pcExperience >= 165000 && pcExperience < 195000) {
			pc.setLevel(15);
			pcRepo.save(pc);
		}
		if (pcExperience >= 195000 && pcExperience < 225000) {
			pc.setLevel(16);
			pcRepo.save(pc);
		}
		if (pcExperience >= 225000 && pcExperience < 265000) {
			pc.setLevel(17);
			pcRepo.save(pc);
		}
		if (pcExperience >= 265000 && pcExperience < 305000) {
			pc.setLevel(18);
			pcRepo.save(pc);
		}
		if (pcExperience >= 305000 && pcExperience < 355000) {
			pc.setLevel(19);
			pcRepo.save(pc);
		}
		if (pcExperience >= 355000) {
			pc.setLevel(20);
			pcRepo.save(pc);
		}
	}

	public Integer xpToNextLevel(Long characterId) {
		PlayerCharacter pc = pcRepo.findById(characterId).orElseThrow();
		Integer pcXp = pc.getXp();
		@SuppressWarnings("serial")
		List<Integer> levelThresholds = new ArrayList<>() {
			{
				add(300);
				add(900);
				add(2700);
				add(6500);
				add(14000);
				add(23000);
				add(48000);
				add(64000);
				add(85000);
				add(100000);
				add(120000);
				add(140000);
				add(165000);
				add(195000);
				add(225000);
				add(265000);
				add(305000);
				add(355000);
			}
		};
		Integer nextLevel = levelThresholds.stream().filter(x -> x.intValue() > pcXp).findFirst().orElseThrow();
		Integer xpToLevelUp = (nextLevel - pcXp);
		System.out.println(xpToLevelUp + "XP TO LEVEL UP");
		return xpToLevelUp;
	}

}
