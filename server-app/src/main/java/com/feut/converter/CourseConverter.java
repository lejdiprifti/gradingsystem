package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.CourseEntity;
import com.feut.entity.GroupEntity;
import com.feut.model.CourseModel;

@Component
public class CourseConverter {
	
	@Autowired
	private ModelMapper modelMapper;
		
	@Autowired
	private LecturesConverter lecturesConverter;
	
	public CourseConverter() {
		
	}
	
	public CourseEntity toEntity(CourseModel model) {
		return modelMapper.map(model, CourseEntity.class);
	}
	
	public CourseModel toModel(CourseEntity entity) {
		/*
		 * CourseModel model = new CourseModel();
		 * model.setDegreeId(entity.getDegree().getId());
		 * model.setDepartmentId(entity.getDepartment().getId());
		 * model.setId(entity.getId()); model.setName(entity.getName());
		 * model.setSyllabus(entity.getSyllabus());
		 * model.setLecturesList(lecturesConverter.toModel(entity.getLectureList()));
		 * return model;
		 */
		
		return modelMapper.map(entity, CourseModel.class);
	}
	
	public List<CourseModel> toModel(List<CourseEntity> entityList){
		List<CourseModel> modelList = new ArrayList<CourseModel>();
		for (CourseEntity course: entityList) {
			modelList.add(toModel(course));
		}
		return modelList;
	}
}
