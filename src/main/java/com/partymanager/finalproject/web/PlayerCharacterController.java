package com.partymanager.finalproject.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.partymanager.finalproject.dto.PlayerCharacterDto;
import com.partymanager.finalproject.dto.XpModifier;
import com.partymanager.finalproject.service.OnePartyPlayerService;
import com.partymanager.finalproject.service.PartyService;
import com.partymanager.finalproject.service.PlayerCharacterService;
import com.partymanager.finalproject.service.UserService;

@Controller
public class PlayerCharacterController {
	@Autowired
	private PlayerCharacterService pcService;
	@Autowired
	private UserService userService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private OnePartyPlayerService oPPservice;

	@PostMapping("/create-character/{partyId}")
	public String createCharacter(@ModelAttribute("pc") PlayerCharacterDto playerCharacterDto,
			@PathVariable Long partyId) {
		// Get the User
		User user = userService.findLoggedIn();

		// PC from PCDTO
		PlayerCharacter playerCharacter = pcService.convertDtoToPc(playerCharacterDto, user);

		// Add PC to users characters
		List<PlayerCharacter> userPcs = user.getCharacters();
		userPcs.add(playerCharacter);
		userService.save(user);
		pcService.save(playerCharacter);

		// convert coins upon creation if need be
		Long pcId = playerCharacter.getCharacterId();
		pcService.coinConversion(pcId);

		return "redirect:/join-party/{partyId}";
	}

	@SuppressWarnings("serial")
	@GetMapping("/create-character/{partyName}")
	public String createCharacterfromParty(ModelMap model, @PathVariable String partyName) {
		PlayerCharacterDto playerCharacterDto = new PlayerCharacterDto();
		playerCharacterDto.setPartyName(partyName);
		Party party = partyService.findByPartyName(partyName);
		model.put("party", party);
		model.put("pc", playerCharacterDto);
		List<String> alignments = new ArrayList<String>() {
			{
				add("Lawful Good");
				add("Netural Good");
				add("Choatic Good");
				add("Lawful Neutral");
				add("True Neutral");
				add("Chaotic Neutral");
				add("Lawful Evil");
				add("Neutral Evil");
				add("Choatic Evil");
			}
		};
		model.put("alignments", alignments);
		return "character";

	}

	@PostMapping("/add-xp/{characterName}/{partyId}")
	public String addCharacterXP(@PathVariable String characterName, @PathVariable Long partyId,
			@ModelAttribute("xpModifier") XpModifier amount) {
		PlayerCharacter playerCharacter = pcService.findByName(characterName);
		Integer xpToAdd = amount.getAmount();
		if (playerCharacter.getXp() == null) {
			playerCharacter.setXp(0);
		}
		Integer previousXp = playerCharacter.getXp();
		Integer newXp = previousXp + xpToAdd;
		playerCharacter.setXp(newXp);
		pcService.save(playerCharacter);
		User user = playerCharacter.getUser();
		userService.save(user);

		pcService.levelUpCharacter(playerCharacter.getCharacterId());
		Integer xpToLevel = pcService.xpToNextLevel(playerCharacter.getCharacterId());
		playerCharacter.setXpToLevel(xpToLevel);

		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/add-gold/{characterName}/{partyId}")
	public String addGold(@PathVariable String characterName, @PathVariable Long partyId,
			@ModelAttribute("goldModifier") CoinModifier amount) {
		PlayerCharacter playerCharacter = pcService.findByName(characterName);
		// add/spend gold and do conversion
		oPPservice.addGold(characterName, amount);
		// revert to previous value if spend would put gold in negative
		if (playerCharacter.getGold() < 0) {
			playerCharacter.setGold((playerCharacter.getGold() + Math.abs(amount.getAmount())));
			pcService.save(playerCharacter);
			return "bigSpender";
		}

		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/add-silver/{characterName}/{partyId}")
	public String addSilver(@PathVariable String characterName, @PathVariable Long partyId,
			@ModelAttribute("silverModifier") CoinModifier amount) {
		PlayerCharacter playerCharacter = pcService.findByName(characterName);
		// fallback values in case pulling silver causes gold to go negative
		Integer previousGold = playerCharacter.getGold();
		Integer previousSilver = playerCharacter.getSilver();
		// add/spend the silver and do conversion
		oPPservice.addSilver(characterName, amount);
		// deal with negative gold
		if (playerCharacter.getGold() < 0) {
			playerCharacter.setGold(previousGold);
			playerCharacter.setSilver(previousSilver);
			pcService.save(playerCharacter);
			return "bigSpender";
		}
		return "redirect:/join-party/{partyId}";
	}

	@PostMapping("/add-copper/{characterName}/{partyId}")
	public String addCopper(@PathVariable String characterName, @PathVariable Long partyId,
			@ModelAttribute("copperModifier") CoinModifier amount) {
		PlayerCharacter playerCharacter = pcService.findByName(characterName);
		// fallback values in case pulling copper causes silver to put gold in negative
		Integer previousGold = playerCharacter.getGold();
		Integer previousSilver = playerCharacter.getSilver();
		Integer previousCopper = playerCharacter.getCopper();
		// add/spend copper and do conversion
		oPPservice.addCopper(characterName, amount);
		// deal with negative gold
		if (playerCharacter.getGold() < 0) {
			playerCharacter.setGold(previousGold);
			playerCharacter.setSilver(previousSilver);
			playerCharacter.setCopper(previousCopper);
			pcService.save(playerCharacter);
			return "bigSpender";
		}

		return "redirect:/join-party/{partyId}";
	}

	@GetMapping("/see-characters")
	public String editCharacter(ModelMap model) {
		User user = userService.findLoggedIn();

		List<PlayerCharacter> pcs = user.getCharacters();
		for (int i = 0; i < pcs.size(); i++) {
			PlayerCharacter characterOne = pcs.get(i);
			model.put("character", characterOne);
		}
		model.put("characters", pcs);
		model.put("user", user);
		return "characters";
	}

	@PostMapping("/delete-character/{characterId}")
	public String deleteCharacter(@PathVariable Long characterId) {
		pcService.deleteById(characterId);
		return "redirect:/see-characters";
	}
}
