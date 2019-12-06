package com.feut.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student", schema="feut")
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Column(name = "first_name", length = 20)
	private String firstName;
	
	@NotNull
	@Column(name = "father_name", length = 20)
	private String fatherName;
	
	@NotNull
	@Column(name= "last_name", length = 20)
	private String lastName;
	
	@NotNull
	@Column(name = "personal_number", length = 10)
	private String personalNumber;
	
	@NotNull
	@Column(name = "username", length = 7)
	private String username;
	
	@NotNull
	@Column(name= "password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@NotNull
	@Column(name= "birthdate")
	private Date birthdate;
	
	@NotNull
	@Column(name="email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private GroupEntity group;
	
	@OneToMany(mappedBy="student")
	private List<GradeEntity> gradeList;
	
	@Column(name="active")
	private boolean active;
	
	public StudentEntity() {
		
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

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<GradeEntity> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<GradeEntity> gradeList) {
		this.gradeList = gradeList;
	}

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", firstName=" + firstName + ", fatherName=" + fatherName + ", lastName="
				+ lastName + ", personalNumber=" + personalNumber + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", birthdate=" + birthdate + ", email=" + email + ", group=" + group + ", active="
				+ active + "]";
	}
	
	
}
