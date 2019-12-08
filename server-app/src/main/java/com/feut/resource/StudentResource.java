package com.feut.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.StudentModel;
import com.feut.service.StudentService;

@RestController
@RequestMapping(path="/student", consumes="application/json", produces="application/json")
public class StudentResource {
	
	@Autowired
	private StudentService studentService;
	
	public StudentResource() {
		
	}
	
	@PostMapping
	public void register(@RequestBody StudentModel model) {
		studentService.register(model);
	}
}
