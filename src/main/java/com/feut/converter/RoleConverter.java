package com.feut.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.Role;
import com.feut.model.RoleModel;

@Component
public class RoleConverter {

	@Autowired
	private ModelMapper modelMapper;

	public RoleConverter() {

	}

	public Role toEntity(RoleModel model) {
		return modelMapper.map(model, Role.class);
	}

	public RoleModel toModel(Role entity) {
		return modelMapper.map(entity, RoleModel.class);
	}
}
