package com.todo.todoappmanagement.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.todo.todoappmanagement.entity.ToboEntity;
import com.todo.todoappmanagement.logger.LoggerManager;
import com.todo.todoappmanagement.service.TodoService;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Repository("TodoDaoComponent")
public class TodoDaoImpl {

	public static final Logger LOGGER = LogManager.getLogger(TodoDaoImpl.class);

	private @Autowired @Lazy(value = true) TodoDao tododao;

	public String creatTask(ToboEntity toboEntity) {

		LoggerManager.infoSimple(LOGGER, "inside creatTask dao class");
		try {
			if (toboEntity.getName().isEmpty() || toboEntity.getTaskName().isEmpty()
					|| toboEntity.getTaskDescription().isEmpty()) {
				LoggerManager.error(LOGGER, "Name,taskName,TaskDesc Cannot be empty");
				return "fields Cannot be empty";
			} else {
				toboEntity.setIsTaskcomplted("False");
				tododao.save(toboEntity);
				LoggerManager.infoSimple(LOGGER, "data saved sucessfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LoggerManager.logError(LOGGER, "Execption occured in creatTask Api " + e.getMessage());
		}
		return "save";

	}

	public List<ToboEntity> listAllTask() {
		LoggerManager.infoSimple(LOGGER, "inside listAllTask dao class");
		List<ToboEntity> toboEntityList = new ArrayList<>();
		try {

			toboEntityList = tododao.findAll();
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in listAllTask Api " + e.getMessage());
		}
		return toboEntityList;
	}

	@SuppressWarnings("deprecation")
	public List<ToboEntity> getTaskDetaisById(ToboEntity toboEntity) {
		LoggerManager.infoSimple(LOGGER, "inside getTaskDetaisById dao class");
		List<ToboEntity> toboEntityList = new ArrayList<>();
		try {

			toboEntityList = tododao.getTaskDetaisById(toboEntity.getId());
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in getTaskDetaisById Api " + e.getMessage());
		}
		return toboEntityList;
	}

	public String deleteTaskDetaisById(ToboEntity toboEntity) {
		LoggerManager.infoSimple(LOGGER, "inside deleteTaskDetaisById dao class");
		String result = "";
		try {
			Long id = toboEntity.getId();
			List<ToboEntity> existingDetails = new ArrayList<>();
			existingDetails = tododao.getTaskDetaisById(id);

			if (!existingDetails.isEmpty()) {
				tododao.deleteById(id);
				result = "sucess";
			} else {
				result = "norecord";
			}

		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in deleteTaskDetaisById Api " + e.getMessage());
		}

		return result;
	}

	public String updateTaskDetaisById(ToboEntity toboEntity) {
		LoggerManager.infoSimple(LOGGER, "inside updateTaskDetaisById dao class");
		String result = "";
		try {

			Long id = toboEntity.getId();
			String isTaskComplted = toboEntity.getIsTaskcomplted();
			List<ToboEntity> existingDetails = new ArrayList<>();
			existingDetails = tododao.getTaskDetaisById(id);
			if (!existingDetails.isEmpty()) {

				tododao.updateDetaildById(id, isTaskComplted);
				result = "Updated";
			} else {
				result = "noRecordFound";
			}

		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "Execption occured in updateTaskDetaisById  Api" + e.getMessage());
		}
		return result;
	}

}
