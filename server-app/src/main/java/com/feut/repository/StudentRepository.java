package com.feut.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.StudentEntity;

@Repository
public class StudentRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public StudentRepository() {
		
	}
	
	public StudentEntity getByUsername(String username) {
		TypedQuery<StudentEntity> query = em.createNamedQuery("Student.getByUsername",StudentEntity.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}
	
	public StudentEntity getById(Long id) {
		TypedQuery<StudentEntity> query = em.createQuery("Student.getById", StudentEntity.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
	
	@Transactional
	public void save(StudentEntity student) {
		em.persist(student);
	}
}
