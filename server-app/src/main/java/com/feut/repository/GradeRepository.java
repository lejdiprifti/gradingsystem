package com.feut.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class GradeRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public GradeRepository() {
		
	}
	
}
