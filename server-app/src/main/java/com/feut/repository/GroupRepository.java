package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List<GroupEntity> getByTeacherAndDegree(Long teacherId, Long degreeId){
		TypedQuery<GroupEntity> query = em.createQuery("Select Distinct g from GroupEntity g "
				+ "Join LecturesEntity l on l.group = g.id "
				+ "Join DegreeEntity d on d.id = g.degree "
				+ "where l.teacher.id = ?1 and d.id = ?2 and g.active =?3 and d.active =?3 and l.active =?3", GroupEntity.class);
		query.setParameter(1, teacherId);
		query.setParameter(2, degreeId);
		query.setParameter(3, true);
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
	
	public boolean checkIfExists(Long number, Long degreeId) {
		try {
			TypedQuery<GroupEntity> query = em.createNamedQuery("Group.CheckIfExists", GroupEntity.class);
			query.setParameter(1, number);
			query.setParameter(2, degreeId);
			query.setParameter(3, true);
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public boolean checkIfNumberExists(Long number, Long degreeId, Long groupId) {
		try {
			TypedQuery<GroupEntity> query = em.createNamedQuery("Select g from GroupEntity g JOIN DegreeEntity d on d.id = g.degree"
					+ " where g.number = ?1 and d.id=?2 and g.active =?3 and g.id != ?4", GroupEntity.class);
			query.setParameter(1, number);
			query.setParameter(2, degreeId);
			query.setParameter(3, true);
			query.setParameter(4, groupId);
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}
