package com.feut.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feut.model.LoginRequest;
import com.feut.model.LoginStudentResponse;
import com.feut.model.LoginTeacherResponse;
import com.feut.service.LoginStudentService;
import com.feut.service.LoginTeacherService;

@RestController
@RequestMapping(path="/login", consumes="application/json", produces="application/json")
public class LoginResource {
	
	private static Logger logger = LogManager.getLogger(LoginResource.class);
	
	@Autowired
	private LoginStudentService loginStudentService;
	
	@Autowired
	private LoginTeacherService loginTeacherService;
	
	public LoginResource() {
		
	}
	
	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
		Long roleId = loginRequest.getRole().getId();
		logger.info(roleId);
		if (roleId == 1) {
			logger.info("roleId eshte", roleId);
			return new ResponseEntity<LoginStudentResponse>(loginStudentService.authenticate(loginRequest), HttpStatus.OK);
		} else {
			return new ResponseEntity<LoginTeacherResponse>(loginTeacherService.authenticate(loginRequest), HttpStatus.OK);
		}
	}

}
