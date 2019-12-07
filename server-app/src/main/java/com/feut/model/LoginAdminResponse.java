package com.feut.model;

public class LoginAdminResponse {
	
	private String jwt;
	private AdministratorModel model;
	
	public LoginAdminResponse() {
		
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public AdministratorModel getModel() {
		return model;
	}

	public void setModel(AdministratorModel model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "LoginAdminResponse [jwt=" + jwt + ", model=" + model + "]";
	}
	
	
}
