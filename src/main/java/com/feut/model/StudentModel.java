package com.feut.model;

import java.util.Date;
import java.util.List;

public class StudentModel {
	
	private Long id;
	private String firstName;
	private String fatherName;
	private String lastName;
	private String personalNumber;
	private String username;
	private String password;
	private RoleModel role;
	private Date birthdate;
	private GroupModel gorup;
	private String email;
	private List<GradeModel> gradeList;
	
	public StudentModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public GroupModel getGorup() {
		return gorup;
	}

	public void setGorup(GroupModel gorup) {
		this.gorup = gorup;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<GradeModel> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<GradeModel> gradeList) {
		this.gradeList = gradeList;
	}

	@Override
	public String toString() {
		return "StudentModel [id=" + id + ", firstName=" + firstName + ", fatherName=" + fatherName + ", lastName="
				+ lastName + ", personalNumber=" + personalNumber + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", birthdate=" + birthdate + ", gorup=" + gorup + ", email=" + email + "]";
	}
	
}
