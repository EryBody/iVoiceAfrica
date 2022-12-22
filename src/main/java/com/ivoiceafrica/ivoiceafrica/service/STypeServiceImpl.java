package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.repository.ServiceTypeRepository;


@Service
public class STypeServiceImpl implements STypeService {

	@Autowired
	ServiceTypeRepository serviceTypeRepository;
	
	@Override
	public List<ServiceType> findAll() {
		return serviceTypeRepository.findAll();
	}

	@Override
	public void save(ServiceType theService) {
		serviceTypeRepository.save(theService);
	}

	@Override
	public void deleteById(String theId) {
		serviceTypeRepository.deleteById(theId);
	}

	@Override
	public Optional<ServiceType> findById(String theId) {
		
		Optional<ServiceType> result = serviceTypeRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+theId);
		}
	}
	
	
	
}
