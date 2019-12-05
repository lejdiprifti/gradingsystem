package com.feut.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="group", schema="feut")
public class GroupEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="group_number")
	private Long number;
	
	@ManyToOne
	@JoinColumn(name="degree_id")
	private DegreeEntity degree;
	
	@OneToMany(mappedBy="group")
	private List<StudentEntity> studentList;

}
