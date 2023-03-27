package com.partymanager.finalproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.partymanager.finalproject.dto.UserDto;
import com.partymanager.finalproject.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

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
