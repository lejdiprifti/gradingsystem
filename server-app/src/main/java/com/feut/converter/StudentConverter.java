package com.feut.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.StudentEntity;
import com.feut.model.StudentModel;

@Component
public class StudentConverter  {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public StudentConverter() {
		
	}
	
	public StudentEntity toEntity(StudentModel model) {
		return modelMapper.map(model, StudentEntity.class);
	}
	
	public StudentModel toModel(StudentEntity entity) {
		return modelMapper.map(entity, StudentModel.class);
	}
	
}
