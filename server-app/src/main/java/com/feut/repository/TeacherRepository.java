package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.TeacherEntity;

@Repository
public class TeacherRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public TeacherRepository() {
		
	}
	
	public List<TeacherEntity> getAll() {
		TypedQuery<TeacherEntity> query = em.createQuery("Select t from TeacherEntity t where t.active =?1",TeacherEntity.class);
		query.setParameter(1, true);
		return query.getResultList();
	}
	
	public TeacherEntity getByUsername(String username) {
		TypedQuery<TeacherEntity> query = em.createNamedQuery("Teacher.getByUsername", TeacherEntity.class);
		query.setParameter(1, username);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public TeacherEntity getById(Long id) {
		TypedQuery<TeacherEntity> query = em.createNamedQuery("Teacher.getById", TeacherEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public List<TeacherEntity> getByDepartment(Long id){
		TypedQuery<TeacherEntity> query = em.createNamedQuery("Teacher.getByDepartment", TeacherEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	@Transactional
	public void save(TeacherEntity entity) {
		em.persist(entity);
	}
	
	@Transactional
	public void edit(TeacherEntity entity) {
		em.merge(entity);
	}
	
	
}
