package com.feut.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="student", schema="feut")
@NamedQueries({
	@NamedQuery(name="Student.getByGroup", query="Select s from StudentEntity s Join GroupEntity g on g.id = s.group where g.id =?1 and s.active =?2"),
	@NamedQuery(name="Student.getByUsername", query="Select s from StudentEntity s where s.username = ?1 and s.active=?2"),
	@NamedQuery(name="Student.getById", query="Select s from StudentEntity s where s.id = ?1 and s.active=?2"),
	@NamedQuery(name="Student.checkIfExists", query="Select s from StudentEntity s where (s.personalNumber =?1 or s.username=?3) and s.active= ?2")
})
public class StudentEntity extends UserEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8157320698791509567L;
	

	@ManyToOne
	@JoinColumn(name="group_id")
	private GroupEntity group;
	
	@Column(name="access_token")
	private String accessToken;
	
	
	public StudentEntity() {
		
	}

	@JsonIgnore
	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "StudentEntity [group=" + group + ", accessToken=" + accessToken + "]";
	}

	


	
	
}
