package com.todo.todoappmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todoappmanagement.bean.JwtResponse;
import com.todo.todoappmanagement.bean.RegisterUserdetails;
import com.todo.todoappmanagement.bean.UserAuthentication;
import com.todo.todoappmanagement.service.AutheticateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/security")
public class SercurityController {

	@Autowired
	public AutheticateService service;

	@PostMapping("/registerAdminUser")
	public JwtResponse registerAdminUser(@RequestBody RegisterUserdetails user) {

		if (!user.getEmail().isEmpty() && !user.getPassword().isEmpty()) {
			JwtResponse response = new JwtResponse();
			response = service.registerAdminUser(user);
			return response;
		} else if (user.getName().isEmpty()) {
			JwtResponse response = new JwtResponse();
			response.setCode(400);
			response.setMessage("Name is mandatory");
			return response;
		} else if (user.getEmail().isEmpty()) {
			JwtResponse response = new JwtResponse();
			response.setCode(400);
			response.setMessage("Email is mandatory");
			return response;
		} else if (user.getPassword().isEmpty()) {
			JwtResponse response = new JwtResponse();
			response.setCode(400);
			response.setMessage("password is mandatory");
			return response;
		} else {
			JwtResponse response = new JwtResponse();
			response.setCode(400);
			response.setMessage("Name,Email and password are mandatory fields");
			return response;
		}

	}

	@PostMapping("/AuthenticateUser")
	public JwtResponse AuthenticateUser(@RequestBody UserAuthentication userAuthenticate) {

		if (!userAuthenticate.getEmail().isEmpty() && !userAuthenticate.getPassword().isEmpty()) {
			JwtResponse response = new JwtResponse();
			response = service.AuthenticateUser(userAuthenticate);
			return response;
		} else if (userAuthenticate.getEmail().isEmpty()) {
			JwtResponse response = new JwtResponse();
			response.setCode(400);
			response.setMessage("UserName is mandatory");
			return response;
		} else if (userAuthenticate.getPassword().isEmpty()) {
			JwtResponse response = new JwtResponse();
			response.setCode(400);
			response.setMessage("password is mandatory");
			return response;
		} else {
			JwtResponse response = new JwtResponse();
			response.setCode(400);
			response.setMessage("UserName and password are both mandatory");
			return response;
		}

	}

}
