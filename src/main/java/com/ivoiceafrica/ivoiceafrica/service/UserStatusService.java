package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.UserStatus;


public interface UserStatusService {

	public List<UserStatus> findAll();

	public Optional<UserStatus> findById(int theId);

	public void save(UserStatus theStatus);

	public void deleteById(int theId);
	
}
