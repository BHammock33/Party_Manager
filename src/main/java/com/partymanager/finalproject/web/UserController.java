package com.partymanager.finalproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public String getUserPage(ModelMap model, @PathVariable Long userId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User authUser = (User)authentication.getPrincipal();
		model.put("user", authUser);
		return "user";
	}
}
