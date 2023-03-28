package com.partymanager.finalproject.web;

import java.util.List;

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
import com.partymanager.finalproject.dto.PlayerCharacterDto;
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
	PartyService partyService;

	@PostMapping("/create-character/{partyId}")
	public String createCharacter(@ModelAttribute("pc") PlayerCharacterDto playerCharacterDto,
			@PathVariable Long partyId) {
		// Get the User
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findById(currentUser.getUserId());

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
	@GetMapping("/create-character/{partyName}")
	public String createCharacterfromParty(ModelMap model, @PathVariable String partyName) {
		PlayerCharacterDto playerCharacterDto = new PlayerCharacterDto();
		playerCharacterDto.setPartyName(partyName);
		Party party = partyService.findByPartyName(partyName);
		model.put("party", party);
		model.put("pc", playerCharacterDto);
		return "character";

	}

}
