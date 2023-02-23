package com.partymanager.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.repository.PartyRepository;
import com.partymanager.finalproject.repository.PlayerCharacterRepository;
import com.partymanager.finalproject.repository.UserRepository;

@Service
public class PartyService {
	
	@Autowired
	private PartyRepository partyRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PlayerCharacterRepository pCRepo;
	

}
