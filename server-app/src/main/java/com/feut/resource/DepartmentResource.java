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
import com.feut.model.DepartmentModel;
import com.feut.model.TeacherModel;
import com.feut.service.CourseService;
import com.feut.service.DepartmentService;
import com.feut.service.TeacherService;

@RestController
@RequestMapping(path="/departments", produces="application/json")
@CrossOrigin("http://localhost:4200")
public class DepartmentResource {
	
	@Autowired
	private DepartmentService depService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CourseService courseService;
	
	public DepartmentResource() {
		
	}
	
	@GetMapping
	public ResponseEntity<List<DepartmentModel>> getAll(){
		return new ResponseEntity<List<DepartmentModel>>(depService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentModel> getById(@PathVariable("id") Long id){
		return new ResponseEntity<DepartmentModel>(depService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/teachers")
	public ResponseEntity<List<TeacherModel>> getTeachersByDepartment(@PathVariable("id") Long id){
		return new ResponseEntity<List<TeacherModel>>(teacherService.getByDepartment(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/courses")
	public ResponseEntity<List<CourseModel>> getCoursesByDepartment(@PathVariable("id") Long id){
		return new ResponseEntity<List<CourseModel>>(courseService.getByDepartment(id), HttpStatus.OK);
	}
	
	@PostMapping(consumes ="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody DepartmentModel model) {
		depService.save(model);
	}
	
	@PutMapping(path = "/{id}", consumes ="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void edit(@RequestBody DepartmentModel model, @PathVariable Long id) {
		depService.edit(model, id);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		depService.delete(id);
	}
}
