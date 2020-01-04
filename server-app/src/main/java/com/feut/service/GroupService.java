package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.GroupConverter;
import com.feut.entity.CourseEntity;
import com.feut.entity.GroupEntity;
import com.feut.entity.LecturesEntity;
import com.feut.entity.StudentEntity;
import com.feut.model.GroupModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.DegreeRepository;
import com.feut.repository.GroupRepository;
import com.feut.repository.LecturesRepository;
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
	
	@Autowired
	private LecturesRepository lecturesRepository;
	
	@Autowired
	private CourseRepository courseRepository;

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
			List<GroupModel> list = groupConverter.toModel(groupRepository.getByDegree(id));
			for (GroupModel model : list) {
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
			for (GroupModel model : list) {
				model.setStudentList(studentService.getByGroup(model.getId()));
			}
			return list;
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Groups not found.");
		}
	}

	public void save(GroupModel model, Long id) {
		try {
			GroupEntity entity = new GroupEntity();
			if (groupRepository.checkIfExists(model.getNumber(), model.getDegreeId()) == false) {
				entity.setNumber(model.getNumber());
				entity.setDegree(degreeRepository.getById(id));
				entity.setEmail(model.getEmail());
				entity.setActive(true);
				groupRepository.save(entity);
				List<CourseEntity> courseList = courseRepository.getByDegree(entity.getDegree().getId());
				for (CourseEntity course : courseList) {
					LecturesEntity lectures = new LecturesEntity();
					lectures.setCourse(course);
					lectures.setGroup(entity);
					lectures.setActive(true);
					lecturesRepository.save(lectures);
				}
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group already exists.");
			}
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
	}

	public void edit(GroupModel model, Long id) {
		try {
			GroupEntity entity = groupRepository.getById(id);
			if (groupRepository.checkIfNumberExists(model.getNumber(), model.getDegreeId(), id) == false) {
				entity.setNumber(model.getNumber());
			}
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