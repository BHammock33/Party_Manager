package com.partymanager.finalproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
//	@GetMapping("/party/{partyId}")
//	public String getPartyPlayers(ModelMap model, @PathVariable Long partyId) {
//		return null;
//		
//	}

	@GetMapping("/user/{userId}")
	public String getUserPage(ModelMap model, @PathVariable Long userId, @AuthenticationPrincipal User user) {
		
		
		System.out.println(user);
		model.put("user", user);
		return "user";
	}
}
