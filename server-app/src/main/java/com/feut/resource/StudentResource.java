package com.feut.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentModel> getById(@PathVariable("id") Long id){
		return new ResponseEntity<StudentModel>(studentService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<StudentModel>> getByName(@PathVariable("name") String name){
		return new ResponseEntity<List<StudentModel>>(studentService.getByName(name), HttpStatus.OK);
	}
	
	@PostMapping
	public void register(@RequestBody StudentModel model) {
		studentService.register(model);
	}
}
