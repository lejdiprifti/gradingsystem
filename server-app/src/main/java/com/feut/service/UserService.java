package com.feut.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.UserConverter;
import com.feut.model.UserModel;
import com.feut.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserService() {
		
	}
	
	public UserModel getByUsername(String username) {
		try {
			return userConverter.toModel(userRepository.getByUsername(username));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
	}
}
