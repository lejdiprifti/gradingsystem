package com.feut.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="group", schema="feut", uniqueConstraints = {
	@UniqueConstraint(columnNames="group_number"),
	@UniqueConstraint(columnNames="group_email")
})
public class GroupEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5143276838394650380L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="group_number")
	private Long number;
	
	@Column(name="group_email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="degree_id")
	private DegreeEntity degree;
	
	@OneToMany(mappedBy="group", fetch = FetchType.LAZY)
	private List<StudentEntity> studentList;
	
	@Column(name="active")
	private boolean active;
	
	public GroupEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DegreeEntity getDegree() {
		return degree;
	}

	public void setDegree(DegreeEntity degree) {
		this.degree = degree;
	}

	public List<StudentEntity> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentEntity> studentList) {
		this.studentList = studentList;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "GroupEntity [id=" + id + ", number=" + number + ", email=" + email + ", degree=" + degree
				+ ", studentList=" + studentList + ", active=" + active + "]";
	}


}
