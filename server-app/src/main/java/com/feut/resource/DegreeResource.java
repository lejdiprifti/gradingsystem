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
import com.feut.model.DegreeModel;
import com.feut.model.GroupModel;
import com.feut.service.CourseService;
import com.feut.service.DegreeService;
import com.feut.service.GroupService;

@RestController
@RequestMapping(path="/degree", produces ="application/json")
@CrossOrigin(origins = {"https://myfeut.firebaseapp.com", "http://localhost:4200"})
public class DegreeResource {
	
	private Logger logger = LogManager.getLogger(DegreeResource.class);
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private CourseService courseService;
	
	public DegreeResource() {
		
	}
	
	@GetMapping
	public ResponseEntity<List<DegreeModel>> getAll(){
		logger.info("GETTING ALL DEGREES.");
		return new ResponseEntity<List<DegreeModel>>(degreeService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DegreeModel> getById(@PathVariable("id") Long id){
		logger.info("GETTING DEGREE BY ID.");
		return new ResponseEntity<DegreeModel>(degreeService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/groups")
	public ResponseEntity<List<GroupModel>> getGroupsByDegree(@PathVariable("id") Long id){
		logger.info("GETTING GROUPS BY DEGREE.");
		return new ResponseEntity<List<GroupModel>>(groupService.getByDegree(id),HttpStatus.OK);
	}
	
	@GetMapping("/{id}/courses")
	public ResponseEntity<List<CourseModel>> getCoursesByDegree(@PathVariable("id") Long id){
		logger.info("GETTING COURSES BY DEGREE.");
		return new ResponseEntity<List<CourseModel>>(courseService.getByDegree(id), HttpStatus.OK);
	}
	@PostMapping(path = "/{id}/groups", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void addNewGroup(@RequestBody GroupModel model, @PathVariable("id") Long id) {
		logger.info("ADDING NEW GROUP.");
		groupService.save(model,id);
	}
	@PostMapping(consumes ="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody DegreeModel model) {
		logger.info("ADDING NEW DEGREE.");
		degreeService.save(model);
	}
	
	@PutMapping(path = "/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void edit(@RequestBody DegreeModel model, Long id) {
		logger.info("EDITING DEGREE BY ID.");
		degreeService.edit(model, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		logger.info("DELETING DEGREE BY ID.");
		degreeService.delete(id);
	}
}
