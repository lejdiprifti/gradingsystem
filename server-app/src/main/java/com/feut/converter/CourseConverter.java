package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

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
		entity.getDegree().setCourseList(null);
		entity.getDepartment().setCourseList(null);
		return modelMapper.map(entity, CourseModel.class);
	}
	
	public List<CourseModel> toModel(List<CourseEntity> entityList){
		List<CourseModel> modelList = new ArrayList<CourseModel>();
		for (CourseEntity course: entityList) {
			toModel(course);
		}
		return modelList;
	}
}
