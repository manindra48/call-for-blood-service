package com.helpingspot.callforbloodservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.helpingspot.callforbloodservice.model.Donor;
import com.helpingspot.callforbloodservice.model.DonorResponse;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {

	@Query("SELECT new com.helpingspot.callforbloodservice.model.DonorResponse(d.firstName, d.phoneNumber, d.bloodGroup) FROM Donor d WHERE d.bloodGroup = ?1 and d.country = ?2 and d.state = ?3 and d.district = ?4 and d.city = ?5")
	List<DonorResponse> findUserByStatusAndName(String bloodGroup, String country, String state, String district,
			String city);

	//@Query("SELECT new com.helpingspot.callforbloodservice.model.Donor(d.id,d.userName,d.firstName,d.lastName,d.birthDate,d.phoneNumber,d.password,d.email,d.bloodGroup,d.country,d.state,d.district,d.city,d.availability) FROM Donor d WHERE d.userName = ?1")
	Donor findByUserName(String userName);

}
