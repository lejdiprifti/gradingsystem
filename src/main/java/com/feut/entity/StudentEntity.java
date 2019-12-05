package com.feut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student", schema="feut")
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private String id;
	
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
	
	@NotNull
	@Column(name= "birthdate")
	private Date birthdate;
	

}
