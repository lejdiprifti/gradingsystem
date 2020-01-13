package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.TeacherConverter;
import com.feut.entity.LecturesEntity;
import com.feut.entity.Role;
import com.feut.entity.TeacherEntity;
import com.feut.model.TeacherModel;
import com.feut.repository.DepartmentRepository;
import com.feut.repository.LecturesRepository;
import com.feut.repository.TeacherRepository;
import com.feut.repository.UserRepository;
import com.feut.security.JwtTokenUtil;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TeacherConverter teacherConverter;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private LecturesRepository lecturesRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public TeacherService() {

	}

	public List<TeacherModel> getAll(){
		if (jwtTokenUtil.getRole().getId() == 1) {
		return teacherConverter.toModel(teacherRepository.getAll());
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}

	public TeacherModel getByUsername(String username) {
		try {
			return teacherConverter.toModel(teacherRepository.getByUsername(username));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found.");
		}
	}

	public TeacherModel getById(Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		try {
			return teacherConverter.toModel(teacherRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}

	public List<TeacherModel> getByDepartment(Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		return teacherConverter.toModel(teacherRepository.getByDepartment(id));
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}

	public void save(TeacherModel model) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		if (userRepository.checkIfExists(model.getPersonalNumber(), model.getUsername()) == false) {
		TeacherEntity entity = new TeacherEntity();
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		entity.setFatherName(model.getFatherName());
		entity.setBirthdate(model.getBirthdate());
		entity.setEmail(model.getEmail());
		entity.setDepartment(departmentRepository.getById(model.getDepartmentId()));
		entity.setPassword(passwordEncoder.encode(model.getPassword()));
		entity.setUsername(model.getUsername());
		entity.setPersonalNumber(model.getPersonalNumber());
		Role role = new Role();
		role.setId(3);
		entity.setRole(role);
		entity.setActive(true);
		teacherRepository.save(entity);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}

	public void edit(TeacherModel model, Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		try {
			TeacherEntity entity = teacherRepository.getById(id);
			entity.setFirstName(model.getFirstName());
			entity.setLastName(model.getLastName());
			entity.setFatherName(model.getFatherName());
			entity.setBirthdate(model.getBirthdate());
			entity.setEmail(model.getEmail());
			entity.setDepartment(departmentRepository.getById(model.getDepartmentId()));
			entity.setPassword(passwordEncoder.encode(model.getPassword()));
			if (teacherRepository.checkIfExists(model.getPersonalNumber(), id) == false) {
			entity.setPersonalNumber(model.getPersonalNumber());
			teacherRepository.edit(entity);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request.");
			}
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}

	public void delete(Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		try {
			TeacherEntity entity = teacherRepository.getById(id);
			List<LecturesEntity> list = lecturesRepository.getByTeacher(id);
			for (LecturesEntity lecture: list) {
				lecture.setTeacher(null);
				lecturesRepository.edit(lecture);
			}
			entity.setActive(false);
			teacherRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
}
