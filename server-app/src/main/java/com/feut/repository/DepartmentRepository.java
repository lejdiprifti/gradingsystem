package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.DepartmentEntity;

@Repository
public class DepartmentRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public DepartmentRepository() {
		
	}
	
	public List<DepartmentEntity> getAll() {
		TypedQuery<DepartmentEntity> query = em.createNamedQuery("Department.getAll", DepartmentEntity.class);
		query.setParameter(1, true);
		return query.getResultList();
		
	}
	
	public DepartmentEntity getById(Long id) {
		TypedQuery<DepartmentEntity> query = em.createNamedQuery("Department.getById", DepartmentEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	@Transactional
	public void save(DepartmentEntity department) {
		em.persist(department);
	}
	
	@Transactional
	public void edit(DepartmentEntity department) {
		em.merge(department);
	}
}
