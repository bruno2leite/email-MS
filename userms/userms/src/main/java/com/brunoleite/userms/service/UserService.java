package com.brunoleite.userms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoleite.userms.model.UserModel;
import com.brunoleite.userms.producer.UserProducer;
import com.brunoleite.userms.repository.UserRepository;



@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserProducer userProducer;
	
	public UserService(UserRepository userRepository, UserProducer userProducer) {
		this.userRepository = userRepository;
		this.userProducer = userProducer;
	}
	
	@Transactional
	public UserModel save(UserModel userModel) {
		userModel = userRepository.save(userModel);
		userProducer.publishMessageEmail(userModel);
		return userModel;
	}
	
	
}
