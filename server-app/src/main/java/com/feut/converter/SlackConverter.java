package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.SlackEntity;
import com.feut.model.SlackModel;

@Component
public class SlackConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SlackConverter() {
		
	}
	
	public SlackModel toModel(SlackEntity entity) {
		return modelMapper.map(entity, SlackModel.class);
	}
	
	public SlackEntity toEntity(SlackModel model) {
		return modelMapper.map(model, SlackEntity.class);
	}
	
	public List<SlackModel> toModel(List<SlackEntity> entityList){
		List<SlackModel> list = new ArrayList<SlackModel>();
		for (SlackEntity entity: entityList) {
			list.add(toModel(entity));
		}
		return list;
	}
}
