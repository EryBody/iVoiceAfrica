package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findUserByUsername(String username);
	
	//add a method to sort by created date
	public List<User> findAllByOrderByCreatedDateDesc();
	
	
}
