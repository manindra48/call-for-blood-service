package com.helpingspot.callforbloodservice.commandLineRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DonorDAOCommandLineRunner2 implements CommandLineRunner {

	private final static Logger log = LoggerFactory.getLogger(DonorDAOCommandLineRunner2.class);

//	@Autowired
//	DonorRepository donorRepository;

	@Override
	public void run(String... args) throws Exception {
//		Date d = new Date();
//		Donor donor = new Donor("test",1,d,"222222222222","B+","India","AP","Krishna","MTM",true);
//		donorRepository.save(donor);
//		log.info("New User got created : " + donor.getId());

	}

}
