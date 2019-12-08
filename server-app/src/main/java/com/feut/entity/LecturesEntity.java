package com.feut.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lectures",schema="feut")
public class LecturesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private GroupEntity group;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private TeacherEntity teacher;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private CourseEntity course;
	
	@Column(name="active")
	private boolean active;
	
	public LecturesEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "LecturesEntity [id=" + id + ", group=" + group + ", teacher=" + teacher + ", course=" + course
				+ ", active=" + active + "]";
	}
	
	
}
