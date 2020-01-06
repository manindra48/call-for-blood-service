package com.helpingspot.callforbloodservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.helpingspot.callforbloodservice.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("SELECT new com.helpingspot.callforbloodservice.model.Role(d.id,d.name) FROM Role d WHERE d.name = ?1")
	Role findRoleByName(String userName);

}
