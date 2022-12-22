package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.UserStatus;
import com.ivoiceafrica.ivoiceafrica.repository.UserStatusRepository;


@Service
public class UserStatusServiceImpl implements UserStatusService {

	@Autowired
	UserStatusRepository userStatusRepository;

	@Override
	public List<UserStatus> findAll() {
		return userStatusRepository.findAll();
	}

	@Override
	public Optional<UserStatus> findById(int theId) {
		
		Optional<UserStatus> result = userStatusRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void save(UserStatus theStatus) {
		userStatusRepository.save(theStatus);
		
	}

	@Override
	public void deleteById(int theId) {
		userStatusRepository.deleteById(theId);
		
	}
	
}
