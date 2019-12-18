package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.DepartmentConverter;
import com.feut.entity.DepartmentEntity;
import com.feut.model.DepartmentModel;
import com.feut.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository depRepository;
	
	@Autowired
	private DepartmentConverter depConverter;
	
	
	public DepartmentService() {
		
	}
	
	public List<DepartmentModel> getAll()	{
		return depConverter.toModel(depRepository.getAll());
	}
	
	public DepartmentModel getById(Long id) {
		try {
			return depConverter.toModel(depRepository.getById(id));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
		}
	}
	
	public void save(DepartmentModel model) {
		DepartmentEntity entity = new DepartmentEntity();
		entity.setName(model.getName());
		entity.setDescription(model.getDescription());
		entity.setActive(true);
		depRepository.save(entity);
	}
	
	public void edit(DepartmentModel model, Long id) {
		try {
			DepartmentEntity entity = depRepository.getById(id);
			entity.setDescription(model.getDescription());
			entity.setName(model.getName());
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
		}
	}
	
	public void delete(Long id) {
		try {
			DepartmentEntity entity = depRepository.getById(id);
			entity.setActive(false);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
		}
	}
}
