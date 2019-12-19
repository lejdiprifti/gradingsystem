package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.CourseEntity;
import com.feut.entity.DegreeEntity;
import com.feut.entity.DepartmentEntity;

@Repository
public class CourseRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public CourseRepository() {
		
	}
	
	public List<CourseEntity> getAll() {
		TypedQuery<CourseEntity> query = em.createNamedQuery("Course.getAll", CourseEntity.class);
		query.setParameter(1, true);
		return query.getResultList();
	}
	
	public List<CourseEntity> getByDegree(Long id) {
		TypedQuery<CourseEntity> query = em.createNamedQuery("Course.getByDegree", CourseEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		List<CourseEntity> list = query.getResultList();
		for (CourseEntity entity: list) {
		System.out.println(entity.getName());
		}
		return list;
	}
	
	public List<CourseEntity> getByDepartment(DepartmentEntity department){
		TypedQuery<CourseEntity> query = em.createNamedQuery("Course.getByDepartment", CourseEntity.class);
		query.setParameter(1, department);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public CourseEntity getById(Long id) {
		TypedQuery<CourseEntity> query = em.createNamedQuery("Course.getById", CourseEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	@Transactional
	public void save(CourseEntity entity) {
		em.persist(entity);
	}
	
	@Transactional 
	public void edit(CourseEntity entity) {
		em.merge(entity);
	}

}
