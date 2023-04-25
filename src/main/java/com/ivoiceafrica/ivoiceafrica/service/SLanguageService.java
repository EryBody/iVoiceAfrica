package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceLanguages;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;


public interface SLanguageService {

	public List<ServiceLanguages> findAll();
	
	public Optional<ServiceLanguages> findById(String theId);

	public void save(ServiceLanguages theRole);

	public void deleteById(String theId);
	
	Optional<ServiceLanguages> findFirstServiceLanguageByServiceRendered(ServiceRendered serviceRendered);
	
	List<ServiceLanguages> findServiceLanguageByServiceRendered(ServiceRendered serviceRendered);
	
	List<ServiceLanguages> findFirst3ServiceLanguageByServiceRendered(ServiceRendered serviceRendered);
	
	List<ServiceLanguages> findFirst3ServiceLanguageByServiceRenderedAndUser(ServiceRendered serviceRendered, User user);
}
