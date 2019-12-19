package com.feut.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.CourseModel;
import com.feut.service.CourseService;

@RestController
@RequestMapping(path="/courses", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseResource {
	
	@Autowired
	private CourseService courseService;
	
	public CourseResource() {
		
	}
	
	@GetMapping
	public ResponseEntity<List<CourseModel>> getAll(){
		return new ResponseEntity<List<CourseModel>>(courseService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody CourseModel model) {
		courseService.save(model);
	}
}
