package com.feut.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feut.entity.LecturesEntity;
import com.feut.model.LecturesModel;

@Component
public class LecturesConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CourseConverter courseConverter;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
	@Autowired
	private GroupConverter groupConverter;
	
	public LecturesConverter() {

	}

	public LecturesModel toModel(LecturesEntity entity) {
		/*
		 * LecturesModel model = new LecturesModel(); model.setId(entity.getId());
		 * model.setCourseId(entity.getCourse().getId());
		 * model.setGroupId(entity.getGroup().getId());
		 * model.setTeacherId(entity.getTeacher().getId()); return model;
		 */
		
		return modelMapper.map(entity, LecturesModel.class);
	}

	public LecturesEntity toEntity(LecturesModel model) {
		return modelMapper.map(model, LecturesEntity.class);
	}
	
	public List<LecturesModel> toModel(List<LecturesEntity> lecturesList){
		List<LecturesModel> modelList = new ArrayList<LecturesModel>();
		for (LecturesEntity entity: lecturesList) {
			modelList.add(toModel(entity));
		}
		return modelList;
	}
}
