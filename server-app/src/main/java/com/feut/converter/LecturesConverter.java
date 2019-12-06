package com.feut.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.LecturesEntity;
import com.feut.model.LecturesModel;

@Component
public class LecturesConverter {

	@Autowired
	private ModelMapper modelMapper;

	public LecturesConverter() {

	}

	public LecturesModel toModel(LecturesEntity entity) {
		return modelMapper.map(entity, LecturesModel.class);
	}

	public LecturesEntity toEntity(LecturesModel model) {
		return modelMapper.map(model, LecturesEntity.class);
	}
}
