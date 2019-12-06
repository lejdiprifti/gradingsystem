package com.feut.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.DepartmentEntity;
import com.feut.model.DepartmentModel;

@Component
public class DepartmentConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public DepartmentConverter() {
		
	}
	
	public DepartmentEntity toEntity(DepartmentModel model) {
		return modelMapper.map(model, DepartmentEntity.class);
	}
	
	public DepartmentModel toModel(DepartmentEntity entity) {
		return modelMapper.map(entity, DepartmentModel.class);
	}
}
