package com.feut.model;

import java.util.Date;

public class GradeModel {
	
	private Long id;
	private StudentModel student;
	private TeacherModel teacher;
	private CourseModel course;
	private String comment;
	private String code;
	private Date createdTime;
	
	public GradeModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentModel getStudent() {
		return student;
	}

	public void setStudent(StudentModel student) {
		this.student = student;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "GradeModel [id=" + id + ", student=" + student + ", teacher=" + teacher + ", course=" + course
				+ ", comment=" + comment + ", code=" + code + ", createdTime=" + createdTime + "]";
	}
	
	
}
