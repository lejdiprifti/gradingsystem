package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.CourseEntity;
import com.feut.entity.DepartmentEntity;
import com.feut.entity.LecturesEntity;
import com.feut.model.DepartmentModel;

@Component
public class DepartmentConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CourseConverter courseConverter;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
	public DepartmentConverter() {
		
	}
	
	public DepartmentEntity toEntity(DepartmentModel model) {
		return modelMapper.map(model, DepartmentEntity.class);
	}
	
	public DepartmentModel toModel(DepartmentEntity entity) {
		/*
		 * DepartmentModel model = new DepartmentModel(); model.setId(entity.getId());
		 * model.setName(entity.getName());
		 * model.setDescription(entity.getDescription());
		 * model.setTeacherList(teacherConverter.toModel(entity.getTeacherList()));
		 * model.setCourseList(courseConverter.toModel(entity.getCourseList())); return
		 * model;
		 */
		return modelMapper.map(entity, DepartmentModel.class);
	}
	
	public List<DepartmentModel> toModel(List<DepartmentEntity> entityList){
		List<DepartmentModel> modelList = new ArrayList<DepartmentModel>();
		for (DepartmentEntity entity: entityList) {
			modelList.add(toModel(entity));
		}
		return modelList;
	}
}
