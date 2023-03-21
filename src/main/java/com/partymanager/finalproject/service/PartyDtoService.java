package com.partymanager.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.dto.PartyDto;
import com.partymanager.finalproject.repository.PartyDtoRepository;

@Service
public class PartyDtoService {

	@Autowired
	private PartyDtoRepository partyDtoRepo;
	
	public PartyDto findById(Long partyDtoId) {
		return partyDtoRepo.findById(partyDtoId);
	}
}
