package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.DegreeConverter;
import com.feut.converter.GroupConverter;
import com.feut.entity.GroupEntity;
import com.feut.model.GroupModel;
import com.feut.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private GroupConverter groupConverter;

	@Autowired
	private DegreeConverter degreeConverter;

	public GroupService() {

	}

	public List<GroupModel> getAll() {
		return groupConverter.toModel(groupRepository.getAll());
	}

	public GroupModel getById(Long id) {
		try {
			return groupConverter.toModel(groupRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
		}
	}

	public List<GroupModel> getByDegree(Long id) {
		try {
			return groupConverter.toModel(groupRepository.getByDegree(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Degree not found.");
		}
	}

	public void save(GroupModel model) {
		GroupEntity entity = new GroupEntity();
		entity.setNumber(model.getNumber());
		entity.setDegree(degreeConverter.toEntity(model.getDegree()));
		entity.setActive(true);
		groupRepository.save(entity);
	}

	public void edit(GroupModel model, Long id) {
		try {
			GroupEntity entity = groupRepository.getById(id);
			entity.setNumber(model.getNumber());
			entity.setDegree(degreeConverter.toEntity(model.getDegree()));
			groupRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
		}
	}

	public void delete(Long id) {
		try {
			GroupEntity entity = groupRepository.getById(id);
			entity.setActive(false);
			groupRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
		}
	}
}