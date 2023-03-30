package com.partymanager.finalproject.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.CoinModifier;
import com.partymanager.finalproject.dto.OnePartyPlayer;
import com.partymanager.finalproject.dto.XpModifier;
import com.partymanager.finalproject.service.OnePartyPlayerService;
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
	@Autowired
	private OnePartyPlayerService oPPservice;

	@GetMapping("/join-party/{partyId}")
	public String getPartyPage(ModelMap model, @PathVariable Long partyId) {

		Party party = partyService.findByPartyId(partyId).orElseThrow();
		String partyName = party.getPartyName();

		// create One party Players for Thymeleaf
		List<OnePartyPlayer> onePartyPlayers = oPPservice.createOnePartyPlayers(partyId);

		// The Dm will create the party and will always be the first in the array of
		// players
		OnePartyPlayer dm = onePartyPlayers.get(0);
		System.out.println(dm + "DM STATS");
		model.put("dm", dm);

		// Get everyone but the Dm
		List<OnePartyPlayer> justPlayers = onePartyPlayers.stream().skip(1).collect(Collectors.toList());
		System.out.println("OnePartyPlayers" + justPlayers);

		// get the logged in user and see if they're already in the party
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findById(currentUser.getUserId());
		Boolean characterInParty = pcService.checkIfInParty(partyId, user.getUserId());
		// determine if player is in party
		Boolean inParty = partyService.isInParty(partyName, user);

		// put data objects for experience and currency transactions
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

	@PostMapping("/remove-from-party/{partyId}/{firstName}")
	public String removeFromParty(ModelMap model, @PathVariable String firstName, @PathVariable Long partyId) {
		User user = userService.findByFirstName(firstName);
		Long userId = user.getUserId();

		partyService.removeFromParty(userId, partyId);
		return "redirect:/join-party/{partyId}";
	}

}
