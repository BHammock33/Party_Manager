package com.partymanager.finalproject.service;

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

}
