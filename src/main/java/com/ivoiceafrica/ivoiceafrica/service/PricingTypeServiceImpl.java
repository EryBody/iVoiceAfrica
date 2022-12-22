package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.PricingType;
import com.ivoiceafrica.ivoiceafrica.repository.PricingTypeRepository;

@Service

public class PricingTypeServiceImpl implements PricingTypeService {

	
	@Autowired
	PricingTypeRepository pricingTypeRepository;
	
	@Override
	public List<PricingType> findAll() {
		return pricingTypeRepository.findAll();
	}

	@Override
	public void save(PricingType thePricing) {
		pricingTypeRepository.save(thePricing);
	}

	@Override
	public void deleteById(String theId) {
		pricingTypeRepository.deleteById(theId);
	}

	@Override
	public Optional<PricingType> findById(String theId) {
		
		Optional<PricingType> result = pricingTypeRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+theId);
		}
	}
	
	
	
}
