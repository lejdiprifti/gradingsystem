package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.LecturesEntity;
import com.feut.entity.TeacherEntity;
import com.feut.model.TeacherModel;

@Component
public class TeacherConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DepartmentConverter departmentConverter;
	
	@Autowired
	private LecturesConverter lecturesConverter;
	public TeacherConverter() {
		
	}
	
	public TeacherModel toModel(TeacherEntity entity) {
		/*
		 * TeacherModel model = new TeacherModel(); model.setId(entity.getId());
		 * model.setBirthdate(entity.getBirthdate());
		 * model.setDepartmentId(entity.getDepartment().getId());
		 * model.setEmail(entity.getEmail());
		 * model.setFatherName(entity.getFatherName());
		 * model.setFirstName(entity.getFirstName());
		 * model.setLastName(entity.getLastName());
		 * model.setPassword(entity.getPassword());
		 * model.setPersonalNumber(entity.getPassword());
		 * model.setUsername(entity.getUsername());
		 * model.setLecturesList(lecturesConverter.toModel(entity.getLectures()));
		 * return model;
		 */
		
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

