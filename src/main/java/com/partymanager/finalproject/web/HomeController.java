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
		
		//insert PartyDto for conversion to a party
		PartyDto partyDto = new PartyDto();
		model.put("partyDto", partyDto);
		
		//Find the currently logged in user and put 
		//them on the model if they match the expected user
		Long userId = user.getUserId();
		User userById = userService.findById(userId);
		if(user.getFirstName().equals(userById.getFirstName())) {
			model.put("user", user);
		}
		//get a collection of all parties and put them on the page
		//so they can be dispalyed and clicked into
		List<Party> parties = partyService.findAll();
		model.put("partiesList", parties);				
		return "home";
	}
	@PostMapping("/home/delete/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		
		//delete user from DB
		userService.deleteById(userId);
		return "redirect:/register";
	}
	@PostMapping("/create-party")
	public String createParty(ModelMap model, @ModelAttribute PartyDto partyDto) {
		
		//convert DTO to Party
		partyService.createParty(partyDto);
		return "redirect:/home"; //change to parties later
	}
	
}
