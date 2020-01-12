package com.helpingspot.callforbloodservice.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.helpingspot.callforbloodservice.service.DonorService;

@Configuration
//@EnableWebSecurity
@EnableWebMvc
public class DonorSecurityConfig extends WebSecurityConfigurerAdapter {

	private final org.slf4j.Logger log = LoggerFactory.getLogger(DonorSecurityConfig.class);

	// add a reference to our security data source
	@Autowired
	private DonorService donorService;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("Entering Configuration method with auth details {}", auth);
		auth.authenticationProvider(authenticationProvider());
		log.info("Exiting Configuration method");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("Entering configure with HttpSecurity details : {}", http);
		http.authorizeRequests().antMatchers("/").authenticated().and().formLogin().loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser").successHandler(customAuthenticationSuccessHandler)
				.permitAll().and().logout().logoutSuccessUrl("/donors/showMyLoginPage").permitAll().and()
				.exceptionHandling().accessDeniedPage("/access-denied");
		log.info("Exiting Configuration method");
	}

	// authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		log.info("Entering Authentication Provider....");
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(donorService); // set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
		log.info("Exiting Authentication Provider....");
		return auth;
	}

	// beans
	// bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
