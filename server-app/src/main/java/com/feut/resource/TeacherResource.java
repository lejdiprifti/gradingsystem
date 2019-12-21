package com.feut.resource;

import java.util.List;

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
import com.feut.model.DegreeModel;
import com.feut.model.TeacherModel;
import com.feut.service.CourseService;
import com.feut.service.DegreeService;
import com.feut.service.TeacherService;

@RestController
@RequestMapping(path="/teachers", produces="application/json")
@CrossOrigin("http://localhost:4200")
public class TeacherResource {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private CourseService courseService;
	
	public TeacherResource() {
		
	}
	
	@GetMapping
	public ResponseEntity<List<TeacherModel>> getAll(){
		return new ResponseEntity<List<TeacherModel>>(teacherService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TeacherModel> getById(@PathVariable("id") Long id){
		return new ResponseEntity<TeacherModel>(teacherService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/degrees")
	public ResponseEntity<List<DegreeModel>> getByTeacher(@PathVariable("id") Long id){
		return new ResponseEntity<List<DegreeModel>>(degreeService.getDegreesByTeacher(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/degrees/{degreeId}/courses")
	public ResponseEntity<List<CourseModel>> getByTeacherAndDegree(@PathVariable("id") Long teacherId, @PathVariable("degreeId") Long degreeId){
		return new ResponseEntity<List<CourseModel>>(courseService.getByTeacherAndDegree(teacherId, degreeId), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody TeacherModel model){
		teacherService.save(model);
	}
	
	@PutMapping(path="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void edit(@RequestBody TeacherModel model, @PathVariable("id") Long id) {
		teacherService.edit(model, id);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id")Long id) {
		teacherService.delete(id);
	}
}
