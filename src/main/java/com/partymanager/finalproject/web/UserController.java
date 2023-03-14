package com.partymanager.finalproject.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.service.PartyService;
import com.partymanager.finalproject.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private PartyService partyService;
	
	List<Party> parties = new ArrayList<>();
	
	
	@GetMapping("/user/{userId}")
	public String getUserPage(ModelMap model, @AuthenticationPrincipal User user) {
		User foundUser = userService.findById(user.getUserId());
		System.out.println(user);
		model.put("user", foundUser);
		return "user";
	}
	@PostMapping("/user/update")
	public String updateAccount(User user) {
		userService.save(user);
		System.out.println(user);
		
		return "redirect:/user/{UserId}";
	}
	@PostMapping("/user/create-party")
	public String createParty(Party party, @AuthenticationPrincipal User user, ModelMap model) {
		Party newParty = new Party();
		model.put("party", newParty);
		partyService.save(newParty);
		user.setParties(parties);
		parties.add(newParty);
		return "redirect:/user/{UserId}";
	}
	
}
