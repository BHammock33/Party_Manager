package com.partymanager.finalproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String getHome(@AuthenticationPrincipal User user, ModelMap model) {
		Long userId = user.getUserId();
		User userById = userService.findById(userId);
		model.put("user", userById);
		return "home";
	}
}
