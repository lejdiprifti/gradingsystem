package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.DegreeEntity;
import com.feut.entity.GroupEntity;

@Repository
public class GroupRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public GroupRepository() {
		
	}
	
	public List<GroupEntity> getAll(){
		TypedQuery<GroupEntity> query = em.createQuery("Select g from GroupEntity g where g.active = ?1", GroupEntity.class);
		query.setParameter(1, true);
		return query.getResultList();
	}
	
	public GroupEntity getById(Long id) {
		TypedQuery<GroupEntity> query = em.createQuery("Select g from GroupEntity g where g.id = ?1 and g.active = ?2", GroupEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public List<GroupEntity> getByDegree(Long id){
		TypedQuery<GroupEntity> query = em.createQuery("Select g from GroupEntity g where g.degree.id = ?1 and g.active = ?2", GroupEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	@Transactional
	public void save(GroupEntity entity) {
		em.persist(entity);
	}
	
	@Transactional
	public void edit(GroupEntity entity) {
		em.merge(entity);
	}
}
