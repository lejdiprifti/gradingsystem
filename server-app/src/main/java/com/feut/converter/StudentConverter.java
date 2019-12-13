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
	
	public StudentConverter() {
		
	}
	
	public StudentEntity toEntity(StudentModel model) {
		return modelMapper.map(model, StudentEntity.class);
	}
	
	public StudentModel toModel(StudentEntity entity) {
		entity.getGroup().setStudentList(null);
		entity.getGroup().getDegree().setGroupList(null);;
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
