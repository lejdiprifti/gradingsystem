package com.feut.model;

import java.util.List;

public class StudentModel extends UserModel {
	
	
	private GroupModel gorup;
	private List<GradeModel> gradeList;
	
	public StudentModel() {
		
	}

	public GroupModel getGorup() {
		return gorup;
	}

	public void setGorup(GroupModel gorup) {
		this.gorup = gorup;
	}

	public List<GradeModel> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<GradeModel> gradeList) {
		this.gradeList = gradeList;
	}



	@Override
	public String toString() {
		return "StudentModel [gorup=" + gorup + ", gradeList=" + gradeList + "]";
	}

	
	
}
