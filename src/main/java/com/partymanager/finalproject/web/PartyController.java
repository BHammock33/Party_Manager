package com.partymanager.finalproject.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.CoinModifier;
import com.partymanager.finalproject.dto.OnePartyPlayer;
import com.partymanager.finalproject.dto.PlayerCharacterDto;
import com.partymanager.finalproject.dto.XpModifier;
import com.partymanager.finalproject.service.PartyService;
import com.partymanager.finalproject.service.PlayerCharacterService;
import com.partymanager.finalproject.service.UserService;

@Controller
public class PartyController {

	@Autowired
	private PartyService partyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PlayerCharacterService pcService;

	@GetMapping("/join-party/{partyId}")
	public String getPartyPage(ModelMap model, @PathVariable Long partyId) {
		
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		String partyName = party.getPartyName();
		List<User> players = party.getUsers();
		List<OnePartyPlayer> onePartyPlayers = new ArrayList<>();
		//converting the users in the party into "Single Party Players" to allow
		//for easier displaying of character attributes on the page
		//still working on moving this to a service but it doesn't want to work
		//anywhere else
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
		
		//The Dm will create the party and will always be the first in the array of players
		OnePartyPlayer dm = onePartyPlayers.get(0);
		System.out.println(dm + "DM STATS");
		model.put("dm", dm);

		//Get everyone but the Dm
		List<OnePartyPlayer> justPlayers = onePartyPlayers.stream().skip(1).collect(Collectors.toList());
		System.out.println("OnePartyPlayers" + justPlayers);

		
		
		//get the logged in user and see if they're already in the party
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findById(currentUser.getUserId());
		Boolean characterInParty = pcService.checkIfInParty(partyId, user.getUserId());
		// determine if player is in party
		Boolean inParty = partyService.isInParty(partyName, user);
	
		//put data objects for experience and currency transactions
		XpModifier xpModifier = new XpModifier();
		CoinModifier coinModifier = new CoinModifier();
		
		model.put("inParty", inParty);
		model.put("players", justPlayers);
		

		model.put("party", party);
		model.put("user", user);
		model.put("xpModifier", xpModifier);
		model.put("goldModifier", coinModifier);
		model.put("silverModifier", coinModifier);
		model.put("copperModifier", coinModifier);

		model.put("characterInParty", characterInParty);
		

		return "party";
	}

	@PostMapping("/join-party/{partyId}")
	public String joinParty(ModelMap model, @PathVariable Long partyId) {
		Party foundParty = partyService.findByPartyId(partyId).orElseThrow();
		model.put("partyToBeJoined", foundParty);
		model.put("party", foundParty);
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long currentUserId = currentUser.getUserId();
		User user = userService.findById(currentUserId);
		Long userId = user.getUserId();
		partyService.joinPartyById(partyId, userId);
		return "redirect:/join-party/{partyId}";
	}

	@GetMapping("/back")
	public String goBack() {
		return "redirect:/home";
	}

	@PostMapping("/remove-from-party/{partyId}/{firstName}")
	public String removeFromParty(ModelMap model, @PathVariable String firstName, @PathVariable Long partyId) {
		User user = userService.findByFirstName(firstName);
		Long userId = user.getUserId();

		partyService.removeFromParty(userId, partyId);
		return "redirect:/join-party/{partyId}";
	}

	@GetMapping("/create-character/{partyName}")
	public String createCharacterfromParty(ModelMap model, @PathVariable String partyName) {
		PlayerCharacterDto playerCharacterDto = new PlayerCharacterDto();
		playerCharacterDto.setPartyName(partyName);
		model.put("pc", playerCharacterDto);
		return "character";

	}

//	@PostMapping("/join-party/{partyId}/{userId}/delete")
//	@ResponseBody
//	public User removeUser(@RequestBody User removedUser, @PathVariable Long userId, @PathVariable Long PartyId) {
//		User user = userService.findById(userId);
//		Party party = partyService.findByPartyId(PartyId).orElseThrow();
//		partyService.removeFromParty(userId, PartyId);
//		userService.save(user);
//		partyService.save(party);
//		return user;
//	}
	@PostMapping("/add-xp/{characterName}/{partyId}")
	public String addCharacterXP(@PathVariable String characterName, @PathVariable Long partyId,
			@ModelAttribute("xpModifier") XpModifier amount) {
		PlayerCharacter playerCharacter = pcService.findByName(characterName);
		Integer xpToAdd = amount.getAmount();
		Integer previousXp = playerCharacter.getXp();
		Integer newXp = previousXp + xpToAdd;
		playerCharacter.setXp(newXp);
		pcService.save(playerCharacter);
		User user = playerCharacter.getUser();
		userService.save(user);
		System.out.println("In adding Xp");
		System.out.println("new Xp = " + playerCharacter.getXp());
		pcService.levelUpCharacter(playerCharacter.getCharacterId());
		Integer xpToLevel = pcService.xpToNextLevel(playerCharacter.getCharacterId());
		playerCharacter.setXpToLevel(xpToLevel);

		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/add-gold/{characterName}/{partyId}")
	public String addGold(@PathVariable String characterName, @PathVariable Long partyId,
			@ModelAttribute("goldModifier") CoinModifier amount) {
		PlayerCharacter playerCharacter = pcService.findByName(characterName);
		Integer goldAdd = amount.getAmount();
		Integer newGold = goldAdd + (playerCharacter.getGold());
		playerCharacter.setGold(newGold);
		pcService.coinConversion(playerCharacter.getCharacterId());
		pcService.save(playerCharacter);
		User user = playerCharacter.getUser();
		userService.save(user);

		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/add-silver/{characterName}/{partyId}")
	public String addSilver(@PathVariable String characterName, @PathVariable Long partyId,
			@ModelAttribute("silverModifier") CoinModifier amount) {
		PlayerCharacter playerCharacter = pcService.findByName(characterName);
		Integer silverAdd = amount.getAmount();
		Integer newSilver = silverAdd + (playerCharacter.getSilver());
		playerCharacter.setSilver(newSilver);
		pcService.coinConversion(playerCharacter.getCharacterId());
		pcService.save(playerCharacter);
		User user = playerCharacter.getUser();
		userService.save(user);

		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/add-copper/{characterName}/{partyId}")
	public String addCopper(@PathVariable String characterName, @PathVariable Long partyId,
			@ModelAttribute("copperModifier") CoinModifier amount) {
		PlayerCharacter playerCharacter = pcService.findByName(characterName);
		Integer copperAdd = amount.getAmount();
		Integer newCopper = copperAdd + (playerCharacter.getCopper());
		playerCharacter.setCopper(newCopper);
		pcService.coinConversion(playerCharacter.getCharacterId());
		pcService.save(playerCharacter);
		User user = playerCharacter.getUser();
		userService.save(user);

		return "redirect:/join-party/{partyId}";
	}

}
