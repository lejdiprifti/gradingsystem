package com.feut.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.feut.entity.UserEntity;

@Repository
public class UserRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public UserRepository() {
		
	}
	
	public UserEntity getByUsername(String username) {
		TypedQuery<UserEntity> query = em.createNamedQuery("Student.getByUsername",UserEntity.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}
}
