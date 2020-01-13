package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.LecturesConverter;
import com.feut.entity.LecturesEntity;
import com.feut.model.LecturesModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.GroupRepository;
import com.feut.repository.LecturesRepository;
import com.feut.repository.TeacherRepository;
import com.feut.security.JwtTokenUtil;

@Service
public class LecturesService {
	
	@Autowired
	private LecturesRepository lecturesRepository;
	
	@Autowired
	private LecturesConverter lecturesConverter;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public LecturesService() {
		
	}
	
	public List<LecturesModel> getByGroup(Long groupId){
		try {
		return lecturesConverter.toModel(lecturesRepository.getByGroup(groupId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lectures not found.");
		}
	}
	
	public List<LecturesModel> getByTeacher(Long teacherId){
		try {
		return lecturesConverter.toModel(lecturesRepository.getByTeacher(teacherId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lectures not found.");
		}
	}
	
	public LecturesModel getById(Long id) {
		try {
			return lecturesConverter.toModel(lecturesRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lectures not found.");
		}
	}
	
	public LecturesModel getByGroupAndCourse(Long groupId, Long courseId) {
		try {
			return lecturesConverter.toModel(lecturesRepository.getByGroupAndCourse(groupId, courseId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lectures not found.");
		}
	}
	
	public void save(LecturesModel model) {
		try {
			LecturesEntity entity = lecturesRepository.getByGroupAndCourse(model.getGroupId(),model.getCourseId());
			entity.setGroup(groupRepository.getById(model.getGroupId()));
			entity.setTeacher(teacherRepository.getById(model.getTeacherId()));
			entity.setCourse(courseRepository.getById(model.getCourseId()));
			entity.setActive(true);
			lecturesRepository.edit(entity);
		} catch (NoResultException e) {
			LecturesEntity entity = new LecturesEntity();
			entity.setGroup(groupRepository.getById(model.getGroupId()));
			entity.setTeacher(teacherRepository.getById(model.getTeacherId()));
			entity.setCourse(courseRepository.getById(model.getCourseId()));
			entity.setActive(true);
			lecturesRepository.save(entity);
		}
	}
	
	public void edit(LecturesModel model, Long id) {
		if (jwtTokenUtil.getRole().getId() == 3) {
		try {
			LecturesEntity entity = lecturesRepository.getById(id);
			entity.setGroup(groupRepository.getById(model.getGroupId()));
			entity.setCourse(courseRepository.getById(model.getCourseId()));
			entity.setTeacher(teacherRepository.getById(model.getTeacherId()));
			lecturesRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group/Teacher/Course not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	
}
