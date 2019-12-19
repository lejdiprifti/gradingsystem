package com.feut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feut.converter.CourseConverter;
import com.feut.entity.CourseEntity;
import com.feut.entity.DegreeEntity;
import com.feut.model.CourseModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.DegreeRepository;
import com.feut.repository.DepartmentRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseConverter courseConverter;
	
	@Autowired
	private DegreeRepository degreeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public CourseService() {
		
	}
	
	public List<CourseModel> getAll() {
		return courseConverter.toModel(courseRepository.getAll());
	}
	
	public List<CourseModel> getByDegree(Long degreeId){
		return courseConverter.toModel(courseRepository.getByDegree(degreeId));
	}
	
	public CourseModel getById(Long id) {
		return courseConverter.toModel(courseRepository.getById(id));
	}
	
	public List<CourseModel> getByDepartment(Long id){
		return courseConverter.toModel(courseRepository.getByDepartment(departmentRepository.getById(id)));
	}
	
	public void save(CourseModel model) {
		CourseEntity entity = new CourseEntity();
		entity.setName(model.getName());
		entity.setSyllabus(model.getSyllabus());
		entity.setDegree(degreeRepository.getById(model.getDegreeId()));
		entity.setDepartment(departmentRepository.getById(model.getDepartmentId()));
		entity.setActive(true);
		courseRepository.save(entity);
	}
	
	public void edit(CourseModel model, Long id) {
		CourseEntity entity = courseRepository.getById(id);
		entity.setName(model.getName());
		entity.setSyllabus(model.getSyllabus());
		entity.setDepartment(departmentRepository.getById(model.getDepartmentId()));
		entity.setDegree(degreeRepository.getById(model.getDegreeId()));
		entity.setActive(true);
		courseRepository.edit(entity);
	}
	
	public void delete(Long id) {
		CourseEntity entity = courseRepository.getById(id);
		entity.setActive(false);
		courseRepository.edit(entity);
	}
}
