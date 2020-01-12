package com.helpingspot.callforbloodservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helpingspot.callforbloodservice.model.Donor;
import com.helpingspot.callforbloodservice.model.DonorRequest;
import com.helpingspot.callforbloodservice.model.DonorResponse;
import com.helpingspot.callforbloodservice.service.DonorService;

@Controller
@RequestMapping("/donors")
public class DonorJPAController {

	private final org.slf4j.Logger log = LoggerFactory.getLogger(DonorJPAController.class);

	private final static String SHOW_REGISTRATION_FORM = "registration-form";
	private final static String DONOR_FORM = "donor-form";

	@Autowired
	DonorService donorService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "fancy-login";
	}

	// add request mapping for /access-denied

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}

//	@GetMapping(path = "/donors")
//	public List<Donor> retrieveAllDonors() {
//		return donorService.retrieveAllDonors();
//	}

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
	public int deleteDonorsBy(@RequestParam("id") int id) {
		donorService.deleteDonorsBy(id);
		return id;
	}

	@DeleteMapping(path = "/delete-by-donor")
	public int deleteDonorsByEntity(@RequestBody Donor donor) {
		donorService.deleteDonorsByEntity(donor);
		return donor.getId();
	}

	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {

		theModel.addAttribute("crmUser", new Donor());

		return SHOW_REGISTRATION_FORM;
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") Donor donor, BindingResult theBindingResult,
			Model theModel) {

		String userName = donor.getUserName();
		logger.info("Processing registration form for: {}" + userName);

		// form validation
		if (theBindingResult.hasErrors()) {
			return SHOW_REGISTRATION_FORM;
		}

		// check the database if user already exists
		Donor existing = donorService.findByUserName(userName);
		if (existing != null) {
			theModel.addAttribute("crmUser", new Donor());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
			return SHOW_REGISTRATION_FORM;
		}

		// create user account
		donorService.save(donor);

		logger.info("Successfully created user: " + userName);

		return "registration-confirmation";
	}

/////////////////////////////////////////////////////////////////////////////
//////////////////////////CRUD OPERATIONS////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////

	// add mapping for "/list"

	@GetMapping("/list")
	public String listDonors(Model theModel) {
		// get donors from db
		List<Donor> donors = donorService.retrieveAllDonors();

		// add to the spring model
		theModel.addAttribute("donors", donors);

		return "list-donors";
	}

	@GetMapping("/add")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
		Donor donor = new Donor();
		theModel.addAttribute("donor", donor);
		// return DONOR_FORM;
		return "donor-form";
	}

	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("donorId") int id, Model theModel) {
		// get the donor from the service
		Optional<Donor> donor = donorService.retrieveDonorById(id);

		log.info("showFormForUpdate : Existing donor detials for update : {}", donor);

		// set donor as a model attribute to pre-populate the form
		if (donor.isPresent()) {
			theModel.addAttribute("donor", donor);
		}
		// send over to our form
		return DONOR_FORM;
	}

	@PostMapping("/save")
	public String saveDonors(@Valid @ModelAttribute("donor") Donor donor) {
		log.info("Entering to save donor details : {}", donor);
		// save the donor
		donorService.save(donor);

		log.info("Exiting save donor details : {}", donor);

		// use a redirect to prevent duplicate submissions
		return "redirect:/donors/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("donorId") int id) {
		// delete the donor
		donorService.deleteDonorsBy(id);
		// redirect to /donors/list
		return "redirect:/donors/list";
	}

}
