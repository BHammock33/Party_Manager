package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.dto.PartyDto;
import com.partymanager.finalproject.repository.PartyRepository;
import com.partymanager.finalproject.repository.UserRepository;

@Service
public class PartyService {

	@Autowired
	private PartyRepository partyRepo;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;

	public List<Party> findAll() {
		return partyRepo.findAll();
	}

	public Optional<Party> findByPartyId(Long partyId) {
		return partyRepo.findById(partyId);
	}

	public Party save(Party party) {
		return partyRepo.save(party);

	}

	public Party createParty(PartyDto partyDto, User user) {
		// create a new party, append an empty list of users and characters
		Party party = new Party();
		List<User> emptyUsers = new ArrayList<User>(7);
		List<PlayerCharacter> emptyCharacters = new ArrayList<PlayerCharacter>(7);
		List<Party> userParties = user.getParties();

		// map DTO properties to Party
		party.setPartyName(partyDto.getPartyName());
		party.setPartyId(party.getPartyId());
		party.setUsers(emptyUsers);
		party.setCharacters(emptyCharacters);

		// add current user to party
		userParties.add(party);
		party.getUsers().add(user);

		// save new party in partyRepo
		// userRepo.save(user);
		partyRepo.save(party);
		System.out.println("Party is saved:" + party);// functions party has ID, name, empty users, and empty characters
		return party;
	}

	public Party findByPartyName(String partyName) {
		Party party = partyRepo.findByPartyName(partyName);
		return party;
	}

	// find a user, add them to the party, save entities
	public List<Party> joinPartyById(Long partyId, Long userId) {
		User foundUser = userService.findById(userId);
		List<Party> userParties = foundUser.getParties();
		Party partyToBeJoined = partyRepo.findById(partyId).orElseThrow();

		userParties.add(partyToBeJoined);
		userRepo.save(foundUser);
		partyRepo.save(partyToBeJoined);
		partyRepo.saveAll(userParties);

		return userParties;
	}

	// determine if the user is already in the party for conditional displaying
	// of join party button
	public Boolean isInParty(String partyName, User user) {

		List<Party> parties = user.getParties();
		List<String> partyNames = new ArrayList<>();
		for (Party party : parties) {
			String existingName = party.getPartyName();
			partyNames.add(existingName);
		}
		Boolean contains = partyNames.contains(partyName);
		return contains;

	}

	// allows a DM to remove a player from the party
	// the user can rejoin the party with the character they created for the party
	// if need be
	public void removeFromParty(Long userId, Long partyId) {
		Party foundParty = partyRepo.findById(partyId).orElseThrow();
		User foundUser = userService.findById(userId);
		foundUser.getParties().remove(foundParty);
		userRepo.save(foundUser);
		partyRepo.save(foundParty);
	}

	// The Dm Creates the party so will always be the first in the list of party
	// users
	public User getPartyDm(Long partyId) {
		Party party = partyRepo.findById(partyId).orElseThrow();
		List<User> partyPlayers = party.getUsers();
		User dm = partyPlayers.get(0);
		return dm;

	}
	public void deleteParty(Long partyId) {
		partyRepo.deleteById(partyId);
	}

}
