package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.DegreeEntity;
import com.feut.entity.GroupEntity;

@Repository
public class DegreeRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public DegreeRepository() {
		
	}
	
	public List<DegreeEntity> getAll() {
		TypedQuery<DegreeEntity> query = em.createQuery("Select d from DegreeEntity d where d.active = ?1", DegreeEntity.class);
		query.setParameter(1, true);
		return query.getResultList();
	}
	
	public DegreeEntity getById(Long id) {
		TypedQuery<DegreeEntity> query = em.createQuery("Select d from DegreeEntity d where d.id = ?1 and d.active = ?2", DegreeEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public Long countGroupsOfDegree(Long id) {
		Query query = em.createQuery("Select Count(g.id) from GroupEntity g Join DegreeEntity d on d.id = g.degree where "
				+ "d.id = ?1 and d.active = ?2 and g.active = ?2", Long.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return (Long) query.getSingleResult();
	}
	
	public List<DegreeEntity> getByTeacher(Long id){
		TypedQuery<DegreeEntity> query = em.createQuery("Select Distinct d from DegreeEntity d Join GroupEntity g on g.degree = d.id "
				+ "Join LecturesEntity l on g.id = l.group"
				+ " where l.teacher.id = ?1 and l.active = ?2", DegreeEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	@Transactional
	public void save(DegreeEntity entity) {
		em.persist(entity);
	}
	
	@Transactional
	public void edit(DegreeEntity entity) {
		em.merge(entity);
	}
}
