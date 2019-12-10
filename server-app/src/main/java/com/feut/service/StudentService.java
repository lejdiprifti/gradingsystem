package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.GroupConverter;
import com.feut.converter.StudentConverter;
import com.feut.entity.Role;
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
	
	@Autowired
	private GroupConverter groupConverter;
	
	public StudentService() {
		
	}
	
	public List<StudentModel> getAll(){
		return studentConverter.toModel(studentRepository.getAll());
	}
	
	public StudentModel getById(Long id) {
		try {
			return studentConverter.toModel(studentRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
	}
	
	public StudentModel getByUsername(String username) {
		try {
			return studentConverter.toModel(studentRepository.getByUsername(username));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
	}
	
	public List<StudentModel> getByName(String name){
			return studentConverter.toModel(studentRepository.getByName(name));
	}
	
	public void register(StudentModel model) {
		StudentEntity entity = new StudentEntity();
		entity.setFirstName(model.getFirstName());
		entity.setFatherName(model.getFatherName());
		entity.setEmail(model.getEmail());
		entity.setUsername(model.getUsername());
		entity.setLastName(model.getLastName());
		entity.setActive(true);
		entity.setBirthdate(model.getBirthdate());
		entity.setPassword(passwordEncoder.encode(model.getPassword()));
		entity.setGroup(groupConverter.toEntity(model.getGorup()));
		entity.setPersonalNumber(model.getPersonalNumber());
		Role studentRole = new Role();
		studentRole.setId(2);
		entity.setRole(studentRole);
		studentRepository.save(entity);
	}
	
	public void edit(StudentModel model, Long id) {
		try {
		StudentEntity entity = studentRepository.getById(id);
		entity.setFirstName(model.getFirstName());
		entity.setFatherName(model.getFatherName());
		entity.setLastName(model.getLastName());
		entity.setGroup(groupConverter.toEntity(model.getGorup()));
		entity.setPersonalNumber(model.getPersonalNumber());
		entity.setBirthdate(model.getBirthdate());
		entity.setPassword(passwordEncoder.encode(model.getPassword()));
		studentRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
	}
	
	public void delete(Long id) {
		try {
			StudentEntity entity = studentRepository.getById(id);
			studentRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
	}
}
