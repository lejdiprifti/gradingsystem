package com.feut.entity;


import java.util.List;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student", schema="feut")
@NamedQueries({
	@NamedQuery(name="Student.getByGroup", query="Select s from StudentEntity s Join GroupEntity g on g.id = s.group where s.group =?1 and s.active =?2"),
	@NamedQuery(name="Student.getByUsername", query="Select s from StudentEntity s where s.username = ?1 and s.active=?2"),
	@NamedQuery(name="Student.getById", query="Select s from StudentEntity s where s.id = ?1 and s.active=?2")
})
public class StudentEntity extends UserEntity {
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private GroupEntity group;
	
	@OneToMany(mappedBy="student")
	private List<GradeEntity> gradeList;
	
	
	public StudentEntity() {
		
	}

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	public List<GradeEntity> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<GradeEntity> gradeList) {
		this.gradeList = gradeList;
	}

	@Override
	public String toString() {
		return "StudentEntity [group=" + group + ", gradeList=" + gradeList + "]";
	}

	
	
}
