package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.DegreeEntity;
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
		TypedQuery<StudentEntity> query = em.createNamedQuery("Student.getById", StudentEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public List<StudentEntity> getByGroup(Long id){
		TypedQuery<StudentEntity> query = em.createNamedQuery("Student.getByGroup", StudentEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public List<StudentEntity> getByDegree(DegreeEntity degree){
		TypedQuery<StudentEntity> query = em.createQuery("Select s from StudentEntity s JOIN GroupEntity g on g.id = s.group "
				+ "JOIN DegreeEntity d on d.id = g.degree where d.id = ?1 and s.active =?2", StudentEntity.class);
		query.setParameter(1, degree.getId());
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public List<StudentEntity> getAll(){
		TypedQuery<StudentEntity> query = em.createQuery("Select s from StudentEntity s where s.role.id = 2 and s.active = ?1", StudentEntity.class);
		query.setParameter(1, true);
		return query.getResultList();
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