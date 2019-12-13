package com.feut.model;

import java.util.List;

public class StudentModel extends UserModel {
	
	
	private GroupModel group;
	private Long groupId;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "StudentModel [group=" + group + ", groupId=" + groupId + ", gradeList=" + gradeList + "]";
	}	
}
