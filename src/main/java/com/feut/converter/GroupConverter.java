package com.feut.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.GroupEntity;
import com.feut.model.GroupModel;

@Component
public class GroupConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GroupConverter() {
		
	}
	
	public GroupModel toModel(GroupEntity entity) {
		return modelMapper.map(entity, GroupModel.class);
	}
	
	public GroupEntity toEntity(GroupModel model) {
		return modelMapper.map(model, GroupEntity.class);
	}
}
