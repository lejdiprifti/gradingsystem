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
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.feut.entity.Role; import com.feut.model.LoginRequest; import
 * com.feut.model.LoginTeacherResponse; import com.feut.model.TeacherModel;
 * import com.feut.security.JwtTokenUtil;
 * 
 * @Service public class LoginTeacherService {
 * 
 * @Autowired private LoginTeacherService loginService;
 * 
 * @Autowired private TeacherService teacherService;
 * 
 * @Autowired private JwtTokenUtil jwtTokenUtil;
 * 
 * @Autowired private AuthenticationManager authenticationManager;
 * 
 * 
 * public LoginTeacherService() {
 * 
 * }
 * 
 * public LoginTeacherResponse authenticate(LoginRequest loginRequest) throws
 * Exception { authenticate(loginRequest.getUsername(),
 * loginRequest.getPassword()); final UserDetails userDetails =
 * loginService.loadUserByUsername(loginRequest.getUsername()); Role role = new
 * Role(); role.setId(loginRequest.getRole().getId());
 * role.setName(loginRequest.getRole().getName()); final String token =
 * jwtTokenUtil.generateToken(userDetails,role); LoginTeacherResponse
 * loginResponse = new LoginTeacherResponse(); loginResponse.setJwt(token);
 * loginResponse.setModel(teacherService.getByUsername(loginRequest.getUsername(
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
 * UsernameNotFoundException { TeacherModel model =
 * teacherService.getByUsername(username); return new
 * User(model.getUsername(),model.getPassword(), new ArrayList<>()); }
 * 
 * }
 */