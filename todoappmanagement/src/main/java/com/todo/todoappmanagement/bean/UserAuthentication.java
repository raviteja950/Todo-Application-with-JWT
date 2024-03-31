package com.todo.todoappmanagement.bean;

public class UserAuthentication {

	public String email;
	public String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAuthentication(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

}
