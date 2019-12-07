package com.feut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="administrator", schema="feut")
public class AdministratorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="personal_number", nullable=false, unique=true)
	private String personalNumber;
	
	@Column(name="username", nullable=false, unique=true, updatable=false)
	private String username;
	
	@Column(name="password", nullable=false, updatable=true)
	private String password;
	
	@Column(name="role_id", nullable=false)
	private Role role;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login", nullable=false,updatable=true)
	private Date lastLogin;
	
	@Column(name="active",nullable=false)
	private boolean active;
	
	public AdministratorEntity() {
		
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "AdministratorEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", personalNumber=" + personalNumber + ", username=" + username + ", password=" + password + ", role="
				+ role + ", lastLogin=" + lastLogin + ", active=" + active + "]";
	}

}
