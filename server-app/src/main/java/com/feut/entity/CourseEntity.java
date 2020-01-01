package com.feut.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course", schema="feut", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name", "degree_id", "active"}),
		@UniqueConstraint(columnNames = {"name", "department_id", "active"})
})
@NamedQueries({
	@NamedQuery(name="Course.getAll", query="Select c From CourseEntity c where c.active = ?1"),
	@NamedQuery(name="Course.getById", query="Select c From CourseEntity c where c.id = ?1 and c.active =?2"),
	@NamedQuery(name="Course.getByDegree", query = "Select c From CourseEntity c  JOIN DegreeEntity d ON c.degree = d.id "
			+ "WHERE d.id = ?1 and c.active = ?2"),
	@NamedQuery(name="Course.getByDepartment", query = "Select c from CourseEntity c where c.department = ?1 and c.active =?2")
	})
public class CourseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -272395124946946499L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity department;

	@ManyToOne
	@JoinColumn(name="degree_id")
	private DegreeEntity degree;
	
	@Column(name="syllabus", length=2000, nullable = false)
	private String syllabus;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	@JsonIgnore
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
