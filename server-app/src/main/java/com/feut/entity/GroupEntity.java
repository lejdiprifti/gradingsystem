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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="group", schema="feut")
@NamedQueries({
	@NamedQuery(query = "Select g from GroupEntity g JOIN DegreeEntity d on d.id = g.degree"
			+ " where g.number = ?1 and d.id=?2 and g.active =?3", name = "Group.CheckIfExists")
})
public class GroupEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5143276838394650380L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="group_number", nullable = false)
	private Long number;
	
	@Column(name="group_email", nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="degree_id")
	private DegreeEntity degree;
	
	@Column(name="active")
	private boolean active;
	
	public GroupEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonIgnore
	public DegreeEntity getDegree() {
		return degree;
	}

	public void setDegree(DegreeEntity degree) {
		this.degree = degree;
	}
	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "GroupEntity [id=" + id + ", number=" + number + ", email=" + email
				+ ", active=" + active + "]";
	}


}
