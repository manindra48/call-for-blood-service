package com.helpingspot.callforbloodservice.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.helpingspot.callforbloodservice.model.Donor;
import com.helpingspot.callforbloodservice.service.DonorService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final org.slf4j.Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

	@Autowired
	private DonorService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info(
				"Entering onAuthenticationSuccess method with request : {} response : {} and Authentication : {} details",
				request, response, authentication);

		String userName = authentication.getName();

		Donor theUser = userService.findByUserName(userName);

		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);

		// forward to home page
		log.info("onAuthenticationSuccess: Redirecting to list of donors page");
		response.sendRedirect(request.getContextPath() + "/donors/list");
	}

}
