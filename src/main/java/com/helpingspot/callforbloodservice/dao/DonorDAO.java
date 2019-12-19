package com.helpingspot.callforbloodservice.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.helpingspot.callforbloodservice.model.Donor;

@Repository
@Transactional
public class DonorDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public long insert (Donor donor) {
		
		entityManager.persist(donor);
		return donor.getId();
	}
	
}
