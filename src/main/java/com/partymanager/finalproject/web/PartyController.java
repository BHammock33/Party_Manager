package com.partymanager.finalproject.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.service.PartyService;
import com.partymanager.finalproject.service.UserService;

@Controller
public class PartyController {
	
	@Autowired
	private PartyService partyService;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/parties")
	public String getParties(ModelMap model) {
		List<Party> parties = partyService.findAll();
		model.put("parties", parties);
		return "parties";
	}
	@GetMapping("/party/{partyId}")
	public String getParty(ModelMap model, @PathVariable Long partyId) {
		Optional<Party> party = partyService.findByPartyId(partyId);
		List<User> players = userService.findUsersByPartyId(partyId);
		model.put("party", party);
		model.put("players", players);
		return "party";
	}

}
