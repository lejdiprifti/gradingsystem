package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
	
	public List<GradeEntity> getGradesByStudent(Long studentId){
		TypedQuery<GradeEntity> query = em.createQuery("Select gd from GradeEntity gd JOIN StudentEntity s on gd.student = s.id"
				+ " where s.id = ?1 and gd.active = ?2 and s.active = ?2", GradeEntity.class);
		query.setParameter(1, studentId);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public Double getAverageByStudent(Long studentId) {
		try {
		TypedQuery<Double> query = em.createQuery("Select AVG(gd.grade) from GradeEntity gd "
				+ "JOIN StudentEntity s on s.id = gd.student "
				+ "GROUP BY (s.id, gd.active, s.active) HAVING s.id = ?1 and gd.active = ?2 and s.active = ?2",Double.class); 
		query.setParameter(1, studentId);
		query.setParameter(2,  true);
		return query.getSingleResult();
		} catch (NoResultException e) {
			return 0.0;
		}
		}
	
	public List<GradeEntity> getGradesByCourse(Long courseId){
		TypedQuery<GradeEntity> query = em.createQuery("Select gd from GradeEntity gd JOIN CourseEntity c on c.id = gd.course "
				+ "where c.id = ?1 and gd.active = ?2 and c.active = ?2", GradeEntity.class);
		query.setParameter(1, courseId);
		query.setParameter(2, true);
		return query.getResultList();
	}
	@Transactional
	public void save(GradeEntity entity) {
		em.persist(entity);
	}
	
	@Transactional
	public void edit(GradeEntity entity) {
		em.merge(entity);
	}
	
	public Object getAverageByGroupCourseAndTeacher(Long groupId, Long courseId, Long teacherId) {
		try {
		Query query = em.createQuery("Select AVG(gd.grade),c.id,g.id  from GradeEntity gd "
				+ "JOIN StudentEntity s on s.id = gd.student "
				+ "JOIN GroupEntity g on g.id = s.group "
				+ "JOIN CourseEntity c on c.id = gd.course "
				+ "JOIN TeacherEntity t on t.id = gd.teacher "
				+ "GROUP BY (c.id, g.id, t.id, gd.active) HAVING t.id = ?1 and g.id=?2 and c.id = ?3 and gd.active = ?4 ORDER BY AVG(gd.grade)");
		query.setParameter(1, teacherId);
		query.setParameter(2, groupId);
		query.setParameter(3, courseId);
		query.setParameter(4, true);
		return query.getSingleResult();
		} catch (NoResultException e) {
			return 0;
		}
	}
	
}