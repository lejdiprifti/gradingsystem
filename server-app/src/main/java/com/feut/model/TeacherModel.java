package com.feut.model;

import java.util.List;

public class TeacherModel extends UserModel {
	
	private DepartmentModel department;
	private List<LecturesModel> lecturesList;
	
	public TeacherModel() {
		
	}


	public DepartmentModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}

	public List<LecturesModel> getLecturesList() {
		return lecturesList;
	}

	public void setLecturesList(List<LecturesModel> lecturesList) {
		this.lecturesList = lecturesList;
	}


	@Override
	public String toString() {
		return "TeacherModel [department=" + department + ", lecturesList=" + lecturesList + "]";
	}
	
	
}
