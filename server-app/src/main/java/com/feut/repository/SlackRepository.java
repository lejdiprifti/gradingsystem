package com.feut.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.SlackEntity;

@Repository
public class SlackRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public SlackRepository() {
		
	}
	
	public SlackEntity getAllById(Long id) {
		TypedQuery<SlackEntity> query= em.createQuery("Select s from SlackEntity s where s.id = ?1", SlackEntity.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
	
	public SlackEntity getById(Long id) {
		TypedQuery<SlackEntity> query= em.createQuery("Select s from SlackEntity s where s.id = ?1 and s.active =?2", SlackEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public List<SlackEntity> getByStudent(Long studentId){
		TypedQuery<SlackEntity> query = em.createQuery("Select sl from SlackEntity sl JOIN StudentEntity s on sl.student = s.id "
				+ "where s.id = ?1 and s.active = ?2", SlackEntity.class);
		query.setParameter(1, studentId);
		query.setParameter(2, true);
		return query.getResultList();
	}
	
	public SlackEntity getByChannelName(String channelName) throws NoResultException{
		TypedQuery<SlackEntity> query = em.createQuery("Select s from SlackEntity s where s.channelName = ?1 and s.active =?2", SlackEntity.class);
		query.setParameter(1, channelName);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	@Transactional
	public void save(SlackEntity entity) {
		em.persist(entity);
	}
	
	@Transactional
	public void edit(SlackEntity entity) {
		em.merge(entity);
	}
}
