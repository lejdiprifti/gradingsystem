package com.feut.model;

public class DepartmentModel {
	
	private Long id;
	private String name;
	
	public DepartmentModel() {
		
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

	@Override
	public String toString() {
		return "DepartmentModel [id=" + id + ", name=" + name + "]";
	}
	
	
}
