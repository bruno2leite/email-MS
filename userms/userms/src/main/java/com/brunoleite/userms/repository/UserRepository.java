package com.brunoleite.userms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunoleite.userms.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

}
