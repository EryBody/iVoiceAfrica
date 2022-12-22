package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.ServiceIndustries;
import com.ivoiceafrica.ivoiceafrica.repository.ServiceIndustryRepository;


@Service
public class SIndustryServiceeImpl implements SIndustriesService {

	@Autowired
	ServiceIndustryRepository serviceIndustriesRepository;
	
	@Override
	public List<ServiceIndustries> findAll() {
		return serviceIndustriesRepository.findAll();
	}

	@Override
	public void save(ServiceIndustries theService) {
		serviceIndustriesRepository.save(theService);
	}

	@Override
	public void deleteById(String theId) {
		serviceIndustriesRepository.deleteById(theId);
	}

	@Override
	public Optional<ServiceIndustries> findById(String theId) {
		
		Optional<ServiceIndustries> result = serviceIndustriesRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+theId);
		}
	}
	
	
	
}
