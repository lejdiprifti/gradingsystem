package com.feut.model;

import java.util.Date;

public class GradeModel {
	
	private Long id;
	private StudentModel student;
	private Long studentId;
	private TeacherModel teacher;
	private Long teacherId;
	private CourseModel course;
	private Long courseId;
	private String comment;
	private String code;
	private Long grade;
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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "GradeModel [id=" + id + ", student=" + student + ", studentId=" + studentId + ", teacher=" + teacher
				+ ", teacherId=" + teacherId + ", course=" + course + ", courseId=" + courseId + ", comment=" + comment
				+ ", code=" + code + ", grade=" + grade + ", createdTime=" + createdTime + "]";
	}	
}
