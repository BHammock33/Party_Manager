package com.partymanager.finalproject.web;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.UserDto;
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
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		List<String> roles = userService.createUserRoles();
		model.addAttribute("listRole", roles);
		
		
		return "register";
	}


	


	@PostMapping("/register")
	public String createUser(UserDto userDto) {
		
		userService.save(userDto);
	
		
		
		return "redirect:/login";
	}
	
	

}
