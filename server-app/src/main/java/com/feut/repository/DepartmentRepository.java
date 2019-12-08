package com.feut.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.feut.entity.DepartmentEntity;

@Repository
public class DepartmentRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public DepartmentRepository() {
		
	}
	
	public void save(DepartmentEntity deparment) {
		
	}
}
