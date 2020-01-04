package com.feut.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

@Entity
@Table(name="teacher", schema="feut")
@NamedQueries({
	@NamedQuery(name="Teacher.getByDepartment", query="Select t from TeacherEntity t JOIN DepartmentEntity d ON t.department = d.id "
			+ "where d.id = ?1 and t.active = ?2"),
	@NamedQuery(name="Teacher.getByUsername", query ="Select t from TeacherEntity t where t.username = ?1 and t.active=?2"),
	@NamedQuery(name="Teacher.getById", query = "Select t from TeacherEntity t where t.id = ?1 and t.active=?2"),
	@NamedQuery(name="Teacher.checkIfExists", query ="Select t from TeacherEntity t where t.personalNumber = ?1 and t.username=?2 and t.active =?3"),
	@NamedQuery(name="Teacher.checkIfExistsByPN", query ="Select t from TeacherEntity t where t.personalNumber = ?1 and t.active =?2 and t.id != ?3")
})
public class TeacherEntity extends UserEntity implements Serializable {
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3340542481583049379L;
	

	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity department;
	


	
	public TeacherEntity() {
		
	}


	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "TeacherEntity [department=" + department + "]";
	}

	
	
}
