package com.ivoiceafrica.ivoiceafrica.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
	
	@Value("${profile.environment}")
	String profileEnvironment;
	
	@Value("${upload.path}")
	String uploadDir;

	
	@GetMapping("/get-environment-profile")
	public String getProfile() {
		
		return "Current Env Profile : "+profileEnvironment;
	}
	
	@GetMapping("/get-upload-path")
	public String getUploadPath() {
		
		return "Current Upload Profile : "+uploadDir;
	}
	
}
