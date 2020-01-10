package com.helpingspot.callforbloodservice.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpingspot.callforbloodservice.dao.DonorRepository;
import com.helpingspot.callforbloodservice.dao.RoleRepository;
import com.helpingspot.callforbloodservice.model.Donor;
import com.helpingspot.callforbloodservice.model.DonorRequest;
import com.helpingspot.callforbloodservice.model.DonorResponse;
import com.helpingspot.callforbloodservice.model.Role;

@Component
public class DonorService implements UserDetailsService {

	private final org.slf4j.Logger log = LoggerFactory.getLogger(DonorService.class);

	@Autowired
	DonorRepository donorRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<Donor> retrieveAllDonors() {
		return donorRepository.findAll();
	}

	public Optional<Donor> retrieveDonorById(int id) {
		return donorRepository.findById(id);
	}

	public List<DonorResponse> retrieveAllDonorsByDetails(DonorRequest donorRequest) {
		log.info("Retrieve all donors matching {}", donorRequest);
		return donorRepository.findUserByStatusAndName(donorRequest.getBloodGroup(), donorRequest.getCountry(),
				donorRequest.getState(), donorRequest.getDistrict(), donorRequest.getCity());
	}

	@Transactional
	public Donor findByUserName(String donorName) {
		log.info("Retrieve donor details having donor name {}", donorName);
		// return donorRepository.findByUserName(userName);
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Donor> theQuery = currentSession.createQuery("from Donor where userName=:uName", Donor.class);
		theQuery.setParameter("uName", donorName);
		Donor theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}
		return theUser;

	}

	public Integer save(Donor donor) {
		try {
			log.info("Entering to store donor having details : {}", donor);
			Donor updateDonor = new Donor();
			// assign user details to the user object
			updateDonor.setId(donor.getId());
			updateDonor.setUserName(donor.getUserName());
			updateDonor.setPassword(passwordEncoder.encode(donor.getPassword()));
			updateDonor.setFirstName(donor.getFirstName());
			updateDonor.setLastName(donor.getLastName());
			updateDonor.setEmail(donor.getEmail());
			updateDonor.setBirthDate(donor.getBirthDate());
			updateDonor.setBloodGroup(donor.getBloodGroup());
			updateDonor.setCountry(donor.getCountry());
			updateDonor.setState(donor.getState());
			updateDonor.setDistrict((donor.getDistrict()));
			updateDonor.setCity(donor.getCity());
			updateDonor.setPhoneNumber(donor.getPhoneNumber());
			updateDonor.setAvailability(true);
			updateDonor.setPhoneNumber(donor.getPhoneNumber());
			updateDonor.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_EMPLOYEE")));
			donorRepository.save(updateDonor);
			log.info("Successfully saved donor details : {}", updateDonor);
		} catch (Exception e) {
			log.error("DonorService.save: Failed to save given donor details {} with exception {}", donor, e);
			return 0;
		}
		return donor.getId();
	}

	public int deleteDonorsBy(int id) {
		donorRepository.deleteById(id);
		return id;
	}

	public int deleteDonorsByEntity(Donor donor) {
		donorRepository.delete(donor);
		return donor.getId();
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String donorName) throws UsernameNotFoundException {
		log.info("loadUserByUsername: Entering to get donor by donor name : {}", donorName);
		Donor donor = donorRepository.findByUserName(donorName);
		if (donor == null) {
			log.error("No donor found for the given name : {}", donorName);
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		log.info("loadUserByUsername: Returning donor details : {}", donor);
		return new org.springframework.security.core.userdetails.User(donor.getUserName(), donor.getPassword(),
				mapRolesToAuthorities(donor.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
