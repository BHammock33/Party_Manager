package com.partymanager.finalproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.service.UserService;

@Controller
@RequestMapping("/users")
public class RegistrationController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String getRegistrationPage(ModelMap model) {
		model.put("user", new User());
		return "registration";
	}
	
	@PostMapping("/register")
	public String createUser(@ModelAttribute User user, ModelMap model) {
		boolean usernameExists = userService.usernameExists(user.getUsername());
		
		if(usernameExists) {
			model.addAttribute("errorMessageUser", "Username already exists");
			return "registration";
		}
		
		if(user.getPassword().length()< 6) {
			model.addAttribute("errorMessagePassword", "Password must be at least 6 characters long");
			return "registration";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		return "redirect:/login";
	}

}
