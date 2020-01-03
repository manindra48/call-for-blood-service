package com.helpingspot.callforbloodservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.helpingspot.callforbloodservice.dao.DonorRepository;
import com.helpingspot.callforbloodservice.model.Donor;
import com.helpingspot.callforbloodservice.model.DonorRequest;
import com.helpingspot.callforbloodservice.model.DonorResponse;

@Component
public class DonorService {

	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<Donor> retrieveAllDonors() {
		return donorRepository.findAll();
	}

	public List<DonorResponse> retrieveAllDonorsByDetails(DonorRequest donorRequest) {
		return donorRepository.findUserByStatusAndName(donorRequest.getBloodGroup(),
				donorRequest.getCountry(), donorRequest.getState(), donorRequest.getDistrict(), donorRequest.getCity());
	}
	
	public Donor findByUserName(String userName) {
		return donorRepository.findByUserName(userName);
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
			updateDonor.setAvailability((donor.getAvailability()));
			updateDonor.setPhoneNumber(donor.getPhoneNumber());
			
			donorRepository.save(donor);
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

}
