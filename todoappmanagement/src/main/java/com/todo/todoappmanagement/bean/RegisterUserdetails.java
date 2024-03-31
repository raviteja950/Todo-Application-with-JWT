package com.todo.todoappmanagement.bean;

public class RegisterUserdetails {

	public String email;
	public String name;
	public String password;
	public String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public RegisterUserdetails(String email, String name, String password, String role) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public RegisterUserdetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
