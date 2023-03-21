package com.ivoiceafrica.ivoiceafrica.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
	
	@Value("${profile.environment}")
	String profileEnvironment;

	
	@GetMapping("/get-environment-profile")
	public String getProfile() {
		
		return "Current Env Profile : "+profileEnvironment;
	}
	
}
