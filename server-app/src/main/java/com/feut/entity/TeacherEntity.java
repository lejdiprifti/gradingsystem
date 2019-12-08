package com.feut.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="teacher", schema="feut")
@NamedQueries({
	@NamedQuery(name="Teacher.getByDepartment", query="Select t from TeacherEntity t JOIN DepartmentEntity d ON t.department = d.id "
			+ "where d.id = ?1 and t.active = ?2"),
	@NamedQuery(name="Teacher.getByUsername", query ="Select t from TeacherEntity t where t.username = ?1 and t.active=?2"),
	@NamedQuery(name="Teacher.getById", query = "Select t from TeacherEntity t where t.id = ?1 and t.active=?2")
})
public class TeacherEntity extends UserEntity {
	
		
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity department;
	
	@OneToMany(mappedBy= "teacher")
	private List<LecturesEntity> lectures;

	
	public TeacherEntity() {
		
	}


	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public List<LecturesEntity> getLectures() {
		return lectures;
	}

	public void setLectures(List<LecturesEntity> lectures) {
		this.lectures = lectures;
	}


	@Override
	public String toString() {
		return "TeacherEntity [department=" + department + ", lectures=" + lectures + "]";
	}

	
}
