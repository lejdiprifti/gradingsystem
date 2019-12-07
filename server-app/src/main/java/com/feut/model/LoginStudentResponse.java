package com.feut.model;

public class LoginStudentResponse {
	
	private String jwt;
	private StudentModel model;
	
	public LoginStudentResponse() {
		
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public StudentModel getModel() {
		return model;
	}

	public void setModel(StudentModel model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "LoginStudentResponse [jwt=" + jwt + ", model=" + model + "]";
	}
	
}
