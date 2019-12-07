package com.feut.model;

import java.util.Date;

public class AdministratorModel extends UserModel {
	
	private Date lastLogin;
	
	
	public AdministratorModel() {
		
	}


	

	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}




	@Override
	public String toString() {
		return "AdministratorModel [lastLogin=" + lastLogin + "]";
	}


	
}
