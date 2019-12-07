package com.feut.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.TeacherConverter;
import com.feut.model.TeacherModel;
import com.feut.repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
	public TeacherService() {
		
	}
	
	public TeacherModel getByUsername(String username) {
		try {
			return teacherConverter.toModel(teacherRepository.getByUsername(username));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found.");
		}
	}
}
