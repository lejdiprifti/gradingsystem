package com.feut.service;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.CourseConverter;
import com.feut.entity.CourseEntity;
import com.feut.entity.DegreeEntity;
import com.feut.entity.GradeEntity;
import com.feut.entity.GroupEntity;
import com.feut.entity.LecturesEntity;
import com.feut.entity.StudentEntity;
import com.feut.model.CourseModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.DegreeRepository;
import com.feut.repository.DepartmentRepository;
import com.feut.repository.GradeRepository;
import com.feut.repository.GroupRepository;
import com.feut.repository.LecturesRepository;
import com.feut.repository.StudentRepository;
import com.feut.security.JwtTokenUtil;

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
	
	@Autowired
	private LecturesRepository lecturesRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
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
	
	public List<CourseModel> getByTeacherAndDegree(Long teacherId, Long degreeId){
		if (jwtTokenUtil.getRole().getId() == 3) {
		try {
			return courseConverter.toModel(courseRepository.getByTeacherAndDegree(teacherId, degreeId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Courses not found.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	public List<CourseModel> getByStudent(Long studentId){
		try {
			return courseConverter.toModel(courseRepository.getByStudent(studentId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Courses not found.");
		}
	}
	
	public void save(CourseModel model) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		try {
		CourseEntity entity = new CourseEntity();
		if (courseRepository.checkIfExistsByDegree(model.getName(), model.getDegreeId(), model.getDepartmentId())==false) {
		entity.setName(model.getName());
		entity.setSyllabus(model.getSyllabus());
		DegreeEntity degree = degreeRepository.getById(model.getDegreeId());
		entity.setDegree(degree);
		entity.setDepartment(departmentRepository.getById(model.getDepartmentId()));
		entity.setActive(true);
		courseRepository.save(entity);
		LecturesEntity lecture = new LecturesEntity();
		lecture.setCourse(entity);
		lecture.setTeacher(null);
		lecture.setActive(true);
		for (GroupEntity group : groupRepository.getByDegree(model.getDegreeId())) {
			lecture.setGroup(group);
			lecturesRepository.save(lecture);
		}
		GradeEntity grade = new GradeEntity();
		grade.setCourse(entity);
		for (StudentEntity student : studentRepository.getByDegree(degree)) {
			grade.setStudent(student);
			grade.setActive(true);
			grade.setCode("");
			grade.setComment("");
			grade.setCreatedTime(new GregorianCalendar().getTime());
			gradeRepository.save(grade);
		}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course already exists");
		}
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request.");
		}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
		}
	
	public void edit(CourseModel model, Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		CourseEntity entity = courseRepository.getById(id);
		if (model.getName() != null) {
			entity.setName(model.getName());
		}
		if (model.getSyllabus() != null) {
			entity.setSyllabus(model.getSyllabus());
		}
		if (model.getDepartmentId() != null) {
		entity.setDepartment(departmentRepository.getById(model.getDepartmentId()));
		}
		if (model.getDegreeId() != null) {
		entity.setDegree(degreeRepository.getById(model.getDegreeId()));
		}
		entity.setActive(true);
		courseRepository.edit(entity);
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
	
	public void delete(Long id) {
		if (jwtTokenUtil.getRole().getId() == 1) {
		CourseEntity entity = courseRepository.getById(id);
		List<GradeEntity> gradeList = gradeRepository.getGradesByCourse(id);
		for (GradeEntity grade : gradeList) {
			grade.setActive(false);
			gradeRepository.edit(grade);
		}
		List<LecturesEntity> lecturesList = lecturesRepository.getByCourse(id);
		for (LecturesEntity lecture: lecturesList) {
			lecture.setActive(false);
			lecturesRepository.edit(lecture);
		}
		entity.setActive(false);
		courseRepository.edit(entity);
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action is unauthorized.");
		}
	}
}
