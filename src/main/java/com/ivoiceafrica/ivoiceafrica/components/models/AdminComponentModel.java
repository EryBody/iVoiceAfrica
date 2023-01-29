package com.ivoiceafrica.ivoiceafrica.components.models;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;

@Component("AdminComponentModel")
public class AdminComponentModel {

	
	@Autowired
	CustomUserDetailService userService;
	
	public User getUserDetails(String userId) {
		
		Optional<User> opUser = userService.findFirstUserByUsername(userId);
		return opUser.get();
	}
}
