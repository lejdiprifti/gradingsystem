package com.feut.entity;

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

@Entity
@Table(name="course", schema="feut")
public class CourseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity department;
	
	@ManyToOne
	@JoinColumn(name="degree_id")
	private DegreeEntity degree;
	
	@Column(name="syllabus", length=2000)
	private String syllabus;
	
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	private List<LecturesEntity> lectureList;
	
	@Column(name="active")
	private boolean active;

	public CourseEntity() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public DegreeEntity getDegree() {
		return degree;
	}

	public void setDegree(DegreeEntity degree) {
		this.degree = degree;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
