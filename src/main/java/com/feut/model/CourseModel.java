package com.feut.model;

import java.util.List;

public class CourseModel {
	
	private Long id;
	private DepartmentModel department;
	private DegreeModel degree;
	private String syllabus;
	private List<LecturesModel> lecturesList;
	private List<GradeModel> gradeList;
	
	private boolean active;
	
	public CourseModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DepartmentModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}

	public DegreeModel getDegree() {
		return degree;
	}

	public void setDegree(DegreeModel degree) {
		this.degree = degree;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public List<LecturesModel> getLecturesList() {
		return lecturesList;
	}

	public void setLecturesList(List<LecturesModel> lecturesList) {
		this.lecturesList = lecturesList;
	}

	public List<GradeModel> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<GradeModel> gradeList) {
		this.gradeList = gradeList;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "CourseModel [id=" + id + ", department=" + department + ", degree=" + degree + ", syllabus=" + syllabus
				+ ", lecturesList=" + lecturesList + ", gradeList=" + gradeList + ", active=" + active + "]";
	}

}
