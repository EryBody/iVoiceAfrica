package com.ivoiceafrica.ivoiceafrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.auth.entity.UserStatus;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
	
	
	
}
