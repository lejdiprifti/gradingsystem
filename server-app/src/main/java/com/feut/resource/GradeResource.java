package com.feut.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.GradeModel;
import com.feut.service.GradeService;

@RestController
@RequestMapping(path="/grades", produces="application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class GradeResource {
	
	@Autowired
	private GradeService gradeService;
	
	
	
	public GradeResource() {
		
	}
	
	@PutMapping(path="/{id}",consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody GradeModel model, @PathVariable("id") Long id) {
		gradeService.edit(model, id);
	}
}

