package com.feut.model;

import java.util.List;

public class DegreeModel {
	
	private Long id;
	private String syllabus;
	private boolean active;
	private List<GroupModel> groupList;
	private List<CourseModel> courseList;
	
	public DegreeModel() {
		
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<GroupModel> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupModel> groupList) {
		this.groupList = groupList;
	}

	public List<CourseModel> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseModel> courseList) {
		this.courseList = courseList;
	}

	@Override
	public String toString() {
		return "DegreeModel [id=" + id + ", syllabus=" + syllabus + ", active=" + active + ", groupList=" + groupList
				+ ", courseList=" + courseList + "]";
	}
	
}
