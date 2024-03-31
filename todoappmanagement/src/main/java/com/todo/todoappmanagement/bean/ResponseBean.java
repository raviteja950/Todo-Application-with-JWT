package com.todo.todoappmanagement.bean;

import java.util.List;

import com.todo.todoappmanagement.entity.ToboEntity;

public class ResponseBean {

	private int code = -1;
	private String message;
	private List<ToboEntity> toboEntityList;
	
	
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

	public List<ToboEntity> getToboEntityList() {
		return toboEntityList;
	}

	public void setToboEntityList(List<ToboEntity> toboEntityList) {
		this.toboEntityList = toboEntityList;
	}

}
