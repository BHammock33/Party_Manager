package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.UserDto;
import com.partymanager.finalproject.repository.PartyRepository;
import com.partymanager.finalproject.repository.PlayerCharacterRepository;
import com.partymanager.finalproject.repository.UserRepository;
import com.partymanager.finalproject.security.Authorities;

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
	public User save(UserDto userdto) {
		
		String encodedPassword = new BCryptPasswordEncoder().encode(userdto.getPassword());
		
		Authorities userAuth = new Authorities();
		User newUser = new User();
		
		newUser.setPassword(encodedPassword);
		newUser.setFirstName(userdto.getFirstName());
		newUser.setLastName(userdto.getLastName());
		newUser.setUsername(userdto.getUsername());
		
		if(userdto.getRole().equals("DM")) {
			userAuth.setAuthority("ROLE_DM");
			newUser.getAuthorities().add(userAuth);
			userAuth.setUser(newUser);
		}else {
			userAuth.setAuthority("ROLE_USER");
			newUser.getAuthorities().add(userAuth);
			userAuth.setUser(newUser);
		}
		
		
		return userRepo.save(newUser);
	}
	
	public void deleteById(Long userId) {
		userRepo.deleteById(userId);
	}
	public void addParty(Long userId, Party party) {
			User user = userRepo.findById(userId).get();
			user.getParties().add(party);
			userRepo.save(user);
		
	}
	public List<String> createUserRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("DM");
		roles.add("Player");
		return roles;
	}

	
	
}
