package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	
	public boolean checkIfExists(String name) {
		try {
			TypedQuery<DepartmentEntity> query = em.createNamedQuery("Department.checkIfExists", DepartmentEntity.class);
			query.setParameter(2, true);
			query.setParameter(1, name);
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public boolean checkIfNameExists(String name, Long id) {
		try {
			TypedQuery<DepartmentEntity> query = em.createNamedQuery("Department.checkIfNameExists", DepartmentEntity.class);
			query.setParameter(1, name);
			query.setParameter(2, id);
			query.setParameter(3, true);
			query.getResultList();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}
