package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.DepartmentConverter;
import com.feut.entity.CourseEntity;
import com.feut.entity.DepartmentEntity;
import com.feut.entity.TeacherEntity;
import com.feut.model.DepartmentModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.DepartmentRepository;
import com.feut.repository.TeacherRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository depRepository;
	
	@Autowired
	private DepartmentConverter depConverter;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TeacherService teacherService;
	
	public DepartmentService() {
		
	}
	
	public List<DepartmentModel> getAll()	{
		List<DepartmentModel> list = depConverter.toModel(depRepository.getAll());
		for (DepartmentModel model : list) {
			model.setCourseList(courseService.getByDepartment(model.getId()));
			model.setTeacherList(teacherService.getByDepartment(model.getId()));
		}
		return list;
	}
	
	public DepartmentModel getById(Long id) {
		try {
			return depConverter.toModel(depRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
		}
	}
	
	public void save(DepartmentModel model) {
		if (depRepository.checkIfExists(model.getName())==false) {
		DepartmentEntity entity = new DepartmentEntity();
		entity.setName(model.getName());
		entity.setDescription(model.getDescription());
		entity.setActive(true);
		depRepository.save(entity);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request.");
		}
	}
	
	public void edit(DepartmentModel model, Long id) {
		try {
			DepartmentEntity entity = depRepository.getById(id);
			if (depRepository.checkIfNameExists(model.getName(), id) == false) {
			entity.setDescription(model.getDescription());
			entity.setName(model.getName());
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request.");
			}
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
		} 
	}
	
	public void delete(Long id) {
		try {
			DepartmentEntity entity = depRepository.getById(id);
			List<CourseEntity> courseList = courseRepository.getByDepartment(entity);
			for (CourseEntity course: courseList) {
				course.setDepartment(null);
				courseRepository.edit(course);
			}
			List<TeacherEntity> teacherList = teacherRepository.getByDepartment(id);
			for (TeacherEntity teacher : teacherList) {
				teacher.setDepartment(null);
				teacherRepository.edit(teacher);
			}
			entity.setActive(false);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
		}
	}
}
