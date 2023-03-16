package com.partymanager.finalproject.web;

import java.beans.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.UserDto;
import com.partymanager.finalproject.security.Authorities;
import com.partymanager.finalproject.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String getHome(@AuthenticationPrincipal User user, ModelMap model) {
		System.out.println("line 20, home: " + user);
		UserDto userDto = new UserDto();
		//User savedUser = userService.save(userDto);
		Long userId = user.getUserId();
		User userById = userService.findById(userId);
		System.out.println("Line 29: " + userById);
		if(user.getFirstName().equals(userById.getFirstName())) {
			model.put("user", user);
		}
			
		
	//	model.put("user", savedUser);
		
		return "home";
	}
}
