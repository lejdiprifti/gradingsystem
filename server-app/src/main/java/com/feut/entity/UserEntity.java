package com.feut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user", schema="feut", uniqueConstraints  = {
		@UniqueConstraint(columnNames= {"username", "active"}),
		@UniqueConstraint(columnNames= {"personal_number", "active"}),
		@UniqueConstraint(columnNames= {"email", "active"})
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="User.getByUsername", query="Select s from UserEntity s where s.username = ?1 and s.active=?2")
})

public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	
	@Column(name = "first_name", length = 20, nullable = false)
	private String firstName;
	

	@Column(name = "father_name", length = 20, nullable = false)
	private String fatherName;
	
	
	@Column(name= "last_name", length = 20, nullable = false)
	private String lastName;
	
	
	@Column(name = "personal_number", length = 10, nullable = false)
	private String personalNumber;
	
	 
	@Column(name = "username", length = 10, nullable = false)
	private String username;
	
	 
	@Column(name= "password", nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	 
	@Column(name= "birthdate", nullable=true)
	private Date birthdate;
	
	 
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="active")
	private boolean active;
	
	public UserEntity() {
		
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", firstName=" + firstName + ", fatherName=" + fatherName + ", lastName="
				+ lastName + ", personalNumber=" + personalNumber + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", birthdate=" + birthdate + ", email=" + email + ", active=" + active + "]";
	}
	
	
	

}
