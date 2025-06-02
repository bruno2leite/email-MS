package com.brunoleite.emailms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunoleite.emailms.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
