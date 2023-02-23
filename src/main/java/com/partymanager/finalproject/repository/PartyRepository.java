package com.partymanager.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partymanager.finalproject.domain.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long>{

}
