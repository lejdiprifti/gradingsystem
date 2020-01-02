package com.feut.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="degree", schema="feut")
@NamedQuery(query = "Select d from DegreeEntity d where d.title = ?1 and d.active =?2", name="Degree.checkIfExists")
public class DegreeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3029776994623514319L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name="syllabus", length = 5000, nullable = false)
	private String syllabus;
	
	
	@Column(name="active")
	private boolean active;
	
	public DegreeEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Override
	public String toString() {
		return "DegreeEntity [id=" + id + ", title=" + title + ", syllabus=" + syllabus + ", active=" + active + "]";
	}
	

}
