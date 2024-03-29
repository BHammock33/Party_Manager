package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.domain.User;
import com.partymanager.finalproject.repository.PartyRepository;

@Service
public class DemoPartyService {

	@Autowired
	private PartyRepository partyRepo;
	@Autowired
	private PartyService partyServe;

	public List<Party> getDemoParties() {
		List<Party> allParties = partyServe.findAll();
		List<Party> demoParties = new ArrayList<>();
		for (Party party : allParties) {
			partyServe.forceDmToFront(party);
		}
		for (Party party : allParties) {
			List<User> users = party.getUsers();
			for (User user : users) {
				if (user.getFirstName().equalsIgnoreCase("DemoDM")) {
					List<Party> userParties = user.getParties();
					for (Party filteredParty : userParties) {
						demoParties.add(filteredParty);
					}
				}
			}
		}
		List<Party> distinctDemoParties = demoParties.stream().distinct().collect(Collectors.toList());
		return distinctDemoParties;
	}

	public List<Party> getRealParties() {
		List<Party> allParties = partyRepo.findAll();
		List<Party> realParties = new ArrayList<>();
		for (Party party : allParties) {
			partyServe.forceDmToFront(party);
		}
		for (Party party : allParties) {
			List<User> users = party.getUsers();
			User dm = users.get(0);
			if (!dm.getFirstName().equalsIgnoreCase("DemoDM")) {
				realParties.addAll(dm.getParties());
			}
		}
		List<Party> distinctRealParties = realParties.stream().distinct().collect(Collectors.toList());
		return distinctRealParties;
	}

}
