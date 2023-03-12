package com.partymanager.finalproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
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
	
	public User findByUsername(String username){	
		return userRepo.findByUsername(username);
	}
	
	public User save(User user) {
		return userRepo.save(user);
	}
	public User findById(Long userId) {
		return userRepo.findById(userId).orElse(new User());
	}
	public List<User> findAll(){
		return userRepo.findAll();
	}
	public boolean usernameExists(String username) {
		return userRepo.existsByUsername(username);
	}
	
	public void delete(Long userId) {
		userRepo.deleteById(userId);
	}
	
	public List<User> findUsersByPartyId(long partyId){
		Optional<Party> party = partyRepo.findById(partyId);
		List<User> users = party.get().getUsers();
		return users;
	}
	public List<User> saveAll(List<User> savedUsers){
		return userRepo.saveAll(savedUsers);
	}
	
	
}
