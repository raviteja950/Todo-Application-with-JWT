package com.todo.todoappmanagement.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todoappmanagement.bean.ResponseBean;
import com.todo.todoappmanagement.entity.ToboEntity;
import com.todo.todoappmanagement.logger.LoggerManager;
import com.todo.todoappmanagement.service.TodoService;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
@Configuration
@RestController
@RequestMapping("/main")
public class TodoController {

	public static final Logger LOGGER = LogManager.getLogger(TodoController.class);

	private @Autowired @Lazy(value = true) TodoService todoService;

	@PostMapping("/createTask")
	public ResponseBean createTask(@RequestBody ToboEntity toboEntity)
			throws IllegalArgumentException, InvocationTargetException {
		LoggerManager.infoSimple(LOGGER, "inside createTask api");
		ResponseBean response = new ResponseBean();
		try {
			String result = todoService.createTask(toboEntity);

			if (result.equalsIgnoreCase("save")) {
				response.setCode(200);
				response.setMessage("data saved sucessfully.");
				LoggerManager.infoSimple(LOGGER, "data saved sucessfully.");
			} else {
				response.setCode(400);
				response.setMessage("All Fields are mandatory.");
				LoggerManager.infoSimple(LOGGER, "All Fields are mandatory.");
			}
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "expection occured in createTask api" + e.getMessage());
		}

		return response;
	}

	@GetMapping("/listAllTask")
	public ResponseBean listAllTask() {
		ResponseBean response = new ResponseBean();

		LoggerManager.infoSimple(LOGGER, "inside listAllTask api");
		try {
			List<ToboEntity> toboEntitylist = todoService.listAllTask();

			if (!toboEntitylist.isEmpty()) {
				response.setCode(200);
				response.setMessage("Records feteched sucessfully.");
				response.setToboEntityList(toboEntitylist);
				LoggerManager.infoSimple(LOGGER, "Records feteched sucessfully.");
			} else {
				response.setCode(200);
				response.setMessage("No Records found.");
				LoggerManager.infoSimple(LOGGER, "No Records found.");
			}
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "expection occured in List api" + e.getMessage());
		}

		return response;
	}

	@PostMapping("/getTaskDetaisById")
	public ResponseBean getTaskDetaisById(@RequestBody ToboEntity toboEntity) {
		ResponseBean response = new ResponseBean();
		LoggerManager.infoSimple(LOGGER, "inside getTaskDetaisById api");

		List<ToboEntity> ToboDetails = new ArrayList<>();
		try {

			ToboDetails = todoService.getTaskDetaisById(toboEntity);

			if (!ToboDetails.isEmpty()) {
				response.setCode(200);
				response.setMessage("record feteched sucessfully.");
				response.setToboEntityList(ToboDetails);
				LoggerManager.infoSimple(LOGGER, "record feteched sucessfully.");

			} else {
				response.setCode(200);
				response.setMessage("No record found.");
				LoggerManager.infoSimple(LOGGER, "No record found.");
			}
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "expection occured in getTaskDetaisById api" + e.getMessage());
		}

		return response;
	}

	@PostMapping("/deleteTaskDetaisById")
	public ResponseBean deleteTaskDetaisById(@RequestBody ToboEntity toboEntity) {
		ResponseBean response = new ResponseBean();
		LoggerManager.infoSimple(LOGGER, "inside deleteTaskDetaisById api");
		String result = "";
		try {

			result = todoService.deleteTaskDetaisById(toboEntity);

			if (result.equals("sucess")) {
				response.setCode(200);
				response.setMessage("record deleted sucessfully.");
				LoggerManager.infoSimple(LOGGER, "record deleted sucessfully.");
			} else if (result.equals("norecord")) {
				response.setCode(400);
				response.setMessage("No record found to delete.");
				LoggerManager.infoSimple(LOGGER, "No record found to delete.");
			}
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "expection occured in deleteTaskDetaisById api" + e.getMessage());
		}

		return response;
	}

	@PostMapping("/updateTaskDetaisById")
	public ResponseBean updateTaskDetaisById(@RequestBody ToboEntity toboEntity) {
		ResponseBean response = new ResponseBean();
		LoggerManager.infoSimple(LOGGER, "inside updateTaskDetaisById api");
		String result = "";
		try {

			result = todoService.updateTaskDetaisById(toboEntity);
			if (result.equals("Updated")) {
				response.setCode(200);
				response.setMessage("record updated sucessfully.");
				LoggerManager.infoSimple(LOGGER, "record updated sucessfully.");
			} else if (result.equalsIgnoreCase("noRecordFound")) {
				response.setCode(400);
				response.setMessage("No record found to update.");
				LoggerManager.infoSimple(LOGGER, "No record found to update.");
			}
		} catch (Exception e) {
			LoggerManager.logError(LOGGER, "expection occured in updateTaskDetaisById api" + e.getMessage());
		}

		return response;
	}

}
