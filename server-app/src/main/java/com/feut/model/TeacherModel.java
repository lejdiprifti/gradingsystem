package com.feut.model;

import java.util.List;

public class TeacherModel extends UserModel {
	
	private Long departmentId;
	private DepartmentModel department;
	private List<LecturesModel> lecturesList;
	
	public TeacherModel() {
		
	}


	public Long getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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
		return "TeacherModel [departmentId=" + departmentId + ", department=" + department + ", lecturesList="
				+ lecturesList + "]";
	}
	
}
