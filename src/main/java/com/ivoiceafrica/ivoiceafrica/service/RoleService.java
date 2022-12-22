package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.Role;


public interface RoleService {

	public List<Role> findAll();
	
	public List<Role> findAllByOrderByCreatedDateDesc();

	public Optional<Role> findById(int theId);

	public void save(Role theRole);

	public void deleteById(int theId);
}
