package com.feut.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.feut.entity.TeacherEntity;

@Repository
public class TeacherRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public TeacherRepository() {
		
	}
	
	public TeacherEntity getByUsername(String username) {
		TypedQuery<TeacherEntity> query = em.createNamedQuery("Teacher.getByUsername", TeacherEntity.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}
}
