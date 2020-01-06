package com.helpingspot.callforbloodservice.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

	public List<DonorResponse> retrieveAllDonorsByDetails(DonorRequest donorRequest) {
		return donorRepository.findUserByStatusAndName(donorRequest.getBloodGroup(), donorRequest.getCountry(),
				donorRequest.getState(), donorRequest.getDistrict(), donorRequest.getCity());
	}

	@Transactional
	public Donor findByUserName(String userName) {
		// return donorRepository.findByUserName(userName);
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Donor> theQuery = currentSession.createQuery("from Donor where userName=:uName", Donor.class);
		theQuery.setParameter("uName", userName);
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
			// updateDonor.setAvailability((donor.getAvailability()));
			updateDonor.setPhoneNumber(donor.getPhoneNumber());
//			updateDonor.setEmail("smaple@gmail.com");
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//			updateDonor.setBirthDate(formatter.parse("2019-12-19 10:45:11"));
//			updateDonor.setBloodGroup("A");
//			updateDonor.setCountry("India");
//			updateDonor.setState("Hyd");
//			updateDonor.setDistrict("Hyd");
//			updateDonor.setCity("hyd");
			updateDonor.setAvailability(true);
			updateDonor.setPhoneNumber(donor.getPhoneNumber());
			updateDonor.setRoles(Arrays.asList(roleRepository.findRoleByName("EMPLOYEE")));

			donorRepository.save(updateDonor);
		} catch (Exception e) {
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
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Donor user = donorRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
