package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.repository.ServiceRenderedRepository;


@Service
public class SRenderedServiceImpl implements SRenderedService {

	@Autowired
	ServiceRenderedRepository serviceRenderedRepository;
	
	@Override
	public List<ServiceRendered> findAll() {
		return serviceRenderedRepository.findAll();
	}

	@Override
	public void save(ServiceRendered theService) {
		serviceRenderedRepository.save(theService);
	}

	@Override
	public void deleteById(String theId) {
		serviceRenderedRepository.deleteById(theId);
	}

	@Override
	public Optional<ServiceRendered> findById(String theId) {
		
		Optional<ServiceRendered> result = serviceRenderedRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+theId);
		}
	}

	@Override
	public Optional<ServiceRendered> findServiceRenderedByUser(User user) {
		
		Optional<ServiceRendered> result = serviceRenderedRepository.findServiceRenderedByUser(user);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+user);
		}
	}
	
	@Override
	public Optional<ServiceRendered> findFirstServiceRenderedByUser(User user) {
		
		Optional<ServiceRendered> result = serviceRenderedRepository.findFirstServiceRenderedByUser(user);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+user);
		}
	}

	@Override
	public List<ServiceRendered> findServiceRenderedListByUser(User user) {
		return serviceRenderedRepository.findServiceRenderedListByUser(user);
	}

	@Override
	public List<ServiceRendered> findServiceRenderedListByServiceType(ServiceType serviceType) {
		return serviceRenderedRepository.findServiceRenderedListByServiceType(serviceType);
	}

	@Override
	public List<ServiceRendered> findServiceRenderedListByUserAndServiceType(User user, ServiceType type) {
		return serviceRenderedRepository.findServiceRenderedListByUserAndServiceType(user, type);
	}
	
	
	
}
