package com.helpingspot.callforbloodservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallForBloodServiceController {

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello")
	public String getHello(Model model) {
		
		model.addAttribute("theDate :"+new java.util.Date());
		return "helloworld";
	}

}
