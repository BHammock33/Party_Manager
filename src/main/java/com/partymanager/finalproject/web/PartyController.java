package com.partymanager.finalproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.OnePartyPlayer;
import com.partymanager.finalproject.dto.PartyDto;
import com.partymanager.finalproject.dto.PlayerCharacterDto;
import com.partymanager.finalproject.dto.UserDto;
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
		System.out.println("line 100 getmapping");
		Party party = partyService.findByPartyId(partyId).orElseThrow();
		List<User> players = party.getUsers();
//		for(User user : players) {
//			OnePartyPlayer onePartyPlayer = new OnePartyPlayer();
//			Long userId = user.getUserId();
//			onePartyPlayer.setFirstName(user.getFirstName());
//			onePartyPlayer.setOnePartyUserId(user.getUserId());
//			onePartyPlayer.setOnePartyID(partyId);
//			PlayerCharacter characterInParty = pcService.findUserCharactersByPartyId(userId, partyId);
//			onePartyPlayer.setCharacterName(characterInParty.getName());
//			onePartyPlayer.setExperience(characterInParty.getXp());
//			onePartyPlayer.setAlighment(characterInParty.getAlignment());
//			System.out.println(onePartyPlayer);
//		}
		String partyName = party.getPartyName();
		
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findById(currentUser.getUserId());
		Long userId = user.getUserId();
		PlayerCharacter characterInParty = pcService.findUserCharactersByPartyId(userId, partyId);
		Boolean inParty = partyService.isInParty(partyName, user);
		System.out.println(inParty + "inParty value");
		UserDto userDto = new UserDto();
		PlayerCharacterDto pcDto = new PlayerCharacterDto();
		pcDto.setName("test");
		model.put("inParty", inParty);
		model.put("players", players);
		model.put("players2", players);
		model.put("party", party);
		model.put("user", user);
		model.put("playerCharacterDto", pcDto);
		
		PartyDto partyDto = new PartyDto();
		model.put("partyDto", partyDto);
		return "party";
	}
	@PostMapping("/join-party/{partyId}")
	public String joinParty(ModelMap model, @PathVariable Long partyId) {
		Party foundParty = partyService.findByPartyId(partyId).orElseThrow();
		model.put("partyToBeJoined", foundParty);
		model.put("party", foundParty);
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
	public String createCharacterfromParty(ModelMap model, @PathVariable String partyName){
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

}
