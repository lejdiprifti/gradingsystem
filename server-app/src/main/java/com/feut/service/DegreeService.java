package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.DegreeConverter;
import com.feut.entity.DegreeEntity;
import com.feut.model.DegreeModel;
import com.feut.repository.DegreeRepository;

@Service
public class DegreeService {
	
	@Autowired
	private DegreeRepository degreeRepository;
	
	@Autowired
	private DegreeConverter degreeConverter;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private CourseService courseService;
	
	public DegreeService() {
		
	}
	
	public List<DegreeModel> getAll() {
		List<DegreeModel> list =degreeConverter.toModel(degreeRepository.getAll());
		for (DegreeModel model : list) {
			model.setGroupList(groupService.getByDegree(model.getId()));
			model.setCourseList(courseService.getByDegree(model.getId()));
			model.setNumberOfGroups(countGroupsOfDegree(model.getId()));
		}
		return list;
	}
	
	public DegreeModel getById(Long id) {
		try {
			return degreeConverter.toModel(degreeRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
	}
	
	public Long countGroupsOfDegree(Long id) {
		try {
			return degreeRepository.countGroupsOfDegree(id);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
	}
	
	public void save(DegreeModel model) {
		DegreeEntity entity =new DegreeEntity();
		entity.setSyllabus(model.getSyllabus());
		entity.setTitle(model.getTitle());
		entity.setActive(true);
		degreeRepository.save(entity);
	}
	
	public void edit(DegreeModel model, Long id) {
		try {
			DegreeEntity entity = degreeRepository.getById(id);
			entity.setSyllabus(model.getSyllabus());
			entity.setTitle(model.getTitle());
			degreeRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
	}
	
	public void delete(Long id) {
		try {
			DegreeEntity entity = degreeRepository.getById(id);
			entity.setActive(false);
			degreeRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
	}
}
