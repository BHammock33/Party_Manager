package com.partymanager.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.partymanager.finalproject.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByUsername(String Username);
	
	List<User> findByName(String name);
	
	@Query("select u from User u where username = :username")
	List<User> findExactlyOneUserByUsername(String username);
	
	

}
