package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

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
		entity.getDegree().setGroupList(new ArrayList<GroupEntity>());;
		return modelMapper.map(entity, GroupModel.class);
	}
	
	public GroupEntity toEntity(GroupModel model) {
		return modelMapper.map(model, GroupEntity.class);
	}
	
	public List<GroupModel> toModel(List<GroupEntity> entityList){
		List<GroupModel> modelList = new ArrayList<GroupModel>();
		for(GroupEntity entity: entityList) {
			modelList.add(toModel(entity));
		}
		return modelList;
	}
}
