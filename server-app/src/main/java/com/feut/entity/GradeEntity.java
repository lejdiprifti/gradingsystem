package com.feut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="grade", schema="feut", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"student_id", "course_id", "code"})
})
public class GradeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private StudentEntity student;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private TeacherEntity teacher;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private CourseEntity course;
	
	@NotNull
	@Column(name="comment", length=1000)
	private String comment;
	
	@NotNull
	@Column(name="code")
	private String code;
	
	@Column(name="grade")
	private Long grade;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name="active")
	private boolean active;
	
	public GradeEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "GradeEntity [id=" + id + ", student=" + student + ", teacher=" + teacher + ", course=" + course
				+ ", comment=" + comment + ", code=" + code + ", grade=" + grade + ", createdTime=" + createdTime
				+ ", active=" + active + "]";
	}

}
