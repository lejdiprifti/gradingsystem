package com.feut.model;

import java.util.List;

public class DepartmentModel {
	
	private Long id;
	private String name;
	private String description;
	private List<CourseModel> courseList;
	private List<TeacherModel> teacherList;
	
	public DepartmentModel() {
		
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CourseModel> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseModel> courseList) {
		this.courseList = courseList;
	}

	public List<TeacherModel> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<TeacherModel> teacherList) {
		this.teacherList = teacherList;
	}

	@Override
	public String toString() {
		return "DepartmentModel [id=" + id + ", name=" + name + ", description=" + description + ", courseList="
				+ courseList + ", teacherList=" + teacherList + "]";
	}
	
	
}
