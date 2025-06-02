package com.brunoleite.userms.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.brunoleite.userms.dto.EmailDTO;
import com.brunoleite.userms.model.UserModel;

@Component
public class UserProducer {
	
	final RabbitTemplate rabbitTemplate;
	
	public UserProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Value(value = "${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(UserModel userModel) {
		var emailDTO = new EmailDTO();
		emailDTO.setUserId(userModel.getUserId());
		emailDTO.setEmailTo(userModel.getEmail());
		emailDTO.setSubject("Registration completed successfully");
		emailDTO.setText(userModel.getName() + ", Welcome!");
		
		rabbitTemplate.convertAndSend("", routingKey, emailDTO);
		
	}
}
