package com.feut.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.LoginRequest;
import com.feut.model.LoginResponse;
import com.feut.service.LoginService;

@RestController
@RequestMapping(path="/login", consumes="application/json", produces="application/json")
@CrossOrigin("https://myfeut.firebaseapp.com")
public class LoginResource {
	
	private static Logger logger = LogManager.getLogger(LoginResource.class);
	
	@Autowired
	private LoginService loginService;
	
	public LoginResource() {
		
	}
	
	@PostMapping
	public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
		logger.info("Logging in user");
		return new ResponseEntity<LoginResponse>(loginService.authenticate(loginRequest), HttpStatus.OK);
	}

}
