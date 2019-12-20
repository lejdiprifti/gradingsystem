package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.LecturesEntity;

@Repository
public class LecturesRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public LecturesRepository() {
		
	}
	
	public List<LecturesEntity> getByGroup(Long groupId) {
		TypedQuery<LecturesEntity> query = em.createNamedQuery("Lectures.getByGroup", LecturesEntity.class);
		query.setParameter(1, groupId);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public List<LecturesEntity> getByTeacher(Long teacherId){
		TypedQuery<LecturesEntity> query = em.createNamedQuery("Lectures.getByTeacher", LecturesEntity.class);
		query.setParameter(1, teacherId);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public LecturesEntity getById(Long id){
		TypedQuery<LecturesEntity> query = em.createNamedQuery("Lectures.getById", LecturesEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public LecturesEntity getByGroupAndCourse(Long groupId, Long courseId) throws NoResultException{
		TypedQuery<LecturesEntity> query = em.createQuery("Select l from LecturesEntity l Join GroupEntity g on g.id = l.group"
				+ " Join CourseEntity c on c.id = l.course "
				+ " where g.id = ?1 and c.id = ?2 and l.active = ?3", LecturesEntity.class);
		query.setParameter(1, groupId);
		query.setParameter(2, courseId);
		query.setParameter(3, true);
		return query.getSingleResult();
	}
	@Transactional
	public void save(LecturesEntity entity) {
		em.persist(entity);
	}
	
	@Transactional
	public void edit(LecturesEntity entity) {
		em.merge(entity);
	}
}
