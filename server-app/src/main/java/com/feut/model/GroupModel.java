package com.feut.model;

import java.util.List;

public class GroupModel {
	
	private Long id;
	private DegreeModel degree;
	private Long number;
	private String email;
	private List<StudentModel> studentList;
	private List<LecturesModel> lecturesList;
	
	public GroupModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DegreeModel getDegree() {
		return degree;
	}

	public void setDegree(DegreeModel degree) {
		this.degree = degree;
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

	public List<StudentModel> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentModel> studentList) {
		this.studentList = studentList;
	}
	

	public List<LecturesModel> getLecturesList() {
		return lecturesList;
	}

	public void setLecturesList(List<LecturesModel> lecturesList) {
		this.lecturesList = lecturesList;
	}

	@Override
	public String toString() {
		return "GroupModel [id=" + id + ", degree=" + degree + ", number=" + number + ", email=" + email
				+ ", studentList=" + studentList + ", lecturesList=" + lecturesList + "]";
	}
	
}
