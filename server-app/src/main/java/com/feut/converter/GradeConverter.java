package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.GradeEntity;
import com.feut.model.GradeModel;

@Component
public class GradeConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GradeConverter() {
		
	}
	
	public GradeModel toModel(GradeEntity entity) {
		return modelMapper.map(entity, GradeModel.class);
	}
	
	public GradeEntity toEntity(GradeModel model) {
		return modelMapper.map(model, GradeEntity.class);
	}
	
	public List<GradeModel> toModel (List<GradeEntity> entityList) {
		List<GradeModel> modelList = new ArrayList<GradeModel>();
		for (GradeEntity entity: entityList) {
			modelList.add(toModel(entity));
		}
		return modelList;
	}
}
