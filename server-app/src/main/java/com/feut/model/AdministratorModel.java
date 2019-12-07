package com.feut.model;

import java.util.Date;

public class AdministratorModel {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String personalNumber;
	private String username;
	private String password;
	private RoleModel role;
	private Date lastLogin;
	
	
	public AdministratorModel() {
		
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


	public RoleModel getRole() {
		return role;
	}


	public void setRole(RoleModel role) {
		this.role = role;
	}


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	@Override
	public String toString() {
		return "AdministratorModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", personalNumber=" + personalNumber + ", username=" + username + ", password=" + password + ", role="
				+ role + ", lastLogin=" + lastLogin + "]";
	}
	
	
}
