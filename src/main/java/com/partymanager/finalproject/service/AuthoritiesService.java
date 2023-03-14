package com.partymanager.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymanager.finalproject.repository.AuthoritiesRepository;
import com.partymanager.finalproject.security.Authorities;

@Service
public class AuthoritiesService {

	@Autowired
	private AuthoritiesRepository authRepo;
	
	public void saveAuth(Authorities auth) {
		authRepo.save(auth);
	}
//	public List<Authorities> createAuthoritiesStrings(){
//		List<Authorities> authorities = new ArrayList<>();
//		Authorities dmAuthority = new Authorities("DM");
//		Authorities userAuthority = new Authorities("USER");
//		authorities.add(userAuthority);
//		authorities.add(dmAuthority);
//		return authorities;
//	}
}
