package com.feut.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.TeacherEntity;
import com.feut.model.TeacherModel;

@Component
public class TeacherConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TeacherConverter() {
		
	}
	
	public TeacherModel toModel(TeacherEntity entity) {
		return modelMapper.map(entity, TeacherModel.class);
	}
	
	public TeacherEntity toEntity(TeacherModel model) {
		return modelMapper.map(model, TeacherEntity.class);
	}
}
