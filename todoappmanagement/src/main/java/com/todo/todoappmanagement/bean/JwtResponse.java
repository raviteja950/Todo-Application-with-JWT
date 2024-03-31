package com.todo.todoappmanagement.bean;

public class JwtResponse {

	int code = -1;
	String message = null;
	String token = null;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtResponse(int code, String message, String token) {
		super();
		this.code = code;
		this.message = message;
		this.token = token;
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
