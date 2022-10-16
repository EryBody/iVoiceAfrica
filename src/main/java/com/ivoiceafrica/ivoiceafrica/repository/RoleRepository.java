package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	//add a method to sort by created date
	public List<Role> findAllByOrderByCreatedDateDesc();

}
