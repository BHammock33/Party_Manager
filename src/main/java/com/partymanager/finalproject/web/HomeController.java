package com.partymanager.finalproject.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		System.out.println("line 20, home: " + user);
		PartyDto partyDto = new PartyDto();
		model.put("partyDto", partyDto);
		
		//User savedUser = userService.save(userDto);
		Long userId = user.getUserId();
		User userById = userService.findById(userId);
	//	System.out.println("Line 29: " + userById);
		if(user.getFirstName().equals(userById.getFirstName())) {
			model.put("user", user);
		}
//		List<Party> parties = partyService.createTestParties();
//		model.put("partiesList", parties);
		List<Party> parties = partyService.findAll();
		List<Long> partyIds = new ArrayList<>();
		for (Party party : parties) {
			System.out.println(party.getPartyId());
			partyIds.add(party.getPartyId());
		}
		model.put("partiesList", parties);
		model.put("initialList", partyIds);
		
			
		
	//	model.put("user", savedUser);
		
		return "home";
	}
	@PostMapping("/home/delete/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		userService.deleteById(userId);
		return "redirect:/register";
	}
	@PostMapping("/join-party/{partyId}")
	public String joinParty(ModelMap model, User user, @PathVariable Long partyId ){
//		//use th:object on two seperate divs one for user one for party
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userById = userService.findById(currentUser.getUserId());
//		
//		
		userService.joinParty(userById, partyId);
		
		return "redirect:/home"; ///change to party screen later party/{partyId}
	}
	@PostMapping("/create-party")
	public String createParty(ModelMap model, @ModelAttribute PartyDto partyDto) {
		
		partyService.createParty(partyDto);

		return "redirect:/home"; //change to parties later
	}
//	@RequestMapping("/party")
//	public String redirect(@RequestParam(value = "parties") Long partyId, User user) {
//		userService.joinParty(user, partyId);
//		return "parties";
//	}
	
}
