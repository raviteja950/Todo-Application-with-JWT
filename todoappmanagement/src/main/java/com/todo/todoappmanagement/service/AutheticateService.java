package com.todo.todoappmanagement.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.todoappmanagement.bean.JwtResponse;
import com.todo.todoappmanagement.bean.RegisterUserdetails;
import com.todo.todoappmanagement.bean.UserAuthentication;
import com.todo.todoappmanagement.config.ApplicationConfig;
import com.todo.todoappmanagement.config.JwtService;
import com.todo.todoappmanagement.controller.SercurityController;
import com.todo.todoappmanagement.dao.UserRepository;
import com.todo.todoappmanagement.entity.User;
import com.todo.todoappmanagement.logger.LoggerManager;
import com.todo.todoappmanagement.util.Role;

@Service
public class AutheticateService {

	public static final Logger LOGGER = LogManager.getLogger(AutheticateService.class);

	@Autowired
	public UserRepository repository;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public JwtService jwtService;

	@Autowired
	public AuthenticationManager authenticationManager;

	public JwtResponse registerAdminUser(RegisterUserdetails user) {

		LoggerManager.infoSimple(LOGGER, "inside registerAdminUser service layer api");

		JwtResponse response = new JwtResponse();
		User userDetails = new User();
		userDetails.setName(user.getName());
		userDetails.setEmail(user.getEmail());
		userDetails.setPassword(passwordEncoder.encode(user.getPassword()));
		userDetails.setRole(Role.Admin);
		repository.save(userDetails);
		// String token = jwtService.generateTokenByUserName(userDetails);
		LoggerManager.infoSimple(LOGGER, "User Registered Successfully!..");
		response.setCode(200);
		response.setMessage(" User Registered Successfully!..");
		return response;
	}

	public JwtResponse AuthenticateUser(UserAuthentication userAuthenticate) {

		LoggerManager.infoSimple(LOGGER, "inside AuthenticateUser service layer api");
		JwtResponse response = new JwtResponse();
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthenticate.getEmail(),
					userAuthenticate.getPassword()));

			var user = repository.findByEmail(userAuthenticate.getEmail());
			String token = jwtService.generateTokenByUserName(user.get());

			if (token.isEmpty() || user.isEmpty()) {
				LoggerManager.infoSimple(LOGGER, "User failed to authenticate....");
				response.setCode(400);
				response.setMessage("User failed to authenticate....");
			} else {
				LoggerManager.infoSimple(LOGGER, "User Successfully authenticated....");
				response.setCode(200);
				response.setMessage(" User Successfully authenticated....");
				response.setToken(token);
			}

		} catch (Exception e) {

			if (e.getMessage().equalsIgnoreCase("Bad credentials")) {
				LoggerManager.infoSimple(LOGGER, "oops... Email or Password are Incorrect...");
				response.setCode(400);
				response.setMessage(" oops... Email or Password are Incorrect...");
			}

		}
		return response;

	}
}
