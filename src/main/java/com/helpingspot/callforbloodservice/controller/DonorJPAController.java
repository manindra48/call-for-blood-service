package com.helpingspot.callforbloodservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helpingspot.callforbloodservice.model.Donor;
import com.helpingspot.callforbloodservice.model.DonorRequest;
import com.helpingspot.callforbloodservice.model.DonorResponse;
import com.helpingspot.callforbloodservice.service.DonorService;

@RestController
public class DonorJPAController {

	@Autowired
	DonorService donorservice;
	
	@GetMapping(path = "/donors")
	public List<Donor> retrieveAllDonors() {
		return donorservice.retrieveAllDonors();
	}

	@GetMapping(path = "/donors-by-details")
	public List<DonorResponse> retrieveAllDonorsByDetails(@RequestBody DonorRequest donorRequest) {
		return donorservice.retrieveAllDonorsByDetails(donorRequest);
	}

	@PostMapping(path = "/donors")
	public Integer save(@RequestBody Donor donor) {
		try {
			donorservice.save(donor);
		} catch (Exception e) {
			return 0;
		}
		return donor.getId();
	}
	
	@DeleteMapping(path = "/delete")
	public int deleteDonorsBy(@RequestParam("id")int id) {
		donorservice.deleteDonorsBy(id);
		return id;
	}
	
	@DeleteMapping(path = "/delete-by-donor")
	public int deleteDonorsByEntity(@RequestBody Donor donor) {
		donorservice.deleteDonorsByEntity(donor);
		return donor.getId();
	}
	
}
