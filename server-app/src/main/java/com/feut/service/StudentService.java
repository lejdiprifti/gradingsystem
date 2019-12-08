package com.feut.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.StudentConverter;
import com.feut.entity.StudentEntity;
import com.feut.model.StudentModel;
import com.feut.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private StudentConverter studentConverter;
	
	public StudentService() {
		
	}
	
	public StudentModel getByUsername(String username) {
		try {
			return studentConverter.toModel(studentRepository.getByUsername(username));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
	}
	
	public void register(StudentModel model) {
		StudentEntity entity = studentConverter.toEntity(model);
		entity.setPassword(passwordEncoder.encode(model.getPassword()));
		studentRepository.save(entity);
	}
}
