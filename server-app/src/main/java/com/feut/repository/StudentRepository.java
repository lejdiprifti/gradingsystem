package com.feut.repository;

import java.util.List;

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
	
	public List<StudentEntity> getByName(String name) {
		TypedQuery<StudentEntity> query = em.createQuery("Select s.id, s.firstName, s.fatherName, s.lastName"
				+ "from StudentEntity s where s.firstName LIKE ?1"
				+ "and s.active = ?2", StudentEntity.class);
		query.setParameter(1, "%"+name+"%");
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public StudentEntity getByUsername(String username) {
		TypedQuery<StudentEntity> query = em.createNamedQuery("Student.getByUsername",StudentEntity.class);
		query.setParameter(1, username);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public StudentEntity getById(Long id) {
		TypedQuery<StudentEntity> query = em.createQuery("Student.getById", StudentEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	@Transactional
	public void save(StudentEntity student) {
		em.persist(student);
	}
	
	@Transactional
	public void edit(StudentEntity student) {
		em.merge(student);
	}
}
