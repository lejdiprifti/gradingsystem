package com.feut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="administrator", schema="feut")
public class AdministratorEntity extends UserEntity {
	
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login", nullable=false,updatable=true)
	private Date lastLogin;
	
	
	public AdministratorEntity() {
		
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "AdministratorEntity [lastLogin=" + lastLogin + "]";
	}



}
