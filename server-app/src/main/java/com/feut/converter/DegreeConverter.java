package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.DegreeEntity;
import com.feut.entity.GroupEntity;
import com.feut.model.DegreeModel;

@Component
public class DegreeConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public DegreeConverter() {
		
	}
	
	public DegreeEntity toEntity(DegreeModel model) {
		return modelMapper.map(model, DegreeEntity.class);
	}
	
	public DegreeModel toModel(DegreeEntity entity) {
		for (GroupEntity group: entity.getGroupList()) {
			group.setDegree(null);
		}
		return modelMapper.map(entity, DegreeModel.class);
	}
	
	public List<DegreeModel> toModel(List<DegreeEntity> entityList){
		List<DegreeModel> modelList = new ArrayList<DegreeModel>();
		for (DegreeEntity entity: entityList) {
			modelList.add(toModel(entity));
		}
		
		return modelList;
	}
}
