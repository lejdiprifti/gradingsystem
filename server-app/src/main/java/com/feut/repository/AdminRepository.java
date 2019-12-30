package com.feut.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.AdministratorEntity;

@Repository
public class AdminRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public AdminRepository() {
		
	}
	
	public AdministratorEntity getById(Long id) {
		TypedQuery<AdministratorEntity> query = em.createQuery("Select a from AdministratorEntity a where a.id = ?1 and a.active =?2", AdministratorEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	@Transactional
	public void edit(AdministratorEntity admin) {
		em.merge(admin);
	}
}
