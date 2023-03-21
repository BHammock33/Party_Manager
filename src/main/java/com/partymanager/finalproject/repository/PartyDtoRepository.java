package com.partymanager.finalproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partymanager.finalproject.domain.Party;
import com.partymanager.finalproject.dto.PartyDto;

@Repository
public class PartyDtoRepository {
	@Autowired
	private PartyRepository partyRepo;

	private List<PartyDto> partyDtosList = new ArrayList<>(150);
	
	ModelMapper modelMapper = new ModelMapper();
	
	public PartyDtoRepository() {
		super();
	}
	
	
	private PartyDto convertToDto(Party party) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		PartyDto partyDto = modelMapper.map(party, PartyDto.class);
		System.out.println(partyDto +"inside partyDTORepo");
		return partyDto;
	}
	private List<PartyDto> convertList(){
		List<Party> parties = partyRepo.findAll();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		for(Party party : parties) {
			PartyDto partyDto = modelMapper.map(party, PartyDto.class);
			partyDtosList.add(partyDto);
		}
		System.out.println(partyDtosList);
		return partyDtosList;
	}


	public PartyDto findById(Long partyDtoId) {
		for(PartyDto partyDto : partyDtosList) {
			if((partyDto.getPartyDtoId()).equals(partyDtoId))
				return partyDto;
		}
		return null;
	}
}
