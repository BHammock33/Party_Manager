package com.partymanager.finalproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public User createUser(@RequestBody String name) {
		return userService.createUser(name);
	}
	@GetMapping("/party/{partyId}")
	public String getPartyPlayers(ModelMap model, @PathVariable Long partyId) {
		
	}
	
}
