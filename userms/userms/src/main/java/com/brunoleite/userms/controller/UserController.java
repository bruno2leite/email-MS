package com.brunoleite.userms.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brunoleite.userms.dto.UserRecordDTO;
import com.brunoleite.userms.model.UserModel;
import com.brunoleite.userms.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDTO userRecordDTO){
		var userModel = new UserModel();
		BeanUtils.copyProperties(userRecordDTO, userModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
	}
}
