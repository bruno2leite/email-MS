package com.brunoleite.emailms.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.brunoleite.emailms.dto.EmailRecordDTO;
import com.brunoleite.emailms.model.EmailModel;
import com.brunoleite.emailms.service.EmailService;

@Component
public class EmailConsumer {
	
	final EmailService emailService;
	
	public EmailConsumer(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO) {
		var emailModel = new EmailModel();
		BeanUtils.copyProperties(emailRecordDTO, emailModel);
		emailService.sendEmail(emailModel);
	}
}
