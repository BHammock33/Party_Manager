package com.partymanager.finalproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.security.Authorities;
import com.partymanager.finalproject.service.AuthoritiesService;
import com.partymanager.finalproject.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authService;

	@GetMapping("/register")
	public String getRegistrationPage(ModelMap model) {
		model.put("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String createUser(User user) {
		System.out.println(user);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Authorities authorities = new Authorities("ROLE_USER", user);
		user.getAuthorities().add(authorities);
		System.out.println(user.getAuthorities());
		userService.save(user);
		authService.saveAuth(authorities);
		return "redirect:/login";
	}

}
