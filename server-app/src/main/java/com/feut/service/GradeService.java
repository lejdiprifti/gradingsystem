package com.feut.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.GradeConverter;
import com.feut.entity.GradeEntity;
import com.feut.entity.TeacherEntity;
import com.feut.model.GradeModel;
import com.feut.model.SlackModel;
import com.feut.repository.CourseRepository;
import com.feut.repository.GradeRepository;
import com.feut.repository.StudentRepository;
import com.feut.repository.TeacherRepository;

@Service
public class GradeService {

	private static Logger logger = LogManager.getLogger(GradeService.class);
	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private GradeConverter gradeConverter;

	@Autowired
	private SlackService slackService;
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;
	
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

	public List<GradeModel> getByGroupAndCourse(Long groupId, Long courseId) {
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
		TeacherEntity teacher = teacherRepository.getById(model.getTeacherId());
		entity.setTeacher(teacher);
		gradeRepository.edit(entity);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		List<SlackModel> slackList = slackService.getByStudent(entity.getStudent().getId());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		for (SlackModel slack : slackList) {
			httpHeaders.setBearerAuth(slack.getBotToken());
			String text = "Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " graded "
					+ entity.getGrade() + " on subject " + entity.getCourse().getName();
			JSONObject object = new JSONObject();
			object.put("text", text);
			HttpPost post = new HttpPost(slack.getUrl());
			StringEntity stringEntity;
			try {
				stringEntity = new StringEntity(object.toString());
				post.addHeader("Content-Type", "application/json");
				stringEntity.setContentType("application/json");
				post.setEntity(stringEntity);
				try {
					logger.info(httpclient.execute(post).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<GradeModel> getGradesByStudent(Long studentId) {
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

	public Double getAverageByGroupCourseAndTeacher(Long teacherId, Long groupId, Long courseId) {
		try {
			if (studentRepository.getByGroup(groupId).size() > 0) {
			return gradeRepository.getAverageByGroupCourseAndTeacher(groupId, courseId, teacherId);
			} else {
				return 0.0;
			}
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grades not found.");
		}
	}
}
