package com.feut.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="lectures",schema="feut")
@NamedQueries({
	@NamedQuery(name="Lectures.getByGroup", query = "Select l from LecturesEntity l Join GroupEntity g on l.group = g.id "
			+ "where g.id = ?1 and l.active = ?2"),
	@NamedQuery(name="Lectures.getById", query = "Select l from LecturesEntity l where l.id = ?1 and l.active =?2"),
	@NamedQuery(name="Lectures.getByTeacher", query = "Select l from LecturesEntity l Join TeacherEntity t on t.id = l.teacher"
			+ " where t.id = ?1 and l.active = ?2")
})
public class LecturesEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1093784310153204027L;

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

	@JsonIgnore
	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	@JsonIgnore
	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	@JsonIgnore
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
