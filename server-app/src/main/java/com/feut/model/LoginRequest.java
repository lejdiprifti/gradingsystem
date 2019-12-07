package com.feut.model;

public class LoginRequest {

		private String username;
		private String password;
		private RoleModel role;
		
		public LoginRequest() {
			
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		

		public RoleModel getRole() {
			return role;
		}

		public void setRole(RoleModel role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "LoginRequest [username=" + username + ", password=" + password + ", role=" + role + "]";
		}

	
		
}
