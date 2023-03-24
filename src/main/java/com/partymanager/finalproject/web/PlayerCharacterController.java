package com.partymanager.finalproject.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.Coin;
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
	
	@GetMapping("/create-character")
	public String getCreateCharacter(ModelMap model){
		PlayerCharacterDto playerCharacterDto = new PlayerCharacterDto();
		model.put("pc", playerCharacterDto);
		return "character";
	}
	@PostMapping("/create-character")
	public String createCharacter(@ModelAttribute("pc") PlayerCharacterDto playerCharacterDto) {
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findById(currentUser.getUserId());
		PlayerCharacter playerCharacter = new PlayerCharacter();
		List<Coin> coins = new ArrayList<>();
		List<PlayerCharacter> uPC = user.getCharacters();
		String partyName = playerCharacterDto.getPartyName();
		Party party = partyService.findByPartyName(partyName);
		playerCharacter.setCharacterId(playerCharacter.getCharacterId());
		playerCharacter.setName(playerCharacterDto.getName());
		playerCharacter.setXp(playerCharacterDto.getXp());
		playerCharacter.setAlignment(playerCharacterDto.getAlignment());
		playerCharacter.setParty(party);
		playerCharacter.setUser(user);
		playerCharacter.setCoins(coins);
		uPC.add(playerCharacter);
		pcService.save(playerCharacter);
		userService.save(user);
		partyService.save(party);
		
		System.out.println(playerCharacter);
		
		
		return "redirect:/home";
	}

}
