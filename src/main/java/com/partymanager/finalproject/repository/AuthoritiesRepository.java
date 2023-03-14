package com.partymanager.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partymanager.finalproject.security.Authorities;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {


}
