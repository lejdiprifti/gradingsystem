package com.feut.model;

public class LoginTeacherResponse {
	
	private String jwt;
	private TeacherModel model;
	
	public LoginTeacherResponse() {
		
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public TeacherModel getModel() {
		return model;
	}

	public void setModel(TeacherModel model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "LoginTeacherResponse [jwt=" + jwt + ", model=" + model + "]";
	}
	
	
}
