package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

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
		entity.getDepartment().setTeacherList(null);
		entity.getDepartment().setCourseList(null);
		return modelMapper.map(entity, TeacherModel.class);
	}
	
	public TeacherEntity toEntity(TeacherModel model) {
		return modelMapper.map(model, TeacherEntity.class);
	}
	
	public List<TeacherModel> toModel(List<TeacherEntity> entityList){
		List<TeacherModel> modelList = new ArrayList<TeacherModel>();
		for (TeacherEntity entity: entityList) {
			modelList.add(toModel(entity));
		}
		return modelList;
	}
}
