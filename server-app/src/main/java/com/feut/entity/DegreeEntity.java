package com.feut.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="degree", schema="feut")
public class DegreeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="syllabus", length = 5000)
	private String syllabus;
	
	@OneToMany(mappedBy="degree")
	private List<GroupEntity> groupList;
	
	@OneToMany(mappedBy="degree")
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
		return "DegreeEntity [id=" + id + ", syllabus=" + syllabus + ", groupList=" + groupList + ", courseList="
				+ courseList + ", active=" + active + "]";
	}

}
