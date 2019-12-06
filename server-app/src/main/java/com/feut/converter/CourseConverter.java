package com.feut.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.CourseEntity;
import com.feut.model.CourseModel;

@Component
public class CourseConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CourseConverter() {
		
	}
	
	public CourseEntity toEntity(CourseModel model) {
		return modelMapper.map(model, CourseEntity.class);
	}
	
	public CourseModel toModel(CourseEntity entity) {
		return modelMapper.map(entity, CourseModel.class);
	}
}
