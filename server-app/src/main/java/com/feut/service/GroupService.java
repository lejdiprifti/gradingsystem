package com.feut.service;

import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.GroupConverter;
import com.feut.entity.GroupEntity;
import com.feut.entity.StudentEntity;
import com.feut.model.GroupModel;
import com.feut.repository.DegreeRepository;
import com.feut.repository.GroupRepository;
import com.feut.repository.StudentRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupConverter groupConverter;

	@Autowired
	private DegreeRepository degreeRepository;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public GroupService() {

	}

	public List<GroupModel> getAll() {
		return groupConverter.toModel(groupRepository.getAll());
	}

	public GroupModel getById(Long id) {
		try {
			return groupConverter.toModel(groupRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
		}
	}

	public List<GroupModel> getByDegree(Long id) {
		try {
			List<GroupModel> list =groupConverter.toModel(groupRepository.getByDegree(id));
			for (GroupModel model:list) {
				model.setStudentList(studentService.getByGroup(model.getId()));
			}
			return list;
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
	}
	
	public List<GroupModel> getByTeacherAndDegree(Long teacherId, Long degreeId) {
		try {
			List<GroupModel> list = groupConverter.toModel(groupRepository.getByTeacherAndDegree(teacherId, degreeId));
			for (GroupModel model: list) {
				model.setStudentList(studentService.getByGroup(model.getId()));
			}
			return list;
		}catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Groups not found.");
		}
	}

	public void save(GroupModel model, Long id) {
		try {
		GroupEntity entity = new GroupEntity();
		entity.setNumber(model.getNumber());
		entity.setDegree(degreeRepository.getById(id));
		entity.setEmail(model.getEmail());
		entity.setActive(true);
		groupRepository.save(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
	}

	public void edit(GroupModel model, Long id) {
		try {
			GroupEntity entity = groupRepository.getById(id);
			entity.setNumber(model.getNumber());
			entity.setEmail(model.getEmail());
			groupRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
		}
	}

	public void delete(Long id) {
		try {
			GroupEntity entity = groupRepository.getById(id);
			entity.setActive(false);
			List<StudentEntity> list = studentRepository.getByGroup(id);
			for (StudentEntity student : list) {
				student.setGroup(null);
				studentRepository.edit(student);
			}
			groupRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
		}
	}
}