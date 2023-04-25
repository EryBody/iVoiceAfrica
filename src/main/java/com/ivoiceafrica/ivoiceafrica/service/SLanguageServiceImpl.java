package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceLanguages;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.repository.ServiceLanguageRepository;


@Service
public class SLanguageServiceImpl implements SLanguageService {

	@Autowired
	ServiceLanguageRepository serviceLanguageRepository;
	
	@Override
	public List<ServiceLanguages> findAll() {
		return serviceLanguageRepository.findAll();
	}

	@Override
	public void save(ServiceLanguages theService) {
		serviceLanguageRepository.save(theService);
	}

	@Override
	public void deleteById(String theId) {
		serviceLanguageRepository.deleteById(theId);
	}

	@Override
	public Optional<ServiceLanguages> findById(String theId) {
		
		Optional<ServiceLanguages> result = serviceLanguageRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+theId);
		}
	}

	@Override
	public Optional<ServiceLanguages> findFirstServiceLanguageByServiceRendered(ServiceRendered serviceRendered) {
		return serviceLanguageRepository.findFirstServiceLanguageByServiceRendered(serviceRendered);
	}

	@Override
	public List<ServiceLanguages> findServiceLanguageByServiceRendered(ServiceRendered serviceRendered) {
		return serviceLanguageRepository.findServiceLanguageByServiceRendered(serviceRendered);
	}

	@Override
	public List<ServiceLanguages> findFirst3ServiceLanguageByServiceRendered(ServiceRendered serviceRendered) {
		return serviceLanguageRepository.findFirst3ServiceLanguageByServiceRendered(serviceRendered);
	}

	@Override
	public List<ServiceLanguages> findFirst3ServiceLanguageByServiceRenderedAndUser(ServiceRendered serviceRendered,
			User user) {
		return serviceLanguageRepository.findFirst3ServiceLanguageByServiceRenderedAndUser(serviceRendered, user);
	}
	
	
	
	
}
