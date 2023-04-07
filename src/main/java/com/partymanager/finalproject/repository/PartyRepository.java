package com.partymanager.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.partymanager.finalproject.domain.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

	@Query("select p from Party p " + "where p.partyName =:partyName")
	Party findByPartyName(String partyName);

	// same with PC repo, finding by name is used for mapping DTO to Party

	
}
