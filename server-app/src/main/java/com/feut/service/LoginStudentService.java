/*
 * package com.feut.service;
 * 
 * import java.util.ArrayList;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.BadCredentialsException; import
 * org.springframework.security.authentication.DisabledException; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.feut.entity.Role; import com.feut.model.LoginRequest; import
 * com.feut.model.LoginStudentResponse; import com.feut.model.StudentModel;
 * import com.feut.security.JwtTokenUtil;
 * 
 * 
 * @Service public class LoginStudentService implements UserDetailsService {
 * 
 * @Autowired private LoginStudentService loginService;
 * 
 * @Autowired private StudentService studentService;
 * 
 * @Autowired private JwtTokenUtil jwtTokenUtil;
 * 
 * @Autowired private AuthenticationManager authenticationManager;
 * 
 * 
 * public LoginStudentService() {
 * 
 * }
 * 
 * public LoginStudentResponse authenticate(LoginRequest loginRequest) throws
 * Exception { authenticate(loginRequest.getUsername(),
 * loginRequest.getPassword()); final UserDetails userDetails =
 * loginService.loadUserByUsername(loginRequest.getUsername()); Role role = new
 * Role(); role.setId(loginRequest.getRole().getId());
 * role.setName(loginRequest.getRole().getName()); final String token =
 * jwtTokenUtil.generateToken(userDetails,role); LoginStudentResponse
 * loginResponse = new LoginStudentResponse(); loginResponse.setJwt(token);
 * loginResponse.setModel(studentService.getByUsername(loginRequest.getUsername(
 * ))); return loginResponse; }
 * 
 * private void authenticate(String username, String password) throws Exception
 * { try { authenticationManager.authenticate(new
 * UsernamePasswordAuthenticationToken(username, password)); } catch
 * (DisabledException e) { throw new Exception("USER_DISABLED", e); } catch
 * (BadCredentialsException e) { throw new Exception("INVALID_CREDENTIALS", e);
 * } }
 * 
 * 
 * public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { StudentModel model =
 * studentService.getByUsername(username); return new
 * User(model.getUsername(),model.getPassword(), new ArrayList<>()); }
 * 
 * }
 */