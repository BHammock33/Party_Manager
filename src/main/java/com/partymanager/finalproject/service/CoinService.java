package com.partymanager.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.repository.CoinRepository;
import com.partymanager.finalproject.repository.PartyRepository;
import com.partymanager.finalproject.repository.PlayerCharacterRepository;

@Service
public class CoinService {
	
	@Autowired
	private PlayerCharacterRepository pCRepo;
	@Autowired
	private PartyRepository partyRepo;
	@Autowired
	private CoinRepository coinRepo;

}
