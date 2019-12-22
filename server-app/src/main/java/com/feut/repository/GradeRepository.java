package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.GradeEntity;

@Repository
public class GradeRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public GradeRepository() {
		
	}
	
	public List<GradeEntity> getGradesByGroupAndCourse(Long groupId, Long courseId) {
		TypedQuery<GradeEntity> query = em.createQuery("Select gd from GradeEntity gd "
				+ "Join StudentEntity s on s.id = gd.student "
				+ "Join GroupEntity g on g.id = s.group "
				+ "Join CourseEntity c on c.id = gd.course "
				+ "where g.id = ?1 and gd.course.id = ?2 and gd.active = ?3", GradeEntity.class);
		query.setParameter(1, groupId);
		query.setParameter(2, courseId);
		query.setParameter(3, true);
		return query.getResultList();
	}
	
	public GradeEntity getById(Long gradeId) {
		TypedQuery<GradeEntity> query = em.createQuery("Select gd from GradeEntity gd where gd.id = ?1 and gd.active =?2", GradeEntity.class);
		query.setParameter(1, gradeId);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	@Transactional
	public void save(GradeEntity entity) {
		em.persist(entity);
	}
	
	@Transactional
	public void edit(GradeEntity entity) {
		em.merge(entity);
	}
	
}
