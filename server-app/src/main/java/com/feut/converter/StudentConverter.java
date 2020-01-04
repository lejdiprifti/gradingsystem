package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.StudentEntity;
import com.feut.model.StudentModel;

@Component
public class StudentConverter  {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GroupConverter groupConverter;
		
	@Autowired
	private RoleConverter roleConverter;
	
	public StudentConverter() {
		
	}
	
	public StudentEntity toEntity(StudentModel model) {
		return modelMapper.map(model, StudentEntity.class);
	}
	
	public StudentModel toModel(StudentEntity entity) {
		
		/*
		 * StudentModel model = new StudentModel();
		 * model.setBirthdate(entity.getBirthdate());
		 * model.setFirstName(entity.getFirstName()); model.setEmail(entity.getEmail());
		 * model.setId(entity.getId()); model.setFatherName(entity.getFatherName());
		 * model.setLastName(entity.getLastName());
		 * model.setPassword(entity.getPassword());
		 * model.setPersonalNumber(entity.getPersonalNumber());
		 * model.setRole(roleConverter.toModel(entity.getRole()));
		 * model.setUsername(entity.getUsername());
		 * 
		 * return model;
		 */
		
		return modelMapper.map(entity, StudentModel.class);
	}
	
	public List<StudentModel> toModel(List<StudentEntity> entityList){
		List<StudentModel> modelList = new ArrayList<StudentModel>();
		for (StudentEntity entity: entityList) {
			modelList.add(toModel(entity));
		}
		return modelList;
	}
	
}
