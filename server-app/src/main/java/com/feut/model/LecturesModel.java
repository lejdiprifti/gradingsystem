package com.feut.model;

public class LecturesModel {
	
	private Long id;
	private Long groupId;
	private Long teacherId;
	private Long courseId;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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
		return "LecturesModel [id=" + id + ", groupId=" + groupId + ", teacherId=" + teacherId + ", courseId="
				+ courseId + ", group=" + group + ", teacher=" + teacher + ", course=" + course + "]";
	}

}
