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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.GradeModel;
import com.feut.model.GroupModel;
import com.feut.service.GradeService;
import com.feut.service.GroupService;

@RestController
@RequestMapping(path="/group", produces="application/json")
@CrossOrigin(origins = {"https://myfeut.firebaseapp.com", "http://localhost:4200"})
public class GroupResource {
	
	private Logger logger = LogManager.getLogger(GroupResource.class);
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GradeService gradeService;
	
	public GroupResource() {
		
	}
	
	@GetMapping
	public ResponseEntity<List<GroupModel>> getAll(){
		logger.info("GETTING ALL GROUPS");
		return new ResponseEntity<List<GroupModel>>(groupService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GroupModel> getById(@PathVariable("id") Long id){
		logger.info("GETTING GROUP BY ID");
		return new ResponseEntity<GroupModel>(groupService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/courses/{courseId}/grades")
	public ResponseEntity<List<GradeModel>> getGradesByGroupAndCourse(@PathVariable("id") Long groupId, @PathVariable("courseId") Long courseId){
		logger.info("GETTING GRADE BY GROUP AND COURSE");
		return new ResponseEntity<List<GradeModel>>(gradeService.getByGroupAndCourse(groupId, courseId), HttpStatus.OK);
	}
	
	
	@PutMapping(path = "/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void edit(@RequestBody GroupModel model, @PathVariable("id") Long id) {
		logger.info("ADDING NEW GROUP");
		groupService.edit(model,id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		logger.info("DELETING GROUP");
		groupService.delete(id);
	}
}
