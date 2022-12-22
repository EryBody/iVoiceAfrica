package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.Role;
import com.ivoiceafrica.ivoiceafrica.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public void save(Role theRole) {
		roleRepository.save(theRole);
	}

	@Override
	public void deleteById(int theId) {
		roleRepository.deleteById(theId);
	}

	@Override
	public List<Role> findAllByOrderByCreatedDateDesc() {
		return roleRepository.findAllByOrderByCreatedDateDesc();
	}

	
	@Override
	public Optional<Role> findById(int theId) {
		
		Optional<Role> result = roleRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find Role id - "+theId);
		}
	}

}
