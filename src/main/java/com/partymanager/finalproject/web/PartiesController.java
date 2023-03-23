package com.partymanager.finalproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.service.UserService;

@Controller
public class PartiesController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/home/parties/{userId}")
	public String getParties(ModelMap model, @PathVariable Long userId) {
		User currentUser = userService.findById(userId);
		model.put("user", currentUser);
		List<Party> parties = currentUser.getParties();
		model.put("parties", parties);
		return "parties";
	}

}
