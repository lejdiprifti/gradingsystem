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

import com.feut.model.SlackModel;
import com.feut.service.SlackService;

@RestController
@RequestMapping(path="/slack", produces="application/json")
@CrossOrigin(origins = {"https://myfeut.firebaseapp.com", "http://localhost:4200"})
public class SlackResource {
	
	private Logger logger = LogManager.getLogger(SlackResource.class);
	
	@Autowired
	private SlackService slackService;
	
	public SlackResource() {
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<SlackModel>> getByStudent(@PathVariable("id") Long studentId){
		logger.info("GETTING SLACK BY STUDENT.");
		return new ResponseEntity<List<SlackModel>>(slackService.getByStudent(studentId), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody SlackModel model){
		logger.info("ADDING NEW SLACK.");
	slackService.save(model);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		logger.info("DEACTIVATING SLACK CHANNEL.");
		slackService.delete(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activate(@PathVariable("id") Long id) {
		logger.info("ACTIVATING SLACK CHANNEL.");
		slackService.activate(id);
	}
}
