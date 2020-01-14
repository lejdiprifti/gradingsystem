package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.DegreeConverter;
import com.feut.entity.CourseEntity;
import com.feut.entity.DegreeEntity;
import com.feut.entity.GroupEntity;
import com.feut.model.DegreeModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.DegreeRepository;
import com.feut.repository.GroupRepository;
import com.feut.security.JwtTokenUtil;

@Service
public class DegreeService {
	
	
	@Autowired
	private DegreeRepository degreeRepository;
	
	@Autowired
	private DegreeConverter degreeConverter;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public DegreeService() {
		
	}
	
	public List<DegreeModel> getAll() {
		if (jwtTokenUtil.getRole().getId() == 1) {
		List<DegreeModel> list =degreeConverter.toModel(degreeRepository.getAll());
		for (DegreeModel model : list) {
			model.setGroupList(groupService.getByDegree(model.getId()));
			model.setCourseList(courseService.getByDegree(model.getId()));
			model.setNumberOfGroups(countGroupsOfDegree(model.getId()));
		}
		return list;
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	public DegreeModel getById(Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		try {
			return degreeConverter.toModel(degreeRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	public Long countGroupsOfDegree(Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		try {
			return degreeRepository.countGroupsOfDegree(id);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	public List<DegreeModel> getDegreesByTeacher(Long id){
		if (jwtTokenUtil.getRole().getId() == 3) {
		try {
			return degreeConverter.toModel(degreeRepository.getByTeacher(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degrees not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	public void save(DegreeModel model) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		if (degreeRepository.checkIfExists(model.getTitle()) == false) {
		DegreeEntity entity =new DegreeEntity();
		entity.setSyllabus(model.getSyllabus());
		entity.setTitle(model.getTitle());
		entity.setActive(true);
		degreeRepository.save(entity);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Degree already exists.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	public void edit(DegreeModel model, Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		try {
			DegreeEntity entity = degreeRepository.getById(id);
			if (degreeRepository.checkIfTitleExists(model.getTitle(), id)==false) {
				entity.setTitle(model.getTitle());
			}
			entity.setSyllabus(model.getSyllabus());
			degreeRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	public void delete(Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		try {
			DegreeEntity entity = degreeRepository.getById(id);
			List<GroupEntity> groupList = groupRepository.getByDegree(id);
			for (GroupEntity group: groupList) {
				group.setActive(false);
				groupRepository.edit(group);
			}
			List<CourseEntity> courseList = courseRepository.getByDegree(id);
			for (CourseEntity course: courseList) {
				course.setActive(false);
				courseRepository.edit(course);
			}
			entity.setActive(false);
			degreeRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		} 
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
}
