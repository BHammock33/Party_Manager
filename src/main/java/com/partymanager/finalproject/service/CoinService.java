package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.domain.Coin;
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

	
	public List<Coin> createCoinsGold(){
		List<Coin> gold = new ArrayList<>();
		Coin coin = new Coin();
		coin.setQuantity(100);
		coin.setType("gold");
		gold.add(coin);
		return gold;
	}
}
