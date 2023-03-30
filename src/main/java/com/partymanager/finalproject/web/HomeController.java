package com.partymanager.finalproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.PartyDto;
import com.partymanager.finalproject.service.OnePartyPlayerService;
import com.partymanager.finalproject.service.PartyService;
import com.partymanager.finalproject.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private PartyService partyService;
	@Autowired
	private OnePartyPlayerService oPPservice;

	@GetMapping("/home")
	public String getHome(@AuthenticationPrincipal User user, ModelMap model) {
		System.out.println("line 20, home: " + user);

		// insert PartyDto for conversion to a party
		PartyDto partyDto = new PartyDto();
		model.put("partyDto", partyDto);

		// Find the currently logged in user and put
		// them on the model if they match the expected user
		Long userId = user.getUserId();
		User userById = userService.findById(userId);
		if (user.getFirstName().equals(userById.getFirstName())) {
			model.put("user", user);
		}
		// get a collection of all parties and put them on the page
		// so they can be displayed and clicked into
		List<Party> parties = partyService.findAll();
		model.put("partiesList", parties);

		return "home";
	}

	@PostMapping("/home/delete/{userId}")
	public String deleteUser(@PathVariable Long userId) {

		// remove player from all parties
		List<Party> parties = partyService.findAll();
		for (Party party : parties) {
			Long partyId = party.getPartyId();
			partyService.removeFromParty(userId, partyId);
			partyService.save(party);
		}
		// delete user from DB
		userService.deleteById(userId);
		return "redirect:/register";
	}

	@PostMapping("/create-party")
	public String createParty(@AuthenticationPrincipal User user, ModelMap model, @ModelAttribute PartyDto partyDto) {
		Long userId = user.getUserId();
		User userById = userService.findById(userId);
		// convert DTO to Party
		partyService.createParty(partyDto, userById);
		return "redirect:/home"; // change to parties later
	}

	@PostMapping("/delete-party/{partyId}")
	public String deleteParty(@PathVariable Long partyId) {
		oPPservice.prepForDeletion(partyId);
		return "redirect:/home";
	}

	@GetMapping("/back-login")
	public String goBackToLogin() {
		return "redirect:/login";
	}

	@GetMapping("/back")
	public String goBack() {
		return "redirect:/home";
	}

}
