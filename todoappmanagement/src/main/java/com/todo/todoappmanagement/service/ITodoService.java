package com.todo.todoappmanagement.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.todo.todoappmanagement.entity.ToboEntity;

public interface ITodoService {

	public String createTask(ToboEntity toboEntity);

	public List<ToboEntity> listAllTask();

	public List<ToboEntity> getTaskDetaisById(ToboEntity toboEntity);

	public String deleteTaskDetaisById(ToboEntity toboEntity);

	public String updateTaskDetaisById(ToboEntity toboEntity);
}
