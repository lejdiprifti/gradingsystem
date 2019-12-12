package com.feut.model;

import java.util.List;

public class StudentModel extends UserModel {
	
	
	private GroupModel group;
	private List<GradeModel> gradeList;
	
	public StudentModel() {
		
	}

	public GroupModel getGroup() {
		return group;
	}

	public void setGroup(GroupModel gorup) {
		this.group = gorup;
	}

	public List<GradeModel> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<GradeModel> gradeList) {
		this.gradeList = gradeList;
	}



	@Override
	public String toString() {
		return "StudentModel [group=" + group + ", gradeList=" + gradeList + "]";
	}

	
	
}
