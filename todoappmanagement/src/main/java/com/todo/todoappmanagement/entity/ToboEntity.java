package com.todo.todoappmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Todo_Details")
public class ToboEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "Id")
	private Long id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "task_description")
	private String taskDescription;

	@Column(name = "task_is_complted")
	private String isTaskcomplted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getIsTaskcomplted() {
		return isTaskcomplted;
	}

	public void setIsTaskcomplted(String isTaskcomplted) {
		this.isTaskcomplted = isTaskcomplted;
	}

}
