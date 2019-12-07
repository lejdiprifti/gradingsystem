package com.feut.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.feut.model.LoginRequest;
import com.feut.model.LoginResponse;
import com.feut.model.UserModel;
import com.feut.security.JwtTokenUtil;

@Service
public class LoginService implements UserDetailsService{
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	public LoginService() {
		
	}
	
	
	public LoginResponse authenticate(LoginRequest loginRequest) throws Exception {
		authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		final UserDetails userDetails = loginService.loadUserByUsername(loginRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setJwt(token);
		loginResponse.setUser(userService.getByUsername(loginRequest.getUsername()));
		return loginResponse;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel model = userService.getByUsername(username);
		return new User(model.getUsername(),model.getPassword(), new ArrayList<>());
	}
}
