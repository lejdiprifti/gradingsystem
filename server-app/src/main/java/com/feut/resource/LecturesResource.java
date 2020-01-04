package com.feut.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.LecturesModel;
import com.feut.service.LecturesService;

@RestController
@RequestMapping(path= "/lectures", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class LecturesResource {
	
	@Autowired
	private LecturesService lecturesService;
	
	public LecturesResource() {
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LecturesModel> getById(@PathVariable("id") Long id){
		return new ResponseEntity<LecturesModel>(lecturesService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/group/{groupId}/course/{courseId}")
	public ResponseEntity<LecturesModel> getByGroupAndCourse(@PathVariable("groupId") Long groupId, @PathVariable("courseId") Long courseId){
		return new ResponseEntity<LecturesModel>(lecturesService.getByGroupAndCourse(groupId, courseId), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody LecturesModel model) {
		lecturesService.save(model);
	}
}
