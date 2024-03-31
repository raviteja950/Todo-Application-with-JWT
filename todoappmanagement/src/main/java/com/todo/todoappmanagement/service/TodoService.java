package com.todo.todoappmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.todo.todoappmanagement.controller.TodoController;
import com.todo.todoappmanagement.dao.TodoDaoImpl;
import com.todo.todoappmanagement.entity.ToboEntity;
import com.todo.todoappmanagement.logger.LoggerManager;

@Service("todoService")
public class TodoService implements ITodoService {

	public static final Logger LOGGER = LogManager.getLogger(TodoService.class);

	private @Autowired @Lazy(value = true) TodoDaoImpl todoDaoImpl;

	@Override
	public String createTask(ToboEntity toboEntity) {
		LoggerManager.infoSimple(LOGGER, "inside createTask service class ");
		String result = "";
		try {
			result = todoDaoImpl.creatTask(toboEntity);
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in creatTask Api " + e.getMessage());
		}
		return result;
	}

	public List<ToboEntity> listAllTask() {
		LoggerManager.infoSimple(LOGGER, "inside listAllTask service class ");
		List<ToboEntity> toboEntityList = new ArrayList<>();
		try {
			toboEntityList = todoDaoImpl.listAllTask();
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in listAllTask Api " + e.getMessage());
		}
		return toboEntityList;
	}

	@Override
	public List<ToboEntity> getTaskDetaisById(ToboEntity toboEntity) {

		LoggerManager.infoSimple(LOGGER, "inside getTaskDetaisById service class ");
		List<ToboEntity> toboEntityList = new ArrayList<>();
		try {
			toboEntityList = todoDaoImpl.getTaskDetaisById(toboEntity);
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in getTaskDetaisById Api " + e.getMessage());
		}
		return toboEntityList;
	}

	public String deleteTaskDetaisById(ToboEntity toboEntity) {
		LoggerManager.infoSimple(LOGGER, "inside deleteTaskDetaisById service class ");
		String result = "";
		try {
			result = todoDaoImpl.deleteTaskDetaisById(toboEntity);
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in deleteTaskDetaisById Api " + e.getMessage());
		}
		return result;
	}

	public String updateTaskDetaisById(ToboEntity toboEntity) {
		LoggerManager.infoSimple(LOGGER, "inside updateTaskDetaisById service class ");
		String result = "";
		try {
			result = todoDaoImpl.updateTaskDetaisById(toboEntity);
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in updateTaskDetaisById Api " + e.getMessage());
		}
		return result;
	}

}
