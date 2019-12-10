package com.feut.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.GroupModel;
import com.feut.service.GroupService;

@RestController
@RequestMapping(path="/group", consumes = "application/json", produces="application/json")
public class GroupResource {
	
	@Autowired
	private GroupService groupService;
	
	public GroupResource() {
		
	}
	
	@GetMapping
	public ResponseEntity<List<GroupModel>> getAll(){
		return new ResponseEntity<List<GroupModel>>(groupService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GroupModel> getById(@PathVariable("id") Long id){
		return new ResponseEntity<GroupModel>(groupService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody GroupModel model) {
		groupService.save(model);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void edit(@RequestBody GroupModel model, @PathVariable("id") Long id) {
		groupService.edit(model,id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		groupService.delete(id);
	}
}
