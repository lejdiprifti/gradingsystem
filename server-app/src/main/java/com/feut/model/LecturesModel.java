package com.feut.model;

public class LecturesModel {
	
	private Long id;
	private GroupModel group;
	private TeacherModel teacher;
	private CourseModel course;
	
	public LecturesModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GroupModel getGroup() {
		return group;
	}

	public void setGroup(GroupModel group) {
		this.group = group;
	}

	public TeacherModel getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherModel teacher) {
		this.teacher = teacher;
	}

	public CourseModel getCourse() {
		return course;
	}

	public void setCourse(CourseModel course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "LecturesModel [id=" + id + ", group=" + group + ", teacher=" + teacher + ", course=" + course + "]";
	}
	
}
