package com.partymanager.finalproject.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.CoinModifier;
import com.partymanager.finalproject.dto.OnePartyPlayer;
import com.partymanager.finalproject.security.Authorities;

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

	// Find all players in a to be deleted party and remove the party from their
	// lists
	public void prepForDeletion(Long partyId) {
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		List<User> players = party.getUsers();
		List<Long> userIds = new ArrayList<>();
		for (User player : players) {
			Long playerId = player.getUserId();
			userIds.add(playerId);
		}
		for (Long userId : userIds) {
			partyService.removeFromParty(userId, partyId);
			User userById = userService.findById(userId);
			userService.save(userById);
		}
		partyService.deleteParty(partyId);
	}

	public void createPartyFund(Long partyId) {
		// get party and players and characters
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		List<User> players = party.getUsers();
		List<PlayerCharacter> pcs = party.getCharacters();
		List<Party> blankParties = new ArrayList<>();
		// create a new user to assign to party fund
		User newUser = new User();
		newUser.setFirstName("Party Fund");
		// generate username and password
		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedUserName = new String(array, Charset.forName("UTF-8"));
		newUser.setUsername(generatedUserName);
		new Random().nextBytes(array);
		String generatedPassword = new String(array, Charset.forName("UTF-8"));
		newUser.setPassword(generatedPassword);
		// create a new character for the new user to act as party fund
		PlayerCharacter partyFundPc = new PlayerCharacter();
		partyFundPc.setName("Party Fund");
		partyFundPc.setCopper(0);
		partyFundPc.setSilver(0);
		partyFundPc.setGold(0);
		partyFundPc.setLevel(0);
		partyFundPc.setAlignment("");
		partyFundPc.setParty(party);
		partyFundPc.setUser(newUser);
		partyFundPc.setXp(0);
		partyFundPc.setXpToLevel(0);
		// add party fund character to party
		pcs.add(partyFundPc);
		// add party fund user to party
		players.add(newUser);
		// add party to users parties
		blankParties.add(party);
		newUser.setParties(blankParties);
		// save
		userService.save(newUser);
		userService.saveAll(players);
		pcService.save(partyFundPc);
		partyService.save(party);

	}

	public Boolean checkForPartyFund(Long partyId) {
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		List<User> players = party.getUsers();
		List<String> names = new ArrayList<>();
		String partyFund = "Party Fund";
		for (User player : players) {
			String name = player.getFirstName();
			names.add(name);
		}
		Boolean partyFunded = false;
		if (names.contains(partyFund)) {
			partyFunded = true;
			return partyFunded;
		}
		return partyFunded;

	}
	public User getDm(Party party) {
		List<User> users = party.getUsers();
		List<Set<Authorities>> auths = new ArrayList<>();
		List<User> dm = new ArrayList<>();
		for(User user : users) {
			Set<Authorities> authority = user.getAuthorities();
			auths.add(authority);
		}
		for(Set<Authorities> authority : auths) {
			for(Authorities auth : authority) {
				String authString = auth.getAuthority();
				if(authString.equalsIgnoreCase("ROLE_DM")) {
					User dmUser = auth.getUser();
					dm.add(dmUser);
				}
			}
		}
		User dmUser = dm.get(0);
		return dmUser;
	}
	
	public User createNonUserPlayer(Long partyId, String firstName, String characterName) {
		Party party = partyService.findByPartyId(partyId).orElseThrow();

		User newUser = new User();
		
		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedUserName = new String(array, Charset.forName("UTF-8"));
		
		new Random().nextBytes(array);
		String generatedPassword = new String(array, Charset.forName("UTF-8"));
		
		//fake register a new user
		newUser.setFirstName(firstName);
		newUser.setLastName("lastName");
		newUser.setPassword(generatedPassword);
		newUser.setUsername(generatedUserName);
		Set<Authorities> auths = new HashSet<>();
		Authorities auth = new Authorities();
		auth.setAuthority("ROLE_USER");
		auths.add(auth);
		newUser.setAuthorities(auths);
		userService.save(newUser);
		
		//create empty list of parties to add this party to users party
		List<Party> parties = new ArrayList<>();
		parties.add(party);
		newUser.setParties(parties);
		//Empty list of player characters to add this users characters
		List<PlayerCharacter> pcs = new ArrayList<>(); 
		
		PlayerCharacter pc = new PlayerCharacter();
		pc.setName(characterName);
		pc.setXp(0);
		pc.setAlignment("");
		pc.setUser(newUser);
		pc.setParty(party);
		pc.setCopper(0);
		pc.setSilver(0);
		pc.setGold(0);
		pc.setLevel(1);
		pc.setXpToLevel(300);
		pcService.save(pc);
		pcs.add(pc);
		newUser.setCharacters(pcs);
		userService.save(newUser);
		
		return newUser;

	}
	

}
