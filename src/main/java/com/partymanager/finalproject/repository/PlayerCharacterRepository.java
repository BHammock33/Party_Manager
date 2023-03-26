package com.partymanager.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.partymanager.finalproject.domain.PlayerCharacter;

@Repository
public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, Long> {

	@Query(value = "select * from characters c where c.name = :name", nativeQuery = true)
	public PlayerCharacter findByName(String name);


	
	
	
	
	
}
