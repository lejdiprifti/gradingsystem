package com.feut.resource;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.CourseModel;
import com.feut.model.GradeModel;
import com.feut.model.StudentModel;
import com.feut.service.CourseService;
import com.feut.service.GradeService;
import com.feut.service.StudentService;

@RestController
@RequestMapping(path="/student", produces="application/json")
@CrossOrigin(origins = {"https://myfeut.firebaseapp.com", "http://localhost:4200"})
public class StudentResource {
	
	private Logger logger = LogManager.getLogger(StudentResource.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private CourseService courseService;
	
	public StudentResource() {
		
	}
	
	@GetMapping 
	public ResponseEntity<List<StudentModel>> getAll(){
		logger.info("GETTING ALL STUDENTS.");
		return new ResponseEntity<List<StudentModel>>(studentService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentModel> getById(@PathVariable("id") Long id){
		logger.info("GETTING STUDENT BY ID.");
		return new ResponseEntity<StudentModel>(studentService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/grades")
	public ResponseEntity<List<GradeModel>> getGradesByStudent(@PathVariable("id") Long studentId){
		logger.info("GETTING GRADES BY STUDENT.");
		return new ResponseEntity<List<GradeModel>>(gradeService.getGradesByStudent(studentId), HttpStatus.OK);
	}
	
	@GetMapping("/query={name}")
	public ResponseEntity<List<StudentModel>> getByName(@PathVariable("name") String name){
		return new ResponseEntity<List<StudentModel>>(studentService.getByName(name), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/courses")
	public ResponseEntity<List<CourseModel>> getByStudent(@PathVariable("id") Long studentId){
		logger.info("GETTING COURSES BY STUDENT.");
		return new ResponseEntity<List<CourseModel>>(courseService.getByStudent(studentId), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void register(@RequestBody StudentModel model) {
		logger.info("ADDING NEW STUDENT.");
		studentService.register(model);
	}
	
	@PutMapping(path="/{id}", consumes ="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void edit(@RequestBody StudentModel model, @PathVariable("id") Long id) {
		logger.info("EDITING STUDENT BY ID.");
		studentService.edit(model, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		logger.info("DELETING STUDENT.");
		studentService.delete(id);
	}
}

