package com.feut.service;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.StudentConverter;
import com.feut.entity.CourseEntity;
import com.feut.entity.GradeEntity;
import com.feut.entity.GroupEntity;
import com.feut.entity.Role;
import com.feut.entity.StudentEntity;
import com.feut.model.StudentModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.GradeRepository;
import com.feut.repository.GroupRepository;
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
	private GroupRepository groupRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private CourseRepository courseRepository;

	public StudentService() {
		
	}
	
	public List<StudentModel> getAll(){
		List<StudentModel> list =studentConverter.toModel(studentRepository.getAll());
		for (StudentModel model : list) {
			model.setGpa(gradeRepository.getAverageByStudent(model.getId()));
		}
		return list;
	}
	
	public StudentModel getById(Long id) {
		try {
			StudentModel model = studentConverter.toModel(studentRepository.getById(id));
			model.setGpa(gradeRepository.getAverageByStudent(model.getId()));
			return model;
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
		studentRepository.createView();
		if (studentRepository.checkIfExists(model.getPersonalNumber(), model.getUsername()) == false) {
		StudentEntity entity = new StudentEntity();
		entity.setFirstName(model.getFirstName());
		entity.setFatherName(model.getFatherName());
		entity.setEmail(model.getEmail());
		entity.setUsername(model.getUsername());
		entity.setLastName(model.getLastName());
		entity.setActive(true);
		entity.setBirthdate(model.getBirthdate());
		entity.setPassword(passwordEncoder.encode(model.getPassword()));
		GroupEntity group = groupRepository.getById(model.getGroupId());
		entity.setGroup(group);
		entity.setPersonalNumber(model.getPersonalNumber());
		Role studentRole = new Role();
		studentRole.setId(2);
		entity.setRole(studentRole);
		studentRepository.save(entity);
		for (CourseEntity course : courseRepository.getByDegree(group.getDegree().getId())) {
			GradeEntity grade = new GradeEntity();
			grade.setCourse(course);
			grade.setStudent(entity);
			grade.setComment("");
			grade.setCode("");
			grade.setActive(true);
			grade.setCreatedTime(new GregorianCalendar().getTime());
			gradeRepository.save(grade);
		}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bad Request.");
		}
	}
	
	public void edit(StudentModel model, Long id) {
		try {
		StudentEntity entity = studentRepository.getById(id);
		entity.setFirstName(model.getFirstName());
		entity.setFatherName(model.getFatherName());
		entity.setLastName(model.getLastName());
		entity.setGroup(groupRepository.getById(model.getGroupId()));
		entity.setPersonalNumber(model.getPersonalNumber());
		entity.setBirthdate(model.getBirthdate());
		entity.setPassword(passwordEncoder.encode(model.getPassword()));
		studentRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
	}
	
	public List<StudentModel> getByGroup(Long id){
		try {
			return studentConverter.toModel(studentRepository.getByGroup(id));
		} catch(NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
		}
	}
	
	public void delete(Long id) {
		try {
			StudentEntity entity = studentRepository.getById(id);
			studentRepository.edit(entity);
			gradeService.deleteGradesByStudent(id);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
	}
}
