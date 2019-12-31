package com.feut.service;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.GradeConverter;
import com.feut.entity.GradeEntity;
import com.feut.model.GradeModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.GradeRepository;
import com.feut.repository.StudentRepository;
import com.feut.repository.TeacherRepository;


@Service
public class GradeService {
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private GradeConverter gradeConverter;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	
	public GradeService() {
		
	}
	
	public GradeModel getById(Long gradeId) {
		try {
			return gradeConverter.toModel(gradeRepository.getById(gradeId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not found.");
		}
	}
	
	public List<GradeModel> getByGroupAndCourse(Long groupId, Long courseId){
		try {
			return gradeConverter.toModel(gradeRepository.getGradesByGroupAndCourse(groupId, courseId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grades not found.");
		}
	}
	
	public void edit(GradeModel model, Long id) {
		GradeEntity entity = gradeRepository.getById(id);
		entity.setCode(model.getCode());
		entity.setComment(model.getComment());
		entity.setGrade(model.getGrade());
		entity.setCreatedTime(new GregorianCalendar().getTime());
		entity.setActive(true);
		entity.setCourse(courseRepository.getById(model.getCourseId()));
		entity.setTeacher(teacherRepository.getById(model.getTeacherId()));
		gradeRepository.edit(entity);
	}
	
	public List<GradeModel> getGradesByStudent(Long studentId){
		try {
			return gradeConverter.toModel(gradeRepository.getGradesByStudent(studentId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grades not found.");
		}
	}
	
	public void deleteGradesByStudent(Long studentId) {
		List<GradeEntity> list = gradeRepository.getGradesByStudent(studentId);
		for (GradeEntity grade : list) {
			grade.setActive(false);
			gradeRepository.edit(grade);
		}
	}
}

