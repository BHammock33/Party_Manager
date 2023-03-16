package com.partymanager.finalproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.PartyDto;
import com.partymanager.finalproject.service.PartyService;
import com.partymanager.finalproject.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PartyService partyService;

	@GetMapping("/home")
	public String getHome(@AuthenticationPrincipal User user, ModelMap model) {
		//System.out.println("line 20, home: " + user);
		
		//User savedUser = userService.save(userDto);
		Long userId = user.getUserId();
		User userById = userService.findById(userId);
	//	System.out.println("Line 29: " + userById);
		if(user.getFirstName().equals(userById.getFirstName())) {
			model.put("user", user);
		}
		List<Party> parties = partyService.createTestParties();
		model.put("partiesList", parties);
		
			
		
	//	model.put("user", savedUser);
		
		return "home";
	}
	@PostMapping("/home/delete/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		userService.deleteById(userId);
		return "redirect:/register";
	}
	@PostMapping("/home/join-party")
	public String joinParty(Model model, @ModelAttribute("partyDto") PartyDto partyDto) {
		model.addAttribute("partyDto", partyDto);
		Party party = new Party();
		party.setPartyName(partyDto.getPartyName());
		partyService.save(party);
		
		return "redirect:/home"; ///change to party screen later
	}
}
