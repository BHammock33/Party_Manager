package com.partymanager.finalproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.repository.PartyRepository;
import com.partymanager.finalproject.repository.PlayerCharacterRepository;
import com.partymanager.finalproject.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PlayerCharacterRepository pCRepo;
	@Autowired
	private PartyRepository partyRepo;
	
	public List<User> findByUsername(String username){
		return userRepo.findByUsername(username);
		
	}
	public User findExactlyOneUserByUsername(String username) {
		List<User> users = userRepo.findExactlyOneUserByUsername(username);
		if(users.size() > 0)
			return users.get(0);
		else
			return new User();
	}
	
	public User findById(Long userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		return userOpt.orElse(new User());
	}
	
//	public User saveUser(User user) {
//		if(user.getUserId() == null) {
//			PlayerCharacter pc = new PlayerCharacter();
//			
//		}
//	}
	public void delete(Long userId) {
		userRepo.deleteById(userId);
	}
	
}
