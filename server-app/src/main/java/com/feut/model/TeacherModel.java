package com.feut.model;

import java.util.List;

public class TeacherModel {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String personalNumber;
	private String username;
	private String password;
	private DepartmentModel department;
	private List<LecturesModel> lecturesList;
	
	public TeacherModel() {
		
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

	public DepartmentModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}

	public List<LecturesModel> getLecturesList() {
		return lecturesList;
	}

	public void setLecturesList(List<LecturesModel> lecturesList) {
		this.lecturesList = lecturesList;
	}

	@Override
	public String toString() {
		return "TeacherModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", personalNumber="
				+ personalNumber + ", username=" + username + ", password=" + password + ", department=" + department
				+ ", lecturesList=" + lecturesList + "]";
	}
	
}
