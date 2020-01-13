package com.feut.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feut.entity.UserEntity;

@Repository
public class UserRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public UserRepository() {
		
	}
	
	public UserEntity getByUsername(String username) {
		TypedQuery<UserEntity> query = em.createNamedQuery("User.getByUsername",UserEntity.class);
		query.setParameter(1, username);
		query.setParameter(2, true);
		return query.getSingleResult();
	}
	
	public boolean checkIfExists(String personalNumber, String username) {
		try {
			Query query = em.createNativeQuery("Select s.personal_number, s.username from feut.user_view s where s.personal_number = :pn or s.username = :username");
			query.setParameter("pn", personalNumber);
			query.setParameter("username", username);
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		} catch (NonUniqueResultException e) {
			return false;
		}
	}
	
	@Transactional
	public void createView() {
		Query query1 = em.createNativeQuery("DROP VIEW IF EXISTS feut.user_view ");
		Query query = em.createNativeQuery("CREATE VIEW feut.user_view AS "
				+ "SELECT u.personal_number as personal_number, u.username as username From feut.user u "
				+ "WHERE u.active=true");
		query1.executeUpdate();
		query.executeUpdate();
}
}
