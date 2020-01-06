package com.helpingspot.callforbloodservice.commandLineRunner;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DonorDAOCommandLineRunner implements CommandLineRunner {

	private final static Logger log = LoggerFactory.getLogger(DonorDAOCommandLineRunner.class);

//	@Autowired
//	DonorDAO donorDAO;

	@Override
	public void run(String... args) throws Exception {

		Date d = new Date();
		// Donor donor = new Donor("test", 2, d, "222222222222", "B+", "India", "AP",
		// "Krishna", "MTM", true);
		// donorDAO.insert(donor);
		// log.info("New User got created : " + donor.getId());

	}

}
