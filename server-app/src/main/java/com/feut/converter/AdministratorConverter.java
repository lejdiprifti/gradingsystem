package com.feut.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.AdministratorEntity;
import com.feut.model.AdministratorModel;

@Component
public class AdministratorConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AdministratorConverter() {
		
	}
	
	public AdministratorEntity toEntity(AdministratorModel model) {
		return modelMapper.map(model, AdministratorEntity.class);
	}
	
	public AdministratorModel toModel(AdministratorEntity entity) {
		return modelMapper.map(entity, AdministratorModel.class);
	}
	
}
