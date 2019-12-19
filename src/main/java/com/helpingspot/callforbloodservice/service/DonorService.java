package com.helpingspot.callforbloodservice.service;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.helpingspot.callforbloodservice.dao.DonorRepository;
import com.helpingspot.callforbloodservice.model.Donor;
import com.helpingspot.callforbloodservice.model.DonorRequest;
import com.helpingspot.callforbloodservice.model.DonorResponse;

@Component
public class DonorService {

	@Autowired
	DonorRepository donorRepository;

	public List<Donor> retrieveAllDonors() {
		return donorRepository.findAll();
	}

	public List<DonorResponse> retrieveAllDonorsByDetails(DonorRequest donorRequest) {
		return donorRepository.findUserByStatusAndName(donorRequest.getBloodGroup(),
				donorRequest.getCountry(), donorRequest.getState(), donorRequest.getDistrict(), donorRequest.getCity());
	}

	public Integer save(Donor donor) {
		try {
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
