package com.helpingspot.callforbloodservice.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	DonorService donorService;
	
    private Logger logger = Logger.getLogger(getClass().getName());

	
	@GetMapping(path = "/donors")
	public List<Donor> retrieveAllDonors() {
		return donorService.retrieveAllDonors();
	}

	@GetMapping(path = "/donors-by-details")
	public List<DonorResponse> retrieveAllDonorsByDetails(@RequestBody DonorRequest donorRequest) {
		return donorService.retrieveAllDonorsByDetails(donorRequest);
	}

	@PostMapping(path = "/registration")
	public Integer save(@RequestBody Donor donor) {
		try {
			donorService.save(donor);
		} catch (Exception e) {
			return 0;
		}
		return donor.getId();
	}
	
	@DeleteMapping(path = "/delete")
	public int deleteDonorsBy(@RequestParam("id")int id) {
		donorService.deleteDonorsBy(id);
		return id;
	}
	
	@DeleteMapping(path = "/delete-by-donor")
	public int deleteDonorsByEntity(@RequestBody Donor donor) {
		donorService.deleteDonorsByEntity(donor);
		return donor.getId();
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") Donor donor, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = donor.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        Donor existing = donorService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new Donor());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
        
        // create user account        						
        donorService.save(donor);
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
	
}
