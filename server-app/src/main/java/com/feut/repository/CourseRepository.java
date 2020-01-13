package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.CourseEntity;
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
		return list;
	}
	
	public List<CourseEntity> getByDepartment(DepartmentEntity department){
		TypedQuery<CourseEntity> query = em.createNamedQuery("Course.getByDepartment", CourseEntity.class);
		query.setParameter(1, department);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public List<CourseEntity> getByStudent(Long studentId){
		TypedQuery<CourseEntity> query = em.createQuery("Select c from CourseEntity c JOIN DegreeEntity d on c.degree = d.id"
				+ " JOIN GroupEntity g on g.degree = d.id "
				+ "	JOIN StudentEntity s on s.group  = g.id"
				+ " where s.id = ?1 and s.active = ?2 and c.active =?2", CourseEntity.class);
		query.setParameter(1, studentId);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public List<CourseEntity> getByTeacherAndDegree(Long teacherId, Long degreeId){
		TypedQuery<CourseEntity> query = em.createQuery("Select Distinct c from CourseEntity c "
				+ "Join DegreeEntity d on c.degree = d.id "
				+ "Join LecturesEntity l on l.course = c.id "
				+ "where l.teacher.id = ?1 and d.id = ?2 and c.active =?3 and l.active = ?3 and d.active = ?3", CourseEntity.class);
		query.setParameter(1, teacherId);
		query.setParameter(2, degreeId);
		query.setParameter(3, true);
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
	
	public boolean checkIfExistsByDegree(String name, Long degreeId, Long depId) {
		try {
			TypedQuery<CourseEntity> query = em.createNamedQuery("Course.CheckIfExistsByDegree", CourseEntity.class);
			query.setParameter(1, name);
			query.setParameter(2, degreeId);
			query.setParameter(3, true);
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}
