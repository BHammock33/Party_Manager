package com.partymanager.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.partymanager.finalproject.domain.PlayerCharacter;
import com.partymanager.finalproject.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u " + "left join fetch u.authorities " + "where u.username = :username")
	User findByUsername(String username);

	Boolean existsByUsername(String username);

	@Query("select u from User u " + "left join fetch u.characters " + "where u.username = :username")
	List<PlayerCharacter> findUserCharacters(String username);

	@Query("select u from User u where u.firstName = :firstName")
	User findByFirstName(String firstName);

}