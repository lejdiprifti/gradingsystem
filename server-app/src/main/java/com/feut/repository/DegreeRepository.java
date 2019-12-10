package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.DegreeEntity;

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
	
	@Transactional
	public void save(DegreeEntity entity) {
		em.persist(entity);
	}
	
	@Transactional
	public void edit(DegreeEntity entity) {
		em.merge(entity);
	}
}
