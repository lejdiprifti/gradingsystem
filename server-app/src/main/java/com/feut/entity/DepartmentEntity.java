package com.feut.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="department", schema="feut", uniqueConstraints = {
		@UniqueConstraint(columnNames="department_name")
})
@NamedQueries({
	@NamedQuery(name="Department.getById", query = "Select d from DepartmentEntity d where d.id = ?1 and d.active = ?2"),
	@NamedQuery(name="Department.getAll", query = "Select d from DepartmentEntity d where d.active = ?1")
})
public class DepartmentEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8986422020350397439L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="department_id")
	private Long id;
	
	@Column(name="department_name")
	private String name;
	
	@Column(name="department_description", length = 2000)
	private String description;

	
	@Column(name="active")
	private boolean active;
	
	public DepartmentEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DepartmentEntity [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active
				+ "]";
	}


	
}
