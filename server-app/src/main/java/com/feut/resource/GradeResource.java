package com.feut.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = {"https://myfeut.firebaseapp.com", "http://localhost:4200"})
public class GradeResource {
	
	private Logger logger = LogManager.getLogger(GradeResource.class);
	
	@Autowired
	private GradeService gradeService;
	
	
	
	public GradeResource() {
		
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<GradeModel> getGradeById(@PathVariable("id") Long gradeId){
		logger.info("GETTING GRADE BY ID");
		return new ResponseEntity<GradeModel>(gradeService.getById(gradeId), HttpStatus.OK);
	}
	
	@PutMapping(path="/{id}",consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody GradeModel model, @PathVariable("id") Long id) {
		logger.info("EDITING GRADE");
		gradeService.edit(model, id);
	}
}

