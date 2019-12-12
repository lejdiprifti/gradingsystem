package com.feut.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="degree", schema="feut", uniqueConstraints = {
		@UniqueConstraint(columnNames = "title")
})
public class DegreeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3029776994623514319L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="syllabus", length = 5000)
	private String syllabus;
	
	@JsonIgnore
	@OneToMany(mappedBy="degree", fetch = FetchType.LAZY)
	private List<GroupEntity> groupList;
	
	@JsonIgnore
	@OneToMany(mappedBy="degree", fetch = FetchType.LAZY)
	private List<CourseEntity> courseList;
	
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

	public List<GroupEntity> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupEntity> groupList) {
		this.groupList = groupList;
	}

	public List<CourseEntity> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseEntity> courseList) {
		this.courseList = courseList;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DegreeEntity [id=" + id + ", title=" + title + ", syllabus=" + syllabus + ", groupList=" + groupList
				+ ", courseList=" + courseList + ", active=" + active + "]";
	}

	

}
